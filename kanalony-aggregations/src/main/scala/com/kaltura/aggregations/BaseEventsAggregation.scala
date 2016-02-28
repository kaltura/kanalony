package com.kaltura.aggregations

import com.datastax.spark.connector._
import com.kaltura.core.userAgent.enums.{Browser, Device, OperatingSystem}
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.joda.time.DateTime

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._



abstract class BaseEventsAggregation[EventType:ClassTag, AggKey:ClassTag, AggRes:TypeTag :ClassTag] extends Serializable with IAggregate {

  val tableMetadata: Map[String, SomeColumns]
  val keyspace = "kanalony_user_activity"

  def aggregateBatchEvents(enrichedEvents: DStream[EventType]) : DStream[(AggKey,Long)] =
    enrichedEvents.map(e => (aggKey(e),1L)).reduceByKey(_ + _)

  def aggKey(e: EventType): AggKey

  def trackStateFunc(batchTime: Time, key: AggKey, value: Option[Long], state: State[Long]): Option[(AggKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  def prepareForSave(aggregatedEvents: MapWithStateDStream[AggKey, Long, Long, (AggKey, Long)]) : DStream[AggRes] =
    aggregatedEvents.map(agg => toRow(agg))


  def toRow(pair:(AggKey,Long)): AggRes

  val stateSpec = StateSpec.function(trackStateFunc _).timeout(Seconds(ttl))

  def save(aggregatedEvents: DStream[AggRes]) : Unit = {
    try {
      tableMetadata.foreach {
        case (tableName, columns) => aggregatedEvents.foreachRDD(rdd => if (rdd.count() > 0) rdd.saveToCassandra(keyspace, tableName, columns))
      }
    } catch {
      case ex: IllegalArgumentException => println("Failed to ")
    }
  }


  def aggregate(enrichedEvents: DStream[EventType]) : Unit = {
    try {
      val aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
      val aggregatedEvents = aggregatedBatchEvents.mapWithState[Long,(AggKey, Long)](stateSpec)
      save(prepareForSave(aggregatedEvents));

    } catch {
      case e: IllegalArgumentException => println("Failed to update state");
    }
  }

}

case class EntryKey (partnerId: Int, entryId: String, metric: Int, time: DateTime) extends Serializable

case class UserActivityKey(partnerId: Int, metric: Int, time: DateTime) extends Serializable
case class UserActivityCountryKey(partnerId: Int, metric: Int, time: DateTime, country: String) extends Serializable
case class UserActivityCountryCityKey(partnerId: Int, metric: Int, time: DateTime, country: String, city: String) extends Serializable
case class UserActivityDomainKey(partnerId: Int, metric: Int, time: DateTime, domain: String) extends Serializable
case class UserActivityDomainReferrerKey(partnerId: Int, metric: Int, time: DateTime, domain: String, referrer: String) extends Serializable
case class UserActivityDeviceKey(partnerId: Int, metric: Int, time: DateTime, device: Device.Value) extends Serializable
case class UserActivityOperatingSystemKey(partnerId: Int, metric: Int, time: DateTime, operatingSystem: OperatingSystem.Value) extends Serializable
case class UserActivityBrowserKey(partnerId: Int, metric: Int, time: DateTime, browser: Browser.Value) extends Serializable
case class UserActivityDeviceOperatingSystemKey(partnerId: Int, metric: Int, time: DateTime, device: Device.Value, operatingSystem: OperatingSystem.Value) extends Serializable
case class UserActivityOperatingSystemBrowserKey(partnerId: Int, metric: Int, time: DateTime, operatingSystem: OperatingSystem.Value, browser: Browser.Value) extends Serializable
case class UserActivityCountryOperatingSystemBrowserKey(partnerId: Int, metric: Int, time: DateTime, country: String, operatingSystem: OperatingSystem.Value, browser: Browser.Value) extends Serializable


