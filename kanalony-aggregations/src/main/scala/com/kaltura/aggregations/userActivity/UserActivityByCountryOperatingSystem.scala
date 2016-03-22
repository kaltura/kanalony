package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityCountryOperatingSystemKey, UserActivityCountryBrowserKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime

abstract class UserActivityByCountryOperatingSystem extends BaseUserActivityAggregation[UserActivityCountryOperatingSystemKey, PartnerCountryOperatingSystemRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("country","country"),
    ("operating_system","operatingSystem"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): UserActivityCountryOperatingSystemKey = UserActivityCountryOperatingSystemKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.location.country, e.userAgent.browser.id)
   override def toRow(pair: (UserActivityCountryOperatingSystemKey, Long)): PartnerCountryOperatingSystemRes = PartnerCountryOperatingSystemRes(partnerId= pair._1.partnerId, country = pair._1.country, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, operatingSystem = pair._1.operatingSystem, value = pair._2)
}

object HourlyUserActivityByCountryOperatingSystem extends UserActivityByCountryOperatingSystem with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_country_clst_os" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCountryOperatingSystem extends UserActivityByCountryOperatingSystem with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_country_clst_os" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByCountryOperatingSystem extends UserActivityByCountryOperatingSystem with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_country_clst_os" -> toSomeColumns(columns)
  )
}


case class PartnerCountryOperatingSystemRes(partnerId: Int, metric: Int, year: Int, time: DateTime, country: String, operatingSystem: Int, value: Long)

