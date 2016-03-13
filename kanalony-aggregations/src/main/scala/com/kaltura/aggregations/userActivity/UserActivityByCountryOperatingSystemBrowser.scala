package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityCountryOperatingSystemBrowserKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime

abstract class UserActivityByCountryOperatingSystemBrowser extends BaseUserActivityAggregation[UserActivityCountryOperatingSystemBrowserKey, PartnerCountryOperatingSystemBrowserRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("country","country"),
    ("operating_system","operatingSystem"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))
  override def aggKey(e: EnrichedPlayerEvent): UserActivityCountryOperatingSystemBrowserKey = UserActivityCountryOperatingSystemBrowserKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.location.country, e.userAgent.operatingSystem.id, e.userAgent.browser.id)
  override def toRow(pair: (UserActivityCountryOperatingSystemBrowserKey, Long)): PartnerCountryOperatingSystemBrowserRes = PartnerCountryOperatingSystemBrowserRes(partnerId = pair._1.partnerId, country = pair._1.country, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, operatingSystem = pair._1.operatingSystem, browser = pair._1.browser, value = pair._2)
}

object HourlyUserActivityByCountryOperatingSystemBrowser extends UserActivityByCountryOperatingSystemBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_country_clst_os_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_country_os_clst_browser" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCountryOperatingSystemBrowser extends UserActivityByCountryOperatingSystemBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_country_clst_os_browser" -> toSomeColumns(columns),
    "minutely_ua_prtn_country_os_clst_browser" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByCountryOperatingSystemBrowser extends UserActivityByCountryOperatingSystemBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_country_clst_os_browser" -> toSomeColumns(columns),
    "tensecs_ua_prtn_country_os_clst_browser" -> toSomeColumns(columns)
  )
}
case class PartnerCountryOperatingSystemBrowserRes(partnerId: Int, metric: Int, year: Int, time: DateTime, country: String, operatingSystem: Int, browser: Int, value: Long)

