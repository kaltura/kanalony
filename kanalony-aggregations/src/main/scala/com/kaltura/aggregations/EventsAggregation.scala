package com.kaltura.aggregations


import java.io.File

import com.kaltura.aggregations.userActivity._
import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.{EnrichedPlayerEvent, PlayerEventParser}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{Logging, SparkConf, SparkContext}
import org.clapper.classutil.ClassFinder
import org.joda.time.DateTime

import java.io.File
import scala.reflect.runtime.universe._

object EventsAggregation extends App with Logging {

  case class EntryAggrKey(entryId: String, eventType: Int, minute: DateTime)

  case class EntryAggrResult(entryId: String, eventType: Int, minute: DateTime, count: Long)

  override def main(args: Array[String]) {

    setStreamingLogLevels
    //val aggregators = getAggregators()
    val applicationName = ConfigurationManager.get("kanalony.events_aggregation.application_name")
    val checkpointRootPath = ConfigurationManager.getOrElse("kanalony.checkpoint_root_path","/tmp/checkpoint")
    val checkpointDirectory = s"$checkpointRootPath/$applicationName"
    // Get StreamingContext from checkpoint data or create a new one
    val ssc = StreamingContext.getOrCreate(checkpointDirectory,
      () => {
        createSparkStreamingContext(checkpointDirectory)//, aggregators)

      })

    // Start the computation
    ssc.start()
    ssc.awaitTermination()

  }

  def createSparkStreamingContext(checkpointDirectory: String, aggregators: List[(String)] = List()): StreamingContext = {

    val sparkConf = new SparkConf()
      .setAppName(ConfigurationManager.get("kanalony.events_aggregation.application_name"))
      .setMaster(ConfigurationManager.getOrElse("kanalony.events_aggregations.master", "local[8]"))
      .set("spark.cassandra.connection.host", ConfigurationManager.getOrElse("kanalony.events_aggregations.cassandra_host", "localhost"))
      .set("spark.cassandra.connection.keep_alive_ms","30000")
    val sparkContext = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sparkContext, Seconds(ConfigurationManager.getOrElse("kanalony.events_aggregations.batch_duration", "1").toInt))
    ssc.checkpoint(checkpointDirectory)

    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_aggregations.kafka_brokers", "127.0.0.1:9092")
    val topics = Set("enriched-player-events")
    val stream = StreamManager.createStream(ssc, kafkaBrokers, topics)

    val parsedEnrichedEvents = stream.
      map(_._2).
      flatMap(PlayerEventParser.parseEnhancedPlayerEvent)

    //aggregators.foreach(aggregate(_, parsedEnrichedEvents))

    HourlyUserActivityByApplication.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByApplication.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByApplication.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByApplicationPlaybackContext.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByApplicationPlaybackContext.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByBrowser.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByBrowser.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCategory.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCategory.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCountry.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCountry.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByCountry.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCountryBrowser.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCountryBrowser.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCountryCity.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCountryCity.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByCountryCity.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCountryOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCountryOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCountryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCountryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCustomVar1.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCustomVar1.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCustomVar2.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCustomVar2.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCustomVar3.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCustomVar3.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCustomVar1CustomVar2.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCustomVar1CustomVar2.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByDevice.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByDevice.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByDomain.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByDomain.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByDomain.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByDomainReferrer.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByDomainReferrer.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByDomainReferrer.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntry.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntry.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByEntry.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByPlaybackContext.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByPlaybackContext.aggregate(parsedEnrichedEvents)

    HourlyUserActivityByEntryApplication.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryApplication.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByEntryApplication.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryApplicationPlaybackContext.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryApplicationPlaybackContext.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryBrowser.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryBrowser.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryCategory.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryCountry.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryCountry.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByEntryCountry.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryCountryCity.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryCountryCity.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByEntryCountryCity.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryCustomVar1.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryCustomVar1.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryCustomVar2.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryCustomVar2.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryCustomVar3.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryCustomVar3.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryCustomVar1CustomVar2.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryCustomVar1CustomVar2.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryDevice.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryDevice.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryDomain.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryDomain.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByEntryDomain.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryDomainReferrer.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryDomainReferrer.aggregate(parsedEnrichedEvents)
    TenSecsUserActivityByEntryDomainReferrer.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntryPlaybackContext.aggregate(parsedEnrichedEvents)
    MinutelyUserActivityByEntryPlaybackContext.aggregate(parsedEnrichedEvents)
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

  def getAggregators() : List[(String)] = {
    val path = Seq("../").map(new File(_));
    val finder = ClassFinder()
    val classes = finder.getClasses

    val aggregators = ClassFinder.concreteSubclasses("com.kaltura.aggregations.IAggregate", classes.iterator)

    aggregators.map(cls => cls.name).toList


  }

  def aggregate(className: String, events: DStream[EnrichedPlayerEvent]) : Unit = {
    val mirror = runtimeMirror(getClass.getClassLoader)
    val module = mirror.staticModule(className)

    val cls = mirror.reflectModule(module).instance.asInstanceOf[IAggregate]
    val im =mirror.reflect(cls)
    val method = im.symbol.typeSignature.member(TermName("aggregate")).asMethod
    im.reflectMethod(method)(events)
  }

}
