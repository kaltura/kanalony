package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.UserActivityCFKey
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_cf3Row


object HourlyUserActivityByCF3 extends BaseUserActivityAggregation[UserActivityCFKey, hourly_ua_prtn_cf3Row] with IAggregateHourly with Serializable{

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_cf3" -> columns,
     "hourly_ua_clst_cf3" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "cf3" as "cf3",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")

   override def aggKey(e: EnrichedPlayerEvent): UserActivityCFKey = UserActivityCFKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.customVar3)
   override def toRow(pair: (UserActivityCFKey, Long)): hourly_ua_prtn_cf3Row = hourly_ua_prtn_cf3Row(pair._1.partnerId, pair._1.cf, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)


 }
