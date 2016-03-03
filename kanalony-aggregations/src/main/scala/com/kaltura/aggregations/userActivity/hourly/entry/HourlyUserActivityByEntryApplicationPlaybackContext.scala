package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryApplicationPlaybackContextKey, UserActivityApplicationPlaybackContextKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerApplicationPlaybackContext
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_entry_application_playbackcontextRow

object HourlyUserActivityByEntryApplicationPlaybackContext extends BaseUserActivityAggregation[UserActivityEntryApplicationPlaybackContextKey, hourly_ua_prtn_entry_application_playbackcontextRow] with IAggregateHourly with Serializable {

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_entry_app_clst_playbackContext" -> columns,
     "hourly_ua_prtn_entry_app_playbackContext" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "entry_id" as "entryId",
     "application" as "application",
     "playback_context" as "playbackContext",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")

   override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryApplicationPlaybackContextKey = UserActivityEntryApplicationPlaybackContextKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.application, e.playbackContext)
   override def toRow(pair: (UserActivityEntryApplicationPlaybackContextKey, Long)): hourly_ua_prtn_entry_application_playbackcontextRow = hourly_ua_prtn_entry_application_playbackcontextRow(pair._1.partnerId, pair._1.entryId, pair._1.application, pair._1.playbackContext, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
 }
