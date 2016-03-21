package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityCountryBrowserKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime

abstract class UserActivityByCountryBrowser extends BaseUserActivityAggregation[UserActivityCountryBrowserKey, PartnerCountryBrowserRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("country","country"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): UserActivityCountryBrowserKey = UserActivityCountryBrowserKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.location.country, e.userAgent.browser.id)
   override def toRow(pair: (UserActivityCountryBrowserKey, Long)): PartnerCountryBrowserRes = PartnerCountryBrowserRes(partnerId= pair._1.partnerId, country = pair._1.country, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, browser = pair._1.browser, value = pair._2)
}

object HourlyUserActivityByCountryBrowser extends UserActivityByCountryBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_country_clst_browser" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCountryBrowser extends UserActivityByCountryBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_country_clst_browser" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByCountryBrowser extends UserActivityByCountryBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_country_clst_browser" -> toSomeColumns(columns)
  )
}



case class PartnerCountryBrowserRes(partnerId: Int, metric: Int, year: Int, time: DateTime, country: String, browser: Int, value: Long)

