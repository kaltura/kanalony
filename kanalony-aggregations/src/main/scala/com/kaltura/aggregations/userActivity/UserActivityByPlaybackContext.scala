package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityPlaybackContextKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime

abstract class UserActivityByPlaybackContext extends BaseUserActivityAggregation[UserActivityPlaybackContextKey, EntryPlaybackContextRes] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("playback_context","playbackContext"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): UserActivityPlaybackContextKey = UserActivityPlaybackContextKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.playbackContext)
  override def toRow(pair: (UserActivityPlaybackContextKey, Long)): EntryPlaybackContextRes = EntryPlaybackContextRes(partnerId = pair._1.partnerId, playbackContext = pair._1.playbackContext, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)

}


object HourlyUserActivityByPlaybackContext extends UserActivityByPlaybackContext with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_playback_context" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_playback_context" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByPlaybackContext extends UserActivityByPlaybackContext with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_playback_context" -> toSomeColumns(columns),
    "minutely_ua_clst_playback_context" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByPlaybackContext extends UserActivityByPlaybackContext with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_playback_context" -> toSomeColumns(columns),
    "tensecs_ua_clst_playback_context" -> toSomeColumns(columns)
  )
}

case class EntryPlaybackContextRes(partnerId: Int, metric: Int, year: Int, time: DateTime, playbackContext: String, value: Long)
