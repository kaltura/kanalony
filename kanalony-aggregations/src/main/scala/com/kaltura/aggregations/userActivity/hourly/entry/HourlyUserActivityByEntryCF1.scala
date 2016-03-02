package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryCFKey, UserActivityCFKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.{hourly_ua_prtn_entry_cf1Row, hourly_ua_prtn_cf1Row}


object HourlyUserActivityByEntryCF1 extends BaseUserActivityAggregation[UserActivityEntryCFKey, hourly_ua_prtn_entry_cf1Row] with IAggregateHourly with Serializable{

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_entry_cf1" -> columns,
     "hourly_ua_prtn_entry_clst_cf1" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "entry_id" as "entryId",
     "cf1" as "cf1",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")

   override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCFKey = UserActivityEntryCFKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.customVar1)
   override def toRow(pair: (UserActivityEntryCFKey, Long)): hourly_ua_prtn_entry_cf1Row = hourly_ua_prtn_entry_cf1Row(pair._1.partnerId, pair._1.entryId, pair._1.cf, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)


 }
