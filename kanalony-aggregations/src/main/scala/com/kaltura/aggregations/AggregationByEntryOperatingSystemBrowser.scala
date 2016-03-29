package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryOperatingSystemBrowserKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByEntryOperatingSystemBrowser extends BaseAggregation[AggregationEntryOperatingSystemBrowserKey, EntryOperatingSystemBrowserRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("operating_system", "operatingSystem"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryOperatingSystemBrowserKey = AggregationEntryOperatingSystemBrowserKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.userAgent.operatingSystem.id, e.userAgent.browser.id)
  override def toRow(pair: (AggregationEntryOperatingSystemBrowserKey, Long)): EntryOperatingSystemBrowserRes = EntryOperatingSystemBrowserRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, operatingSystem = pair._1.operatingSystem, browser = pair._1.browser, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)

}

object HourlyAggregationByEntryOperatingSystemBrowser extends AggregationByEntryOperatingSystemBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_os_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_os_clst_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_os_browser_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )
}

object MinutelyAggregationByEntryOperatingSystemBrowser extends AggregationByEntryOperatingSystemBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_os_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_os_clst_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_os_browser_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByEntryOperatingSystemBrowser extends AggregationByEntryOperatingSystemBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry_os_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_os_clst_browser" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class EntryOperatingSystemBrowserRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, operatingSystem: Int, browser: Int, value: Long)

