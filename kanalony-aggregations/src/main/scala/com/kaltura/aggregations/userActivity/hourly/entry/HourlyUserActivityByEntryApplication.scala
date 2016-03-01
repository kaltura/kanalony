package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.UserActivityEntryApplicationKey
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.{HourlyEntryApplication, HourlyPartnerApplication}
import com.kaltura.model.events.EnrichedPlayerEvent


object HourlyUserActivityByEntryApplication extends BaseUserActivityAggregation[UserActivityEntryApplicationKey, HourlyEntryApplication] with IAggregateHourly with Serializable{

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_entry_app" -> columns,
     "hourly_ua_prtn_entry_clst_app" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "entry_id" as "entryId",
     "application" as "application",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryApplicationKey = UserActivityEntryApplicationKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.application)

  override def toRow(pair: (UserActivityEntryApplicationKey, Long)): HourlyEntryApplication = ???
}
