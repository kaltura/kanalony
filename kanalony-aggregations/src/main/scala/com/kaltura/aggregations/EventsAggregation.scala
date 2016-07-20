package com.kaltura.aggregations

import com.esotericsoftware.kryo.Kryo
import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.PlayerEventParser
import de.javakaffee.kryoserializers.jodatime.JodaDateTimeSerializer
import org.apache.log4j.{Level, Logger}
import org.apache.spark.serializer.KryoRegistrator
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{Logging, SparkConf, SparkContext}
import org.joda.time.DateTime

object EventsAggregation extends App with Logging {

  case class EntryAggrKey(entryId: String, eventType: Int, minute: DateTime)

  case class EntryAggrResult(entryId: String, eventType: Int, minute: DateTime, count: Long)

  override def main(args: Array[String]) {

    setStreamingLogLevels
    val (enabledAggregations, applicationSuffix) = if (args.isEmpty) ("","") else (args(0),args(1))
    //val (enabledAggregations, applicationSuffix) = if (args.isEmpty) ("MinutelyAggregationByEntryOperatingSystemBrowser,HourlyAggregationByCountryOperatingSystem","0") else (args(0),args(1))
    val applicationName = ConfigurationManager.get("kanalony.events_aggregations.application_name") + applicationSuffix
    val checkpointRootPath = ConfigurationManager.getOrElse("kanalony.checkpoint_root_path","/tmp/checkpoint")
    val checkpointDirectory = s"$checkpointRootPath/$applicationName"

    ConfigurationManager.set("kanalony.events_aggregations.enabled_aggregations",enabledAggregations)
    // Get StreamingContext from checkpoint data or create a new one
    val ssc = StreamingContext.getOrCreate(checkpointDirectory,
      () => {
        createSparkStreamingContext(checkpointDirectory)

      })

    // Start the computation
    ssc.start()
    ssc.awaitTermination()

  }

  def createSparkStreamingContext(checkpointDirectory: String, aggregators: List[(String)] = List()): StreamingContext = {

    val sparkConf = new SparkConf()
//      .setAppName(ConfigurationManager.get("kanalony.events_aggregations.application_name"))
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
    val topics = Set("enriched-player-events")
    val stream = StreamManager.createStream(ssc, kafkaBrokers, topics).checkpoint(Seconds(ConfigurationManager.getOrElse("kanalony.events_aggregations.checkpoint_interval_sec", "30").toInt))

    val parsedEnrichedEvents = stream.
      flatMap(ev => PlayerEventParser.parseEnhancedPlayerEvent(ev._2))
    parsedEnrichedEvents.cache()

    // 2600 events/sec
    /**
     * 0. HourlyAggregationByApplication,HourlyAggregationByApplicationPlaybackContext,HourlyAggregationByBrowser,HourlyAggregationByCountry,HourlyAggregationByCountryBrowser,HourlyAggregationByCountryCity,HourlyAggregationByCountryOperatingSystem,HourlyAggregationByCountryOperatingSystemBrowser,HourlyAggregationByDevice,HourlyAggregationByDeviceOperatingSystem
     */
    HourlyAggregationByApplication.aggregate(parsedEnrichedEvents)
    HourlyAggregationByBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCountry.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCountryBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCountryCity.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCountryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByDevice.aggregate(parsedEnrichedEvents)
    HourlyAggregationByDeviceOperatingSystem.aggregate(parsedEnrichedEvents)

    /**
     * 1. HourlyAggregationByOperatingSystem,HourlyAggregationByOperatingSystemBrowser,HourlyAggregationByPartner,HourlyAggregationByPlaybackContext,HourlyAggregationPeakAudience,HourlyAggregationByDomain,HourlyAggregationByDomainReferrer,HourlyAggregationByEntry,HourlyAggregationByEntryApplication,HourlyAggregationByEntryApplicationPlaybackContext
     */

    HourlyAggregationByOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyAggregationByOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByPartner.aggregate(parsedEnrichedEvents)

    HourlyAggregationPeakAudience.aggregate(parsedEnrichedEvents)
    HourlyAggregationByDomain.aggregate(parsedEnrichedEvents)
    HourlyAggregationByDomainReferrer.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntry.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryApplication.aggregate(parsedEnrichedEvents)


    /**
     * 2. HourlyAggregationByEntryBrowser,HourlyAggregationByEntryCountry,HourlyAggregationByEntryCountryCity,HourlyAggregationByEntryDevice,HourlyAggregationByEntryDeviceOperatingSystem,HourlyAggregationByEntryDomain,HourlyAggregationByEntryDomainReferrer,HourlyAggregationByEntryOperatingSystem,HourlyAggregationByEntryOperatingSystemBrowser,HourlyAggregationByEntryPlaybackContext
     */

    HourlyAggregationByEntryBrowser.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryCountry.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryCountryCity.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryDevice.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryDomain.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryDomainReferrer.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryOperatingSystem.aggregate(parsedEnrichedEvents)
    HourlyAggregationByEntryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)

