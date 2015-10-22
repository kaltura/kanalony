package com.kaltura.enhancement

import com.kaltura.core.ip2location.LocationResolver
import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.urls.UrlParser
import com.kaltura.core.userAgent.UserAgentResolver
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.{RawPlayerEvent, PlayerEventParser, PlayerEvent}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.dstream.DStream
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
    val ssc = new StreamingContext(sparkContext, Seconds(ConfigurationManager.getOrElse("kanalony.events_enhancer.batch_duration","5").toInt))
    ssc.checkpoint(checkpointDirectory)

    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_enhancer.kafka_brokers","127.0.0.1:9092")
    val topics = Set("player-events")
    val stream = StreamManager.createStream(ssc, kafkaBrokers, topics)

    val parsedEvents = stream.
                        map(_._2).
                        flatMap(PlayerEventParser.parsePlayerEvent)



    enhanceEvents(parsedEvents)

    ssc
  }

  def enhanceEvents(playerEvents:DStream[RawPlayerEvent]):Unit = {

    playerEvents.foreachRDD( rdd => {
      rdd.foreachPartition( eventsPart => {
        val locationResolver = new LocationResolver
        val KS
        eventsPart.foreach(rawPlayerEvent => {
          val playerEvent = PlayerEvent(
            rawPlayerEvent.eventTime,
            rawPlayerEvent.params.getOrElse("event:partnerId","-1").toInt,
            rawPlayerEvent.params.getOrElse("event:entryId","-1"),
            rawPlayerEvent.params.getOrElse("ks",""),
            locationResolver.parse(rawPlayerEvent.remoteIp),
            UserAgentResolver.resolve(rawPlayerEvent.userAgent),
            UrlParser.getUrlParts(rawPlayerEvent.referrer)
          )
          println(playerEvent)
        })
        // produce events
        locationResolver.close
      })
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
