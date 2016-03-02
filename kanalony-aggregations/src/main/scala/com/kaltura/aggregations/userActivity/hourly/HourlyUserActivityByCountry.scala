package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityCountryKey
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_countryRow


object HourlyUserActivityByCountry extends BaseUserActivityAggregation[UserActivityCountryKey, hourly_ua_prtn_countryRow] with IAggregateHourly with Serializable{

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_country" -> columns,
    "hourly_ua_clst_country" -> columns
  )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "country" as "country",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityCountryKey = UserActivityCountryKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.location.country)
  override def toRow(pair: (UserActivityCountryKey, Long)): hourly_ua_prtn_countryRow = hourly_ua_prtn_countryRow(pair._1.partnerId, pair._1.country, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)


}
