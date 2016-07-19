package com.kaltura.aggregations

import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.PlayerEventParser
import org.apache.commons.cli.{GnuParser, Options}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{Logging, SparkConf, SparkContext}
import org.joda.time.DateTime

object EventsAggregationRecovery extends App with Logging {

  case class EntryAggrKey(entryId: String, eventType: Int, minute: DateTime)

  case class EntryAggrResult(entryId: String, eventType: Int, minute: DateTime, count: Long)

  override def main(args: Array[String]) {

    setStreamingLogLevels

    val options = new Options()
    options.addOption("a", "aggregation", true, "set of aggregation to run")
    options.addOption("n", "name", true, "application name")
    options.addOption("t", "topic", true, "kafka events topic")
    options.addOption("s", "fromhour", true, "from hour")
    options.addOption("e", "tohour", true, "end hour")

    val parser = new GnuParser()
    val cmd = parser.parse( options, args)
    val enabledAggregations = cmd.getOptionValue("a", "")
    val applicationSuffix = cmd.getOptionValue("n", "")
    val topic = cmd.getOptionValue("t", "enriched-player-events")
    val fromHour = if (cmd.hasOption("s")) Some(new DateTime(cmd.getOptionValue("s"))) else None
    val toHour = if (cmd.hasOption("e")) Some(new DateTime(cmd.getOptionValue("e"))) else None


    val applicationName = ConfigurationManager.get("kanalony.events_aggregations_recovery.application_name")
    val checkpointRootPath = ConfigurationManager.getOrElse("kanalony.checkpoint_root_path","/tmp/checkpoint")
    val checkpointDirectory = s"$checkpointRootPath/$applicationName"

    ConfigurationManager.set("kanalony.events_aggregations.enabled_aggregations", enabledAggregations)
    // Get StreamingContext from checkpoint data or create a new one
    val ssc = StreamingContext.getOrCreate(checkpointDirectory,
      () => {
        createSparkStreamingContext(checkpointDirectory, fromHour, toHour, topic)

      })

    // Start the computation
    ssc.start()
    ssc.awaitTermination()

  }

  def createSparkStreamingContext(checkpointDirectory: String, fromHour: Option[DateTime], toHour: Option[DateTime], eventsTopic: String, aggregators: List[(String)] = List()): StreamingContext = {

    val sparkConf = new SparkConf()
      .setAppName(ConfigurationManager.get("kanalony.events_aggregations_recovery.application_name"))
      .setMaster(ConfigurationManager.getOrElse("kanalony.events_aggregations.master", "local[8]"))
      .set("spark.cassandra.connection.host", ConfigurationManager.getOrElse("kanalony.events_aggregations.cassandra_host", "localhost"))
      .set("spark.cassandra.connection.keep_alive_ms","30000")
      .set("spark.streaming.concurrentJobs",ConfigurationManager.getOrElse("kanalony.events_aggregations.concurrentJobs", "10"))
      .set("spark.scheduler.mode", "FAIR")
    val defaultParallelism = ConfigurationManager.getOrElse("kanalony.events_aggregations.default_parallelism", null)
    if (defaultParallelism != null)
      sparkConf.set("spark.default.parallelism", defaultParallelism)

    val sparkContext = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sparkContext, Seconds(ConfigurationManager.getOrElse("kanalony.events_aggregations.batch_duration", "10").toInt))
    ssc.checkpoint(checkpointDirectory)

    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_aggregations.kafka_brokers", "127.0.0.1:9092")
    val kafkaParams = Map(
      "metadata.broker.list" -> kafkaBrokers
    )
    val topics = Set(eventsTopic)

    val stream  = {
      if (fromHour.isEmpty || toHour.isEmpty) {
        StreamManager.createStream(ssc, kafkaBrokers, Set(eventsTopic)).checkpoint(Seconds(ConfigurationManager.getOrElse("kanalony.events_aggregations.checkpoint_interval_sec", "30").toInt))
      } else {
        val startingOffsets = StreamManager.getPartitionsOffsets(eventsTopic, fromHour.get.minusHours(1))
        StreamManager.createStream(ssc, kafkaParams, startingOffsets).checkpoint(Seconds(ConfigurationManager.getOrElse("kanalony.events_aggregations.checkpoint_interval_sec", "30").toInt))
      }
    }

    val parsedEnrichedEvents = {
      if (fromHour.isEmpty || toHour.isEmpty) {
        stream.flatMap(ev => PlayerEventParser.parseEnhancedPlayerEvent(ev._2))
      } else {
        stream.flatMap(ev => PlayerEventParser.parseEnhancedPlayerEvent(ev._2)).
          filter(event => (event.eventTime.hourOfDay().roundFloorCopy().isAfter(fromHour.get) && event.eventTime.hourOfDay().roundFloorCopy().isBefore(toHour.get))
            || event.eventTime.hourOfDay().roundFloorCopy().isEqual(fromHour.get))
      }
    }

    EventsAggregation.aggregate(parsedEnrichedEvents)

    ssc
  }

  def setStreamingLogLevels {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
      logInfo("Setting log level to [WARN] for streaming example." +
        " To override add a custom log4j.properties to the classpath.")
    }
    Logger.getRootLogger.setLevel(Level.WARN)
  }



}


