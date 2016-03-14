package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityEntryDeviceKey, UserActivityDeviceKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByEntryDevice extends BaseUserActivityAggregation[UserActivityEntryDeviceKey, EntryDeviceRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("device","device"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryDeviceKey = UserActivityEntryDeviceKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.userAgent.device.id)
  override def toRow(pair: (UserActivityEntryDeviceKey, Long)): EntryDeviceRes = EntryDeviceRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, device = pair._1.device, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)


}

object HourlyUserActivityByEntryDevice extends UserActivityByEntryDevice with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_device" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_device" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByEntryDevice extends UserActivityByEntryDevice with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_device" -> toSomeColumns(columns),
    "minutely_ua_clst_device" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByEntryDevice extends UserActivityByEntryDevice with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_device" -> toSomeColumns(columns),
    "tensecs_ua_clst_device" -> toSomeColumns(columns)
  )
}
case class EntryDeviceRes(partnerId: Int, entryId: String, metric: Int, year: Int, time: DateTime, device: Int, value: Long)

