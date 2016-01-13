package com.kaltura.enhancement

import com.kaltura.core.ip2location.LocationResolver
import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.urls.UrlParser
import com.kaltura.core.userAgent.UserAgentResolver
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.{RawPlayerEvent, PlayerEventParser, EnrichedPlayerEvent}
import kafka.producer.KeyedMessage
import kafka.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.kafka.{HasOffsetRanges, OffsetRange}
import org.apache.spark.{SparkContext, SparkConf, Logging}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object EventsEnhancer extends App with Logging {

  override def main(args: Array[String]) {

    setStreamingLogLevels
    val applicationName = ConfigurationManager.get("kanalony.events_enhancer.application_name")
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
      setAppName(ConfigurationManager.get("kanalony.events_enhancer.application_name")).
      setMaster(ConfigurationManager.getOrElse("kanalony.events_enhancer.master","local[8]")).
      set("spark.cassandra.connection.host", ConfigurationManager.getOrElse("kanalony.events_enhancer.cassandra_host","127.0.0.1"))
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
        enhanceEvents(rdd)
        for (o <- offsetRanges) {
          println(s"${o.topic} ${o.partition} ${o.fromOffset} ${o.untilOffset}")
        }
      }

    ssc
  }



  def enhanceEvents(playerEvents:RDD[RawPlayerEvent]):Unit = {
      playerEvents.foreachPartition( eventsPart => {
        val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_enhancer.kafka_brokers","127.0.0.1:9092")
        val producer = StreamManager.createProducer(kafkaBrokers)
        val locationResolver = new LocationResolver
        eventsPart.foreach(rawPlayerEvent => {
          val playerEvent = EnrichedPlayerEvent(
            rawPlayerEvent.params.getOrElse("event:eventType","-1").toInt,
            rawPlayerEvent.eventTime,
            rawPlayerEvent.params.getOrElse("event:partnerId","-1").toInt,
            rawPlayerEvent.params.getOrElse("event:entryId",""),
            rawPlayerEvent.params.getOrElse("event:flavourId",""),
            rawPlayerEvent.params.getOrElse("ks",""),
            locationResolver.parse(rawPlayerEvent.remoteIp),
            UserAgentResolver.resolve(rawPlayerEvent.userAgent),
            UrlParser.getUrlParts(rawPlayerEvent.params.getOrElse("event:referrer","")),
            rawPlayerEvent.params.getOrElse("kalsig","")
          )
          producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent)))
          logWarning(playerEvent.toString)
        })
        producer.close()
        locationResolver.close
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
