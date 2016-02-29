package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityCountryKey
import com.kaltura.model.aggregations.HourlyPartnerCountry
import com.kaltura.model.events.EnrichedPlayerEvent


object HourlyUserActivityByCountry extends BaseUserActivityAggregation[UserActivityCountryKey, HourlyPartnerCountry] with IAggregateHourly with Serializable{

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
  override def toRow(pair: (UserActivityCountryKey, Long)): HourlyPartnerCountry = HourlyPartnerCountry(pair._1.partnerId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._1.country, pair._2)


}
