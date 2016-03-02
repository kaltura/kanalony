package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryPlaybackContextKey, UserActivityPlaybackContextKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerPlaybackContext
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_entry_playbackcontextRow


object HourlyUserActivityByEntryPlaybackContext extends BaseUserActivityAggregation[UserActivityEntryPlaybackContextKey, hourly_ua_prtn_entry_playbackcontextRow] with IAggregateHourly with Serializable{

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_entry_playbackContext" -> columns,
     "hourly_ua_prtn_enrty_clst_playbackContext" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "entry_id" as "entryId",
     "playback_context" as "playbackContext",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")

   override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryPlaybackContextKey = UserActivityEntryPlaybackContextKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.playbackContext)
   override def toRow(pair: (UserActivityEntryPlaybackContextKey, Long)): hourly_ua_prtn_entry_playbackcontextRow = hourly_ua_prtn_entry_playbackcontextRow(pair._1.partnerId, pair._1.entryId, pair._1.playbackContext, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)


 }
