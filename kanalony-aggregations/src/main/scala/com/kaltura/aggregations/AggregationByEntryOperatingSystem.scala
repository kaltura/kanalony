package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryOperatingSystemKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByEntryOperatingSystem extends BaseAggregation[AggregationEntryOperatingSystemKey, EntryOperatingSystemRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("operating_system","operatingSystem"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryOperatingSystemKey = AggregationEntryOperatingSystemKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.userAgent.device.id)
  override def toRow(pair: (AggregationEntryOperatingSystemKey, Long)): EntryOperatingSystemRes = EntryOperatingSystemRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, operatingSystem = pair._1.operatingSystem, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)

}

object HourlyAggregationByEntryOperatingSystem extends AggregationByEntryOperatingSystem with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_os" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_clst_os" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_os_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )
}

object MinutelyAggregationByEntryOperatingSystem extends AggregationByEntryOperatingSystem with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_os" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_clst_os" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_os_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))

  )
}

object TenSecsAggregationByEntryOperatingSystem extends AggregationByEntryOperatingSystem with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry_os" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_clst_os" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class EntryOperatingSystemRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, operatingSystem: Int, value: Long)

