package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityOperatingSystemBrowserKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByOperatingSystemBrowser extends BaseUserActivityAggregation[UserActivityOperatingSystemBrowserKey, PartnerOperatingSystemBrowserRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("operating_system", "operatingSystem"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityOperatingSystemBrowserKey = UserActivityOperatingSystemBrowserKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.userAgent.operatingSystem.id, e.userAgent.browser.id)
  override def toRow(pair: (UserActivityOperatingSystemBrowserKey, Long)): PartnerOperatingSystemBrowserRes = PartnerOperatingSystemBrowserRes(partnerId = pair._1.partnerId, operatingSystem = pair._1.operatingSystem, browser = pair._1.browser, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)

}

object HourlyUserActivityByOperatingSystemBrowser extends UserActivityByOperatingSystemBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_os_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_os_clst_browser" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByOperatingSystemBrowser extends UserActivityByOperatingSystemBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_os_browser" -> toSomeColumns(columns),
    "minutely_ua_prtn_os_clst_browser" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByOperatingSystemBrowser extends UserActivityByOperatingSystemBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_os_browser" -> toSomeColumns(columns),
    "tensecs_ua_prtn_os_clst_browser" -> toSomeColumns(columns)
  )
}

case class PartnerOperatingSystemBrowserRes(partnerId: Int, metric: Int, year: Int, time: DateTime, operatingSystem: Int, browser: Int, value: Long)

