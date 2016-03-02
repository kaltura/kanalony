package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.UserActivityPlaybackContextKey
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_playbackcontextRow


object HourlyUserActivityByPlaybackContext extends BaseUserActivityAggregation[UserActivityPlaybackContextKey, hourly_ua_prtn_playbackcontextRow] with IAggregateHourly with Serializable{

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_playbackContext" -> columns,
     "hourly_ua_clst_playbackContext" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "playback_context" as "playbackContext",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")

   override def aggKey(e: EnrichedPlayerEvent): UserActivityPlaybackContextKey = UserActivityPlaybackContextKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.playbackContext)
   override def toRow(pair: (UserActivityPlaybackContextKey, Long)): hourly_ua_prtn_playbackcontextRow = hourly_ua_prtn_playbackcontextRow(pair._1.partnerId, pair._1.playbackContext, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)


 }
