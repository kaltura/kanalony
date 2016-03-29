package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationCountryOperatingSystemBrowserKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByCountryOperatingSystemBrowser extends BaseAggregation[AggregationCountryOperatingSystemBrowserKey, PartnerCountryOperatingSystemBrowserRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("country","country"),
    ("operating_system","operatingSystem"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))
  override def aggKey(e: EnrichedPlayerEvent): AggregationCountryOperatingSystemBrowserKey = AggregationCountryOperatingSystemBrowserKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.location.country, e.userAgent.operatingSystem.id, e.userAgent.browser.id)
  override def toRow(pair: (AggregationCountryOperatingSystemBrowserKey, Long)): PartnerCountryOperatingSystemBrowserRes = PartnerCountryOperatingSystemBrowserRes(partnerId = pair._1.partnerId, country = pair._1.country, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, operatingSystem = pair._1.operatingSystem, browser = pair._1.browser, value = pair._2)
}

object HourlyAggregationByCountryOperatingSystemBrowser extends AggregationByCountryOperatingSystemBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_country_clst_os_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_country_os_clst_browser" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByCountryOperatingSystemBrowser extends AggregationByCountryOperatingSystemBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_country_clst_os_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_country_os_clst_browser" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByCountryOperatingSystemBrowser extends AggregationByCountryOperatingSystemBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_country_clst_os_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_country_os_clst_browser" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class PartnerCountryOperatingSystemBrowserRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, country: String, operatingSystem: Int, browser: Int, value: Long)

