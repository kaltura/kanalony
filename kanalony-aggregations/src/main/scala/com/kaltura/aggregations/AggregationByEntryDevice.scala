package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryDeviceKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByEntryDevice extends BaseAggregation[AggregationEntryDeviceKey, EntryDeviceRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("device","device"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryDeviceKey = AggregationEntryDeviceKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.userAgent.device.id)
  override def toRow(pair: (AggregationEntryDeviceKey, Long)): EntryDeviceRes = EntryDeviceRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, device = pair._1.device, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)


}

object HourlyAggregationByEntryDevice extends AggregationByEntryDevice with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_device" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_clst_device" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_device_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )
}

object MinutelyAggregationByEntryDevice extends AggregationByEntryDevice with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_device" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_clst_device" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_device_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))

  )
}

object TenSecsAggregationByEntryDevice extends AggregationByEntryDevice with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry_device" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_clst_device" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class EntryDeviceRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, device: Int, value: Long)

