package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityEntryOperatingSystemBrowserKey, UserActivityOperatingSystemBrowserKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByEntryOperatingSystemBrowser extends BaseUserActivityAggregation[UserActivityEntryOperatingSystemBrowserKey, EntryOperatingSystemBrowserRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("operating_system", "operatingSystem"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryOperatingSystemBrowserKey = UserActivityEntryOperatingSystemBrowserKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.userAgent.operatingSystem.id, e.userAgent.browser.id)
  override def toRow(pair: (UserActivityEntryOperatingSystemBrowserKey, Long)): EntryOperatingSystemBrowserRes = EntryOperatingSystemBrowserRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, operatingSystem = pair._1.operatingSystem, browser = pair._1.browser, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)

}

object HourlyUserActivityByEntryOperatingSystemBrowser extends UserActivityByEntryOperatingSystemBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_os_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_os_clst_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_os_browser_clst_entry" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByEntryOperatingSystemBrowser extends UserActivityByEntryOperatingSystemBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_os_browser" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_os_clst_browser" -> toSomeColumns(columns),
    "minutely_ua_prtn_os_browser_clst_entry" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByEntryOperatingSystemBrowser extends UserActivityByEntryOperatingSystemBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_os_browser" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_os_clst_browser" -> toSomeColumns(columns)
  )
}

case class EntryOperatingSystemBrowserRes(partnerId: Int, entryId: String, metric: Int, year: Int, time: DateTime, operatingSystem: Int, browser: Int, value: Long)

