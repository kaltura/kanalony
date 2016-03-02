package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryOperatingSystemBrowserKey, UserActivityOperatingSystemBrowserKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerOperatingSystemBrowser
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_entry_os_browserRow

object HourlyUserActivityByEntryOperatingSystemBrowser extends BaseUserActivityAggregation[UserActivityEntryOperatingSystemBrowserKey, hourly_ua_prtn_entry_os_browserRow] with IAggregateHourly with Serializable {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_os_clst_browser" -> columns,
    "hourly_ua_prtn_entry_os_browser" -> columns
    )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "entry_id" as "entryId",
    "operating_system" as "operatingSystem",
    "browser" as "browser",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryOperatingSystemBrowserKey = UserActivityEntryOperatingSystemBrowserKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.userAgent.operatingSystem.id, e.userAgent.browser.id)
  override def toRow(pair: (UserActivityEntryOperatingSystemBrowserKey, Long)): hourly_ua_prtn_entry_os_browserRow = hourly_ua_prtn_entry_os_browserRow(pair._1.partnerId, pair._1.entryId, pair._1.operatingSystem, pair._1.browser, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
}
