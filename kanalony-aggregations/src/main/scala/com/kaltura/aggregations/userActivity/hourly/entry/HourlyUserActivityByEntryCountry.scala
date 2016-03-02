package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryCountryKey, UserActivityCountryKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerCountry
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_entry_countryRow


object HourlyUserActivityByEntryCountry extends BaseUserActivityAggregation[UserActivityEntryCountryKey, hourly_ua_prtn_entry_countryRow] with IAggregateHourly with Serializable{

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_country" -> columns,
    "hourly_ua_prtn_entry_clst_country" -> columns
  )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "entry_id" as "entryId",
    "country" as "country",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCountryKey = UserActivityEntryCountryKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.location.country)
  override def toRow(pair: (UserActivityEntryCountryKey, Long)): hourly_ua_prtn_entry_countryRow = hourly_ua_prtn_entry_countryRow(pair._1.partnerId, pair._1.entryId, pair._1.country, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)


}
