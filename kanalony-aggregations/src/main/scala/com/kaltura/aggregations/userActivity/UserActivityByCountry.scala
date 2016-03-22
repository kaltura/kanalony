package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateMinutely, IAggregateTenSecs, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityCountryKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByCountry extends BaseUserActivityAggregation[UserActivityCountryKey, PartnerCountryRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("country","country"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): UserActivityCountryKey = UserActivityCountryKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.location.country)
  override def toRow(pair: (UserActivityCountryKey, Long)): PartnerCountryRes = PartnerCountryRes(partnerId = pair._1.partnerId, country = pair._1.country, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)

}

object HourlyUserActivityByCountry extends UserActivityByCountry with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_country" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_country" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCountry extends UserActivityByCountry with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_country" -> toSomeColumns(columns),
    "minutely_ua_clst_country" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByCountry extends UserActivityByCountry with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_country" -> toSomeColumns(columns),
    "tensecs_ua_clst_country" -> toSomeColumns(columns)
  )
}

case class PartnerCountryRes(partnerId: Int, metric: Int, year: Int, time: DateTime, country: String, value: Long)

