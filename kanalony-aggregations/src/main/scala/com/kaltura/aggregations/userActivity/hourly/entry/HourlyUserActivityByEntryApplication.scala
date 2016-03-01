package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryApplicationKey, UserActivityApplicationKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.aggregations.userActivity.hourly.HourlyUserActivityByApplication
import com.kaltura.model.aggregations.HourlyPartnerApplication
import com.kaltura.model.events.EnrichedPlayerEvent


object HourlyUserActivityByEntryApplication extends BaseUserActivityAggregation[UserActivityEntryApplicationKey, HourlyPartnerApplication] with IAggregateHourly with Serializable{

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_entry_app" -> columns,
     "hourly_ua_prtn_entry_clst_app" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",

     "application" as "application",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")

   override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryApplicationKey = UserActivityEntryApplicationKey(e.entryId, HourlyUserActivityByApplication.aggKey(e))
   override def toRow(pair: (UserActivityEntryApplicationKey, Long)): HourlyPartnerApplication = HourlyPartnerApplication(pair._1.applicationKey.partnerId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._1.application, pair._2)


 }
