package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{HourlyPartnerCountryOperatingSystemBrowser, HourlyPartnerOperatingSystemBrowser}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object HourlyUserActivityByCountryOperatingSystemBrowser extends BaseEventsAggregation[UserActivityCountryOperatingSystemBrowserKey, HourlyPartnerCountryOperatingSystemBrowser] with IAggregateHourly  with Serializable{

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_country_clst_os_browser" -> columns,
    "hourly_ua_prtn_country_os_browser" -> columns
  )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "country" as "country",
    "operating_system" as "operatingSystem",
    "browser" as "browser",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityCountryOperatingSystemBrowserKey = UserActivityCountryOperatingSystemBrowserKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.location.country, e.userAgent.operatingSystem, e.userAgent.browser)
  override def toRow(pair: (UserActivityCountryOperatingSystemBrowserKey, Long)): HourlyPartnerCountryOperatingSystemBrowser = HourlyPartnerCountryOperatingSystemBrowser(pair._1.partnerId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._1.country, pair._1.operatingSystem, pair._1.browser, pair._2)
}
