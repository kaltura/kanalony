package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryCountryCityKey, UserActivityCountryCityKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerCountryCity
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_entry_country_cityRow


object HourlyUserActivityByEntryCountryCity extends BaseUserActivityAggregation[UserActivityEntryCountryCityKey, hourly_ua_prtn_entry_country_cityRow] with IAggregateHourly with Serializable{

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_country_city" -> columns,
    "hourly_ua_prtn_entry_clst_country_city" -> columns
  )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "entry_id" as "entryId",
    "country" as "country",
    "city" as "city",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")


  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCountryCityKey = UserActivityEntryCountryCityKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.location.country, e.location.city)
  override def toRow(pair: (UserActivityEntryCountryCityKey, Long)): hourly_ua_prtn_entry_country_cityRow = hourly_ua_prtn_entry_country_cityRow(pair._1.partnerId, pair._1.entryId, pair._1.metric, pair._1.country, pair._1.city, pair._1.time.getYear, pair._1.time, pair._2)
}
