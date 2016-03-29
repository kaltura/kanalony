package com.kaltura.enrichment

import com.kaltura.core.ip2location.LocationResolver
import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.urls.UrlParser
import com.kaltura.core.userAgent.UserAgentResolver
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.entities.PlayerEventTypes
import com.kaltura.model.events.{EnrichedPlayerEvent, PlayerEventParser, RawPlayerEvent}
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.kafka.{HasOffsetRanges, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{Logging, SparkConf, SparkContext}


object EventsEnrichment extends App with Logging {

  override def main(args: Array[String]) {

    setStreamingLogLevels
    val applicationName = ConfigurationManager.get("kanalony.events_enhancer.application_name")
    val checkpointRootPath = ConfigurationManager.getOrElse("kanalony.checkpoint_root_path","/tmp/checkpoint")
    val checkpointDirectory = s"$checkpointRootPath/$applicationName"
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
    val sparkConf = new SparkConf()
      .setAppName(ConfigurationManager.get("kanalony.events_enhancer.application_name"))
      .setMaster(ConfigurationManager.getOrElse("kanalony.events_enhancer.master","local[8]"))
      .set("spark.cassandra.connection.host", ConfigurationManager.getOrElse("kanalony.events_enhancer.cassandra_host","127.0.0.1"))
      .set("spark.cassandra.connection.keep_alive_ms","30000")
      .set("spark.streaming.backpressure.enabled", ConfigurationManager.getOrElse("kanalony.events_enhancer.backpressure","false"))
    val sparkContext = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sparkContext, Seconds(ConfigurationManager.getOrElse("kanalony.events_enhancer.batch_duration","1").toInt))
    ssc.checkpoint(checkpointDirectory)

    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_enhancer.kafka_brokers","127.0.0.1:9092")
    val topics = Set("player-events")

    var offsetRanges = Array[OffsetRange]()
    val stream = StreamManager.createStream(ssc, kafkaBrokers, topics)
    stream.transform { rdd =>
      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      rdd
    }.map(_._2).
      flatMap(PlayerEventParser.parsePlayerEvent).
      foreachRDD { rdd =>
        enrichEvents(rdd)
        for (o <- offsetRanges) {
          println(s"${o.topic} ${o.partition} ${o.fromOffset} ${o.untilOffset}")
        }
      }

    ssc
  }

  def enrichByEntities(playerEvents:RDD[RawPlayerEvent]): RDD[RawPlayerEvent] = {
    EnrichByEntry.enrich(EnrichByPartner.enrich(playerEvents))
  }

  def enrichEvents(playerEvents:RDD[RawPlayerEvent]):Unit = {
    enrichByEntities(playerEvents).
      foreachPartition( eventsPart => {
        val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_enhancer.kafka_brokers","127.0.0.1:9092")
        val producer = StreamManager.createProducer(kafkaBrokers)
        val locationResolver = new LocationResolver
        eventsPart.foreach(rawPlayerEvent => {
          val eventType = rawPlayerEvent.params.get("event:eventType")
          val partnerId = rawPlayerEvent.params.get("event:partnerId")
          val entryId = rawPlayerEvent.params.get("event:entryId")
          if (eventType.isDefined && partnerId.isDefined && entryId.isDefined) {
            val eventTypeEnum = PlayerEventTypes(eventType.get)
            val playerEvent = EnrichedPlayerEvent(
              if (eventTypeEnum.equals(PlayerEventTypes.unknown)) eventType.get else eventTypeEnum.toString,
              rawPlayerEvent.eventTime,
              partnerId.get.toInt,
              entryId.get,
              rawPlayerEvent.params.getOrElse("event:flavourId",""),
              rawPlayerEvent.params.getOrElse("userId","Unknown"),
              locationResolver.parseWithProxy(rawPlayerEvent.remoteAddr, rawPlayerEvent.proxyRemoteAddr),
              UserAgentResolver.resolve(rawPlayerEvent.userAgent),
              UrlParser.getUrlParts(rawPlayerEvent.params.getOrElse("event:referrer","")),
              rawPlayerEvent.params.getOrElse("kalsig",""),
              rawPlayerEvent.params.getOrElse("categories",""),
              rawPlayerEvent.params.getOrElse("application",""),
              rawPlayerEvent.params.getOrElse("playbackContext",""),
              rawPlayerEvent.params.getOrElse("playbackType",""),
              rawPlayerEvent.params.getOrElse("customVar1",""),
              rawPlayerEvent.params.getOrElse("customVar2",""),
              rawPlayerEvent.params.getOrElse("customVar3","")
            )
            producer.send(new ProducerRecord[String,String]("enriched-player-events", playerEvent.entryId, PlayerEventParser.asJson(playerEvent)))
          }
          else { // Handle a case where partnerId or eventType are missing
            producer.send(new ProducerRecord[String,String]("erroneous-player-events", rawPlayerEvent.eventTime.toString, PlayerEventParser.asJson(rawPlayerEvent)))
          }
        })
        producer.close()
        locationResolver.close()
      })
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
