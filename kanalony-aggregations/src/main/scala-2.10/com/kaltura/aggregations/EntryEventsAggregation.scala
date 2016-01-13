package com.kaltura.aggregations


import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.{EnrichedPlayerEvent, PlayerEventParser}
import kafka.serializer.StringDecoder
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.{SparkContext, Logging, SparkConf}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{StateSpec, Seconds, StreamingContext, Time, State}
import com.datastax.spark.connector._
import org.apache.log4j.{Level, Logger}

import org.joda.time.DateTime

/**
 * Created by orlylampert on 1/11/16.
 */
object EntryEventsAggregation extends App with Logging {

  case class EntryAggrKey(entryId: String, eventType: Int, minute: DateTime)

  case class EntryAggrResult(entryId: String, eventType: Int, minute: DateTime, count: Long)

  override def main(args: Array[String]) {

    setStreamingLogLevels
    val applicationName = ConfigurationManager.get("kanalony.events_aggregation.application_name")
    val checkpointDirectory = s"/tmp/checkpoint/$applicationName"
    // Get StreamingContext from checkpoint data or create a new one
    val ssc = StreamingContext.getOrCreate(checkpointDirectory,
      () => {
        createSparkStreamingContext(checkpointDirectory)
      })

    // Start the computation
    ssc.start()
    ssc.awaitTermination()

  }

  def createSparkStreamingContext(checkpointDirectory: String): StreamingContext = {

    val sparkConf = new SparkConf().
      setAppName(ConfigurationManager.get("kanalony.events_aggregation.application_name")).
      setMaster(ConfigurationManager.getOrElse("kanalony.events_aggregations.master", "local[8]")).
      set("spark.cassandra.connection.host", ConfigurationManager.getOrElse("kanalony.events_aggregations.cassandra_host", "localhost"))
    val sparkContext = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sparkContext, Seconds(ConfigurationManager.getOrElse("kanalony.events_aggregations.batch_duration", "20").toInt))
    ssc.checkpoint(checkpointDirectory)

    val stateSpec = StateSpec.function(trackStateFunc _)
    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_aggregations.kafka_brokers", "127.0.0.1:9092")
    val topics = Set("enriched-player-events")
    val stream = StreamManager.createStream(ssc, kafkaBrokers, topics)

    val parsedEnrichedEvents = stream.
      map(_._2).
      flatMap(PlayerEventParser.parseEnhancedPlayerEvent)

    val aggregatedBatchEvents = parsedEnrichedEvents.map(x => (EntryAggrKey(x.entryId, x.eventType, x.eventTime.minuteOfHour().roundFloorCopy()),1L)).reduceByKey(_ + _)
    val aggregatedEvents = aggregatedBatchEvents.mapWithState(stateSpec)
    //aggregatedBatchEvents.print()
    //aggregatedEvents.print()
    aggregatedEvents.foreachRDD((rdd) => rdd.map {
      case (k,v) => EntryAggrResult(k.entryId, k.eventType, k.minute, v)
    }.saveToCassandra(ConfigurationManager.getOrElse("kanalony.events_aggregations.keyspace", "events_aggregations"), "entry_events_by_minute"))


    ssc
  }

  def trackStateFunc(batchTime: Time, key: EntryAggrKey, value: Option[Long], state: State[Long]): Option[(EntryAggrKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    state.update(sum)
    Some(output)
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
