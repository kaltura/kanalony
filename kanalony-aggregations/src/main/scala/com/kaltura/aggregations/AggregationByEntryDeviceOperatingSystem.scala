package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryDeviceOperatingSystemKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByEntryDeviceOperatingSystem extends BaseAggregation[AggregationEntryDeviceOperatingSystemKey, EntryDeviceOperatingSystemRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("device","device"),
    ("operating_system", "operatingSystem"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryDeviceOperatingSystemKey = AggregationEntryDeviceOperatingSystemKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.userAgent.device.id, e.userAgent.operatingSystem.id)
  override def toRow(pair: (AggregationEntryDeviceOperatingSystemKey, Long)): EntryDeviceOperatingSystemRes = EntryDeviceOperatingSystemRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, device = pair._1.device, operatingSystem = pair._1.operatingSystem, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)


}

object HourlyAggregationByEntryDeviceOperatingSystem extends AggregationByEntryDeviceOperatingSystem with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_device_os" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_device_clst_os" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_device_os_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )
}

object MinutelyAggregationByEntryDeviceOperatingSystem extends AggregationByEntryDeviceOperatingSystem with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_device_os" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_device_clst_os" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_device_os_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))

  )
}

object TenSecsAggregationByEntryDeviceOperatingSystem extends AggregationByEntryDeviceOperatingSystem with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry_device_os" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_device_clst_os" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class EntryDeviceOperatingSystemRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, device: Int, operatingSystem: Int, value: Long)

