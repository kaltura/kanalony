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
import org.clapper.classutil.ClassFinder


import org.joda.time.DateTime

import scala.reflect.ClassTag
import java.io.File
import scala.reflect.runtime.universe._

/**
 * Created by orlylampert on 1/11/16.
 */
object EventsAggregation extends App with Logging {

  case class EntryAggrKey(entryId: String, eventType: Int, minute: DateTime)

  case class EntryAggrResult(entryId: String, eventType: Int, minute: DateTime, count: Long)

  override def main(args: Array[String]) {

    setStreamingLogLevels
    //val aggregators = getAggregators()
    val applicationName = ConfigurationManager.get("kanalony.events_aggregation.application_name")
    val checkpointDirectory = s"/tmp/checkpoint/$applicationName"
    // Get StreamingContext from checkpoint data or create a new one
    val ssc = StreamingContext.getOrCreate(checkpointDirectory,
      () => {
        createSparkStreamingContext(checkpointDirectory)//, aggregators)
        //createSparkStreamingContext(checkpointDirectory)

      })

    // Start the computation
    ssc.start()
    ssc.awaitTermination()

  }

  def createSparkStreamingContext(checkpointDirectory: String, aggregators: List[(String, String)] = List()): StreamingContext = {

    val sparkConf = new SparkConf().
      setAppName(ConfigurationManager.get("kanalony.events_aggregation.application_name")).
      setMaster(ConfigurationManager.getOrElse("kanalony.events_aggregations.master", "local[8]")).
      set("spark.cassandra.connection.host", ConfigurationManager.getOrElse("kanalony.events_aggregations.cassandra_host", "localhost"))
    val sparkContext = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sparkContext, Seconds(ConfigurationManager.getOrElse("kanalony.events_aggregations.batch_duration", "1").toInt))
    ssc.checkpoint(checkpointDirectory)

    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_aggregations.kafka_brokers", "127.0.0.1:9092")
    val topics = Set("enriched-player-events")
    val stream = StreamManager.createStream(ssc, kafkaBrokers, topics)

    val parsedEnrichedEvents = stream.
      map(_._2).
      flatMap(PlayerEventParser.parseEnhancedPlayerEvent)

    //parsedEnrichedEvents.print()
    // filter events by time and remove old events
    /*val parsedEnrichedEventsByMinute = parsedEnrichedEvents.filter(
      event => event.eventTime.plusMinutes(ConfigurationManager.getOrElse("kanalony.events_aggregations.minutes_to_save", "5").toInt).isAfterNow)

    val parsedEnrichedEventsByHour = parsedEnrichedEvents.filter(
      event => event.eventTime.plusHours(ConfigurationManager.getOrElse("kanalony.events_aggregations.hours_to_save", "1").toInt)
        .plusMinutes(ConfigurationManager.getOrElse("kanalony.events_aggregations.minutes_to_save", "5").toInt).isAfterNow)
*/
    /*
    aggregators.foreach(x => x match {
      case (a, "Hourly") => aggregate(a, parsedEnrichedEventsByHour)
      case (a, "Minutely") => aggregate(a, parsedEnrichedEventsByMinute)
    })
    */
    HourlyUserActivity.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByEntry.aggregate(parsedEnrichedEvents)
    HourlyUserActivityByCountryOperatingSystemBrowser.aggregate(parsedEnrichedEvents)
    //HourlyUserActivityByBrowser.aggregate(parsedEnrichedEventsByHour)

    /*
    HourlyUserActivityByEntry.aggregate(parsedEnrichedEventsByHour)
    MinutelyUserActivityByEntry.aggregate(parsedEnrichedEventsByMinute)
    MinutelyUserActivity.aggregate(parsedEnrichedEventsByMinute)
    HourlyUserActivityPeakAudience.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivity.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivityByBrowser.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivityByCountry.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivityByCountryCity.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivityByCountryOperatingSystemBrowser.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivityByDevice.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivityByDeviceOperatingSystem.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivityByDomain.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivityByDomainReferrer.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivityByOperatingSystem.aggregate(parsedEnrichedEventsByHour)
    HourlyUserActivityByOperatingSystemBrowser.aggregate(parsedEnrichedEventsByHour)
    **/

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

  def getAggregators() : List[(String, String)] = {
    val path = Seq("../").map(new File(_));
    val finder = ClassFinder(path)
    val classes = finder.getClasses

    val aggregators = ClassFinder.concreteSubclasses("com.kaltura.aggregations.IAggregate", classes.iterator)

    aggregators.map(cls => if (cls.implements("com.kaltura.aggregations.IAggregateHourly")) (cls.name, "Hourly") else (cls.name, "Minutely")).toList


  }
/*
  def aggregate(className: String, events: DStream[EnrichedPlayerEvent]) : Unit = {
    val mirror = runtimeMirror(getClass.getClassLoader)
    val module = mirror.staticModule(className)

    val cls = mirror.reflectModule(module).instance.asInstanceOf[IAggregate]
    val im =mirror.reflect(cls)
    val method = im.symbol.typeSignature.member(TermName("aggregate")).asMethod
    im.reflectMethod(method)(events)
  }*/

}
