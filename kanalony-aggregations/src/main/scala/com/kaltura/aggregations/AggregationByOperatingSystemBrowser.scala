package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationOperatingSystemBrowserKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByOperatingSystemBrowser extends BaseAggregation[AggregationOperatingSystemBrowserKey, PartnerOperatingSystemBrowserRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("operating_system", "operatingSystem"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationOperatingSystemBrowserKey = AggregationOperatingSystemBrowserKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.userAgent.operatingSystem.id, e.userAgent.browser.id)
  override def toRow(pair: (AggregationOperatingSystemBrowserKey, Long)): PartnerOperatingSystemBrowserRes = PartnerOperatingSystemBrowserRes(partnerId = pair._1.partnerId, operatingSystem = pair._1.operatingSystem, browser = pair._1.browser, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)

}

object HourlyAggregationByOperatingSystemBrowser extends AggregationByOperatingSystemBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_os_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_os_clst_browser" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByOperatingSystemBrowser extends AggregationByOperatingSystemBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_os_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_os_clst_browser" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByOperatingSystemBrowser extends AggregationByOperatingSystemBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_os_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_os_clst_browser" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerOperatingSystemBrowserRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, operatingSystem: Int, browser: Int, value: Long)

