package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryBrowserKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByEntryBrowser extends BaseAggregation[AggregationEntryBrowserKey, EntryBrowserRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryBrowserKey = AggregationEntryBrowserKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.userAgent.browser.id)
  override def toRow(pair: (AggregationEntryBrowserKey, Long)): EntryBrowserRes = EntryBrowserRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, browser = pair._1.browser, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)


}

object HourlyAggregationByEntryBrowser extends AggregationByEntryBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_clst_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_browser_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))

  )
}

object MinutelyAggregationByEntryBrowser extends AggregationByEntryBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_clst_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_browser_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))

  )
}

object TenSecsAggregationByEntryBrowser extends AggregationByEntryBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entrybrowser" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_clst_browser" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class EntryBrowserRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, browser: Int, value: Long)

