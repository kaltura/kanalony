package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityEntryOperatingSystemKey, UserActivityOperatingSystemKey, UserActivityDeviceKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByEntryOperatingSystem extends BaseUserActivityAggregation[UserActivityEntryOperatingSystemKey, EntryOperatingSystemRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("operating_system","operatingSystem"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryOperatingSystemKey = UserActivityEntryOperatingSystemKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.userAgent.device.id)
  override def toRow(pair: (UserActivityEntryOperatingSystemKey, Long)): EntryOperatingSystemRes = EntryOperatingSystemRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, operatingSystem = pair._1.operatingSystem, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)

}

object HourlyUserActivityByEntryOperatingSystem extends UserActivityByEntryOperatingSystem with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_os" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_clst_os" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByEntryOperatingSystem extends UserActivityByEntryOperatingSystem with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_os" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_clst_os" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByEntryOperatingSystem extends UserActivityByEntryOperatingSystem with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_os" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_clst_os" -> toSomeColumns(columns)
  )
}
case class EntryOperatingSystemRes(partnerId: Int, entryId: String, metric: Int, year: Int, time: DateTime, operatingSystem: Int, value: Long)

