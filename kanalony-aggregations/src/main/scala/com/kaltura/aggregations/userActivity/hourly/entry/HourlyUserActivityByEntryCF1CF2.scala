package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.UserActivityEntryCF1CF2Key
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.{hourly_ua_prtn_entry_cf1_cf2Row, hourly_ua_prtn_cf1_cf2Row}


object HourlyUserActivityByEntryCF1CF2 extends BaseUserActivityAggregation[UserActivityEntryCF1CF2Key, hourly_ua_prtn_entry_cf1_cf2Row] with IAggregateHourly with Serializable{

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_entry_cf1_cf2" -> columns,
     "hourly_ua_prtn_entry_cf1_clst_cf2" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "entry_id" as "entryId",
     "cf1" as "cf1",
     "cf2" as "cf2",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")


   override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCF1CF2Key = UserActivityEntryCF1CF2Key(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.customVar1, e.customVar2)
   override def toRow(pair: (UserActivityEntryCF1CF2Key, Long)): hourly_ua_prtn_entry_cf1_cf2Row = hourly_ua_prtn_entry_cf1_cf2Row(pair._1.partnerId, pair._1.entryId, pair._1.cf1, pair._1.cf2, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
 }
