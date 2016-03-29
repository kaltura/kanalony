package com.kaltura.aggregations


import java.io.File

import com.kaltura.aggregations._
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

    HourlyAggregationByApplication.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByApplication.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByApplication.aggregate(parsedEnrichedEvents)
    HourlyAggregationByApplicationPlaybackContext.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByApplicationPlaybackContext.aggregate(parsedEnrichedEvents)
    HourlyAggregationByBrowser.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCategory.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCategory.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCountry.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCountry.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByCountry.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCountryBrowser.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCountryBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCountryCity.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCountryCity.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByCountryCity.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCountryOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCountryOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCountryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCountryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCustomVar1.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCustomVar1.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCustomVar2.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCustomVar2.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCustomVar3.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCustomVar3.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCustomVar1CustomVar2.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCustomVar1CustomVar2.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEvents)
    HourlyAggregationByDevice.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByDevice.aggregate(parsedEnrichedEvents)
    HourlyAggregationByDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyAggregationByDomain.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByDomain.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByDomain.aggregate(parsedEnrichedEvents)
    HourlyAggregationByDomainReferrer.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByDomainReferrer.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByDomainReferrer.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntry.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntry.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByEntry.aggregate(parsedEnrichedEvents)
    HourlyAggregationByOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyAggregationByOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByPartner.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByPartner.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByPartner.aggregate(parsedEnrichedEvents)
    HourlyAggregationByPlaybackContext.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByPlaybackContext.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryApplication.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryApplication.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByEntryApplication.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryApplicationPlaybackContext.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryApplicationPlaybackContext.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryBrowser.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryCategory.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryCountry.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryCountry.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByEntryCountry.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryCountryCity.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryCountryCity.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByEntryCountryCity.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryCustomVar1.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryCustomVar1.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryCustomVar2.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryCustomVar2.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryCustomVar3.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryCustomVar3.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryCustomVar1CustomVar2.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryCustomVar1CustomVar2.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryDevice.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryDevice.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryDomain.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryDomain.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByEntryDomain.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryDomainReferrer.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryDomainReferrer.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByEntryDomainReferrer.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryPlaybackContext.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryPlaybackContext.aggregate(parsedEnrichedEvents)
    HourlyAggregationPeakAudience.aggregate(parsedEnrichedEvents)
    MinutelyAggregationPeakAudience.aggregate(parsedEnrichedEvents)
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
