package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.UserActivityCountryBrowserKey
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_country_clst_browserRow

object HourlyUserActivityByCountryBrowser extends BaseUserActivityAggregation[UserActivityCountryBrowserKey, hourly_ua_prtn_country_clst_browserRow] with IAggregateHourly with Serializable{

   override lazy val tableMetadata: Map[String, SomeColumns] = Map(
     "hourly_ua_prtn_country_clst_browser" -> columns
   )

   val columns : SomeColumns = new SomeColumns(
     "partner_id" as "partnerId",
     "country" as "country",
     "browser" as "browser",
     "metric" as "metric",
     "hour" as "hour",
     "year" as "year",
     "value" as "value")

   override def aggKey(e: EnrichedPlayerEvent): UserActivityCountryBrowserKey = UserActivityCountryBrowserKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.location.country, e.userAgent.browser.id)
   override def toRow(pair: (UserActivityCountryBrowserKey, Long)): hourly_ua_prtn_country_clst_browserRow = hourly_ua_prtn_country_clst_browserRow(pair._1.partnerId, pair._1.country, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._1.browser, pair._2)
 }
