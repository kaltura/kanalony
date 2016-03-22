package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateMinutely, IAggregateTenSecs, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityCountryCityKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByCountryCity extends BaseUserActivityAggregation[UserActivityCountryCityKey, PartnerCountryCity] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("country","country"),
    ("city","city"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityCountryCityKey = UserActivityCountryCityKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.location.country, e.location.city)
  override def toRow(pair: (UserActivityCountryCityKey, Long)): PartnerCountryCity = PartnerCountryCity(partnerId = pair._1.partnerId, country = pair._1.country, city = pair._1.city, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)
}

object HourlyUserActivityByCountryCity extends UserActivityByCountryCity with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_country_city" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_country_city" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_country_clst_city" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCountryCity extends UserActivityByCountryCity with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_country_city" -> toSomeColumns(columns),
    "minutely_ua_clst_country_city" -> toSomeColumns(columns),
    "minutely_ua_prtn_country_clst_city" -> toSomeColumns(columns)

  )
}

object TenSecsUserActivityByCountryCity extends UserActivityByCountryCity with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_country_city" -> toSomeColumns(columns),
    "tensecs_ua_clst_country_city" -> toSomeColumns(columns),
    "tensecs_ua_prtn_country_clst_city" -> toSomeColumns(columns)
  )
}


case class PartnerCountryCity(partnerId: Int, metric: Int, year: Int, time: DateTime, country: String, city: String, value: Long)



