package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.userActivity.HourlyUserActivityByApplication._
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityApplicationPlaybackContextKey
import com.kaltura.model.aggregations.HourlyPartnerApplicationPlaybackContext
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime

abstract class UserActivityByApplicationPlaybackContext extends BaseUserActivityAggregation[UserActivityApplicationPlaybackContextKey, PartnerApplicationPlaybackContextRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("application","application"),
    ("playback_context","playbackContext"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): UserActivityApplicationPlaybackContextKey = UserActivityApplicationPlaybackContextKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.application, e.playbackContext)
   override def toRow(pair: (UserActivityApplicationPlaybackContextKey, Long)): PartnerApplicationPlaybackContextRes = PartnerApplicationPlaybackContextRes(partnerId = pair._1.partnerId, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, application = pair._1.application, playbackContext = pair._1.playbackContext, value = pair._2)
}

object HourlyUserActivityByApplicationPlaybackContext extends UserActivityByApplicationPlaybackContext with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_app_clst_playback_context" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_app_playback_context" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByApplicationPlaybackContext extends UserActivityByApplicationPlaybackContext with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_app_clst_playback_context" -> toSomeColumns(columns),
    "minutely_ua_prtn_app_playback_context" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByApplicationPlaybackContext extends UserActivityByApplicationPlaybackContext with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_app_clst_playback_context" -> toSomeColumns(columns),
    "tensecs_ua_prtn_app_playback_context" -> toSomeColumns(columns)
  )
}

case class PartnerApplicationPlaybackContextRes(partnerId: Int, metric: Int, year: Int, time: DateTime, application: String, playbackContext: String, value: Long)




