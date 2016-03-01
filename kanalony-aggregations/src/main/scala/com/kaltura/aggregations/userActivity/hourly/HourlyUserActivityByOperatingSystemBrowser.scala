package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityOperatingSystemBrowserKey
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerOperatingSystemBrowser
import com.kaltura.model.events.EnrichedPlayerEvent

object HourlyUserActivityByOperatingSystemBrowser extends BaseUserActivityAggregation[UserActivityOperatingSystemBrowserKey, HourlyPartnerOperatingSystemBrowser] with IAggregateHourly with Serializable {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_os_clst_browser" -> columns,
    "hourly_ua_prtn_os_browser" -> columns
    )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "operating_system" as "operatingSystem",
    "browser" as "browser",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityOperatingSystemBrowserKey = UserActivityOperatingSystemBrowserKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.userAgent.operatingSystem.id, e.userAgent.browser.id)
  override def toRow(pair: (UserActivityOperatingSystemBrowserKey, Long)): HourlyPartnerOperatingSystemBrowser = HourlyPartnerOperatingSystemBrowser(pair._1.partnerId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._1.operatingSystem, pair._1.browser, pair._2)
}
