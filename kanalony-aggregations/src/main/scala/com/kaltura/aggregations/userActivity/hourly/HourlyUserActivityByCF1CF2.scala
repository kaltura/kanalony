package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityCF1CF2Key, UserActivityCountryCityKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerCountryCity
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.{hourly_user_activity_prtn_cf1_cf2Row, hourly_user_activity_prtn_cf1Row}


object HourlyUserActivityByCF1CF2 extends BaseUserActivityAggregation[UserActivityCF1CF2Key, hourly_user_activity_prtn_cf1_cf2Row] with IAggregateHourly with Serializable{

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_cf1_cf2" -> columns,
     "hourly_ua_prtn_cf1_clst_cf2" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "cf1" as "cf1",
     "cf2" as "cf2",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")


   override def aggKey(e: EnrichedPlayerEvent): UserActivityCF1CF2Key = UserActivityCF1CF2Key(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.cf1, e.cf2)
   override def toRow(pair: (UserActivityCF1CF2Key, Long)): hourly_user_activity_prtn_cf1_cf2Row = hourly_user_activity_prtn_cf1_cf2Row(pair._1.partnerId, pair._1.cf1, pair._1.cf2, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
 }
