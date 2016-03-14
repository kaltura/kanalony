package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.userActivity.HourlyUserActivityByApplicationPlaybackContext._
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityEntryBrowserKey, UserActivityBrowserKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_browserRow
import org.joda.time.DateTime


abstract class UserActivityByEntryBrowser extends BaseUserActivityAggregation[UserActivityEntryBrowserKey, EntryBrowserRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryBrowserKey = UserActivityEntryBrowserKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.userAgent.browser.id)
  override def toRow(pair: (UserActivityEntryBrowserKey, Long)): EntryBrowserRes = EntryBrowserRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, browser = pair._1.browser, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)


}

object HourlyUserActivityByEntryBrowser extends UserActivityByEntryBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_browser" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByEntryBrowser extends UserActivityByEntryBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_browser" -> toSomeColumns(columns),
    "minutely_ua_clst_browser" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByEntryBrowser extends UserActivityByEntryBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_browser" -> toSomeColumns(columns),
    "tensecs_ua_clst_browser" -> toSomeColumns(columns)
  )
}
case class EntryBrowserRes(partnerId: Int, entryId: String, metric: Int, year: Int, time: DateTime, browser: Int, value: Long)