    // 1600 events/s, 20 Cores, 8 Concurrent
    /**
     * 3. MinutelyAggregationByEntry,MinutelyAggregationByEntryBrowser,MinutelyAggregationByEntryCountry,MinutelyAggregationByEntryCountryCity,MinutelyAggregationByEntryDevice,MinutelyAggregationByEntryDeviceOperatingSystem,MinutelyAggregationByEntryDomain,MinutelyAggregationByEntryOperatingSystem,MinutelyAggregationByEntryOperatingSystemBrowser
     */
    MinutelyAggregationByEntry.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryBrowser.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryCountry.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryCountryCity.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryDevice.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryDomain.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByEntryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)

    /**
     * Custom by Entry:
     * 4. MinutelyAggregationByEntryCustomVar1,MinutelyAggregationByEntryCustomVar1CustomVar2,MinutelyAggregationByEntryCustomVar1CustomVar2CustomVar3,MinutelyAggregationByEntryCustomVar2,MinutelyAggregationByEntryCustomVar3,HourlyAggregationByntryCustomVar1,HourlyAggregationByEntryCustomVar1CustomVar2,HourlyAggregationByEntryCustomVar1CustomVar2CustomVar3,HourlyAggregationByEntryCustomVar2,HourlyAggregationByEntryCustomVar3
     */
    val parsedEnrichedEventsWithCustomVars = parsedEnrichedEvents.filter(event => event.customVar1.nonEmpty || event.customVar2.nonEmpty || event.customVar3.nonEmpty)
    MinutelyAggregationByEntryCustomVar1.aggregate(parsedEnrichedEventsWithCustomVars)
    MinutelyAggregationByEntryCustomVar1CustomVar2.aggregate(parsedEnrichedEventsWithCustomVars)
    MinutelyAggregationByEntryCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEventsWithCustomVars)
    MinutelyAggregationByEntryCustomVar2.aggregate(parsedEnrichedEventsWithCustomVars)
    MinutelyAggregationByEntryCustomVar3.aggregate(parsedEnrichedEventsWithCustomVars)
    HourlyAggregationByEntryCustomVar1.aggregate(parsedEnrichedEventsWithCustomVars)
    HourlyAggregationByEntryCustomVar1CustomVar2.aggregate(parsedEnrichedEventsWithCustomVars)
    HourlyAggregationByEntryCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEventsWithCustomVars)
    HourlyAggregationByEntryCustomVar2.aggregate(parsedEnrichedEventsWithCustomVars)
    HourlyAggregationByEntryCustomVar3.aggregate(parsedEnrichedEventsWithCustomVars)

    /**
     * Custom
     * 5. HourlyAggregationByCustomVar1,HourlyAggregationByCustomVar1CustomVar2,HourlyAggregationByCustomVar1CustomVar2CustomVar3,HourlyAggregationByCustomVar2,HourlyAggregationByCustomVar3,MinutelyAggregationByCustomVar1,MinutelyAggregationByCustomVar1CustomVar2,MinutelyAggregationByCustomVar1CustomVar2CustomVar3,MinutelyAggregationByCustomVar2,MinutelyAggregationByCustomVar3
     */
    HourlyAggregationByCustomVar1.aggregate(parsedEnrichedEventsWithCustomVars)
    HourlyAggregationByCustomVar1CustomVar2.aggregate(parsedEnrichedEventsWithCustomVars)
    HourlyAggregationByCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEventsWithCustomVars)
    HourlyAggregationByCustomVar2.aggregate(parsedEnrichedEventsWithCustomVars)
    HourlyAggregationByCustomVar3.aggregate(parsedEnrichedEventsWithCustomVars)
    MinutelyAggregationByCustomVar1.aggregate(parsedEnrichedEventsWithCustomVars)
    MinutelyAggregationByCustomVar1CustomVar2.aggregate(parsedEnrichedEventsWithCustomVars)
    MinutelyAggregationByCustomVar1CustomVar2CustomVar3.aggregate(parsedEnrichedEventsWithCustomVars)
    MinutelyAggregationByCustomVar2.aggregate(parsedEnrichedEventsWithCustomVars)
    MinutelyAggregationByCustomVar3.aggregate(parsedEnrichedEventsWithCustomVars)



    /**
     * 6. MinutelyAggregationByPartner,MinutelyAggregationByCountry,MinutelyAggregationByCountryBrowser,MinutelyAggregationByCountryCity,MinutelyAggregationByCountryOperatingSystem,MinutelyAggregationByCountryOperatingSystemBrowser
     */
    MinutelyAggregationByPartner.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCountry.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCountryBrowser.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCountryCity.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCountryOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByCountryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)

    /**
     * 7. MinutelyAggregationByBrowser,MinutelyAggregationByDevice,MinutelyAggregationByDeviceOperatingSystem,MinutelyAggregationByDomain,MinutelyAggregationByOperatingSystem,MinutelyAggregationByOperatingSystemBrowser,MinutelyAggregationPeakAudience
     */
    MinutelyAggregationByBrowser.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByDevice.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByDeviceOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByDomain.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByOperatingSystem.aggregate(parsedEnrichedEvents)
    MinutelyAggregationByOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    //MinutelyAggregationPeakAudience.aggregate(parsedEnrichedEvents)

    /**
     * 8. TenSecsAggregationByCountry,TenSecsAggregationByCountryCity,TenSecsAggregationByDomain,TenSecsAggregationByPartner,TenSecsAggregationByEntry,TenSecsAggregationByEntryCountry,TenSecsAggregationByEntryCountryCity,TenSecsAggregationByEntryDomain
     */
    TenSecsAggregationByCountry.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByCountryCity.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByDomain.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByPartner.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByEntry.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByEntryCountry.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByEntryCountryCity.aggregate(parsedEnrichedEvents)
    TenSecsAggregationByEntryDomain.aggregate(parsedEnrichedEvents)


    // MinutelyAggregationByEntryDomainReferrer.aggregate(parsedEnrichedEvents)
    // TenSecsAggregationByEntryDomainReferrer.aggregate(parsedEnrichedEvents)
    // MinutelyAggregationByDomainReferrer.aggregate(parsedEnrichedEvents)
    // TenSecsAggregationByDomainReferrer.aggregate(parsedEnrichedEvents)

    /**
     * 9. MinutelyAggregationByEntryApplication,TenSecsAggregationByEntryApplication,TenSecsAggregationByApplication,MinutelyAggregationByApplication,MinutelyAggregationByApplicationPlaybackContext,MinutelyAggregationByEntryApplicationPlaybackContext,MinutelyAggregationByEntryPlaybackContext,MinutelyAggregationByPlaybackContext,MinutelyAggregationByCategory,HourlyAggregationByEntryCategory,HourlyAggregationByCategory
     */
    val parsedEnrichedEventsWithApplication = parsedEnrichedEvents.filter(event => event.application.nonEmpty)
    MinutelyAggregationByEntryApplication.aggregate(parsedEnrichedEventsWithApplication)
    TenSecsAggregationByEntryApplication.aggregate(parsedEnrichedEventsWithApplication)
    TenSecsAggregationByApplication.aggregate(parsedEnrichedEventsWithApplication)
    MinutelyAggregationByApplication.aggregate(parsedEnrichedEventsWithApplication)

    val parsedEnrichedEventsWithPlaybackContext = parsedEnrichedEvents.filter(event => event.playbackContext.nonEmpty)
    MinutelyAggregationByApplicationPlaybackContext.aggregate(parsedEnrichedEventsWithPlaybackContext)
    MinutelyAggregationByEntryApplicationPlaybackContext.aggregate(parsedEnrichedEventsWithPlaybackContext)
    MinutelyAggregationByEntryPlaybackContext.aggregate(parsedEnrichedEventsWithPlaybackContext)
    MinutelyAggregationByPlaybackContext.aggregate(parsedEnrichedEventsWithPlaybackContext)
    HourlyAggregationByEntryPlaybackContext.aggregate(parsedEnrichedEventsWithPlaybackContext)
    HourlyAggregationByEntryApplicationPlaybackContext.aggregate(parsedEnrichedEventsWithPlaybackContext)
    HourlyAggregationByPlaybackContext.aggregate(parsedEnrichedEventsWithPlaybackContext)
    HourlyAggregationByApplicationPlaybackContext.aggregate(parsedEnrichedEventsWithPlaybackContext)

    val parsedEnrichedEventsWithCategory = parsedEnrichedEvents.filter(event => event.categories.nonEmpty)
    MinutelyAggregationByCategory.aggregate(parsedEnrichedEventsWithCategory)
    HourlyAggregationByEntryCategory.aggregate(parsedEnrichedEvents)
    HourlyAggregationByCategory.aggregate(parsedEnrichedEvents)

    /** Keep this agg last, or else your app will be stuck after a few batches **/
    HourlyAggregationByCountryOperatingSystem.aggregate(parsedEnrichedEvents)

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

class CustomKryoRegistrator extends KryoRegistrator {
  override def registerClasses(kryo: Kryo) {
    kryo.register(classOf[org.joda.time.DateTime], new JodaDateTimeSerializer)
  }
}
