package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.userActivity.HourlyUserActivityByApplicationPlaybackContext._
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityBrowserKey
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_browserRow
import org.joda.time.DateTime


abstract class UserActivityByBrowser extends BaseUserActivityAggregation[UserActivityBrowserKey, PartnerBrowserRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityBrowserKey = UserActivityBrowserKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.userAgent.browser.id)
  override def toRow(pair: (UserActivityBrowserKey, Long)): PartnerBrowserRes = PartnerBrowserRes(partnerId = pair._1.partnerId, browser = pair._1.browser, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)


}

object HourlyUserActivityByBrowser extends UserActivityByBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_browser" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByBrowser extends UserActivityByBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_browser" -> toSomeColumns(columns),
    "minutely_ua_clst_browser" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByBrowser extends UserActivityByBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_browser" -> toSomeColumns(columns),
    "tensecs_ua_clst_browser" -> toSomeColumns(columns)
  )
}
case class PartnerBrowserRes(partnerId: Int, metric: Int, year: Int, time: DateTime, browser: Int, value: Long)

