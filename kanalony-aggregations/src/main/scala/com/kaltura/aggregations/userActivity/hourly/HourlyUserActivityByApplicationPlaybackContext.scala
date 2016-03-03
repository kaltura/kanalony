package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.UserActivityApplicationPlaybackContextKey
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerApplicationPlaybackContext
import com.kaltura.model.events.EnrichedPlayerEvent

object HourlyUserActivityByApplicationPlaybackContext extends BaseUserActivityAggregation[UserActivityApplicationPlaybackContextKey, HourlyPartnerApplicationPlaybackContext] with IAggregateHourly with Serializable {

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_app_clst_playbackContext" -> columns,
     "hourly_ua_prtn_app_playbackContext" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "application" as "application",
     "playback_context" as "playbackContext",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")

   override def aggKey(e: EnrichedPlayerEvent): UserActivityApplicationPlaybackContextKey = UserActivityApplicationPlaybackContextKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.application, e.playbackContext)
   override def toRow(pair: (UserActivityApplicationPlaybackContextKey, Long)): HourlyPartnerApplicationPlaybackContext = HourlyPartnerApplicationPlaybackContext(pair._1.partnerId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._1.application, pair._1.playbackContext, pair._2)
 }
