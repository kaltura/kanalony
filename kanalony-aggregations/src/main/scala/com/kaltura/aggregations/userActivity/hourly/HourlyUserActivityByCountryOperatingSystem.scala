package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityCountryOperatingSystemKey, UserActivityCountryOperatingSystemBrowserKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.{HourlyPartnerCountryOperatingSystem, HourlyPartnerCountryOperatingSystemBrowser}
import com.kaltura.model.events.EnrichedPlayerEvent

object HourlyUserActivityByCountryOperatingSystem extends BaseUserActivityAggregation[UserActivityCountryOperatingSystemKey, HourlyPartnerCountryOperatingSystem] with IAggregateHourly with Serializable{

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_country_clst_os" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "country" as "country",
     "operating_system" as "operatingSystem",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")

   override def aggKey(e: EnrichedPlayerEvent): UserActivityCountryOperatingSystemKey = UserActivityCountryOperatingSystemKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.location.country, e.userAgent.operatingSystem.id)
   override def toRow(pair: (UserActivityCountryOperatingSystemKey, Long)): HourlyPartnerCountryOperatingSystem = HourlyPartnerCountryOperatingSystem(pair._1.partnerId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._1.country, pair._1.operatingSystem, pair._2)
 }
