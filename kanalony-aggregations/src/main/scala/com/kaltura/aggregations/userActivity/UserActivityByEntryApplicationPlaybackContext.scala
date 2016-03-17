package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityEntryApplicationPlaybackContextKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class UserActivityByEntryApplicationPlaybackContext extends BaseUserActivityAggregation[UserActivityEntryApplicationPlaybackContextKey, EntryApplicationPlaybackContextRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("application","application"),
    ("playback_context","playbackContext"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryApplicationPlaybackContextKey = UserActivityEntryApplicationPlaybackContextKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.application, e.playbackContext)
   override def toRow(pair: (UserActivityEntryApplicationPlaybackContextKey, Long)): EntryApplicationPlaybackContextRes = EntryApplicationPlaybackContextRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, application = pair._1.application, playbackContext = pair._1.playbackContext, value = pair._2)
}

object HourlyUserActivityByEntryApplicationPlaybackContext extends UserActivityByEntryApplicationPlaybackContext with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_app_clst_playback_context" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_app_playback_context" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_app_playback_context_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))

  )
}

object MinutelyUserActivityByEntryApplicationPlaybackContext extends UserActivityByEntryApplicationPlaybackContext with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_app_clst_playback_context" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_app_playback_context" -> toSomeColumns(columns),
    "minutely_ua_prtn_app_playback_context_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsUserActivityByEntryApplicationPlaybackContext extends UserActivityByEntryApplicationPlaybackContext with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_app_clst_playback_context" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_app_playback_context" -> toSomeColumns(columns)
  )
}

case class EntryApplicationPlaybackContextRes(partnerId: Int, entryId: String, metric: Int, year: Int, month: Int, day: Int, time: DateTime, application: String, playbackContext: String, value: Long)




