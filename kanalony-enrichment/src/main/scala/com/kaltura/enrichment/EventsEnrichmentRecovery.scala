package com.kaltura.enrichment

import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.PlayerEventParser
import org.apache.commons.cli.{GnuParser, Options}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{Logging, SparkConf, SparkContext}
import org.joda.time.DateTime


object EventsEnrichmentRecovery extends App with Logging {

  override def main(args: Array[String]) {

    setStreamingLogLevels

    val options = new Options()
    options.addOption("s", "fromhour", true, "from hour")
    options.addOption("e", "tohour", true, "end hour")

    val parser = new GnuParser()
    val cmd = parser.parse( options, args)
    val fromHour = new DateTime(cmd.getOptionValue("s"))
    val toHour = new DateTime(cmd.getOptionValue("e"))
    val applicationName = ConfigurationManager.get("kanalony.events_enrichment_recovery.application_name")
    val checkpointRootPath = ConfigurationManager.getOrElse("kanalony.checkpoint_root_path","/tmp/checkpoint")
    val checkpointDirectory = s"$checkpointRootPath/$applicationName"
    // Get StreamingContext from checkpoint data or create a new one
    val ssc = StreamingContext.getOrCreate(checkpointDirectory,
      () => {
        createSparkStreamingContext(checkpointDirectory, fromHour, toHour)
      })

    // Start the computation
    ssc.start()
    ssc.awaitTermination()

  }

  def createSparkStreamingContext(checkpointDirectory: String, fromHour: DateTime, toHour: DateTime): StreamingContext = {
    val sparkConf = new SparkConf()
      .setAppName(ConfigurationManager.get("kanalony.events_enrichment_recovery.application_name"))
      .setMaster(ConfigurationManager.getOrElse("kanalony.events_enrichment_recovery.master","local[8]"))
      .set("spark.cassandra.connection.host", ConfigurationManager.getOrElse("kanalony.events_enhancer.cassandra_host","127.0.0.1"))
      .set("spark.cassandra.connection.keep_alive_ms","30000")
      .set("spark.streaming.backpressure.enabled", ConfigurationManager.getOrElse("kanalony.events_enrichment_recovery.backpressure","false"))
    val sparkContext = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sparkContext, Seconds(ConfigurationManager.getOrElse("kanalony.events_enrichment_recovery.batch_duration","5").toInt))
    ssc.checkpoint(checkpointDirectory)

    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_enhancer.kafka_brokers","127.0.0.1:9092")
    val kafkaParams = Map(
      "metadata.broker.list" -> kafkaBrokers
    )

    val startingOffsets = StreamManager.getPartitionsOffsets("player-events", fromHour.minusHours(1))
    val stream = StreamManager.createStream(ssc, kafkaParams, startingOffsets)

    stream.flatMap(event => PlayerEventParser.parsePlayerEvent(event._2))
      .filter(event => (event.eventTime.hourOfDay().roundFloorCopy().isAfter(fromHour) && event.eventTime.hourOfDay().roundFloorCopy().isBefore(toHour))
          || event.eventTime.hourOfDay().roundFloorCopy().isEqual(fromHour))

      .foreachRDD { rdd =>
        EventsEnrichment.enrichEvents(rdd, "recovery-enriched-player-events", "erroneous-player-events")

      }

    ssc

  }

  def setStreamingLogLevels {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
      logInfo("Setting log level to [WARN] for streaming example." +
        " To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }
}
