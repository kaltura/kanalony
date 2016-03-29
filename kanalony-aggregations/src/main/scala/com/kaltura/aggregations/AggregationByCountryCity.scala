package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationCountryCityKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits



abstract class AggregationByCountryCity extends BaseAggregation[AggregationCountryCityKey, PartnerCountryCity] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("country","country"),
    ("city","city"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationCountryCityKey = AggregationCountryCityKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.location.country, e.location.city)
  override def toRow(pair: (AggregationCountryCityKey, Long)): PartnerCountryCity = PartnerCountryCity(partnerId = pair._1.partnerId, country = pair._1.country, city = pair._1.city, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)
}

object HourlyAggregationByCountryCity extends AggregationByCountryCity with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_country_city" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_country_city" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_country_clst_city" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByCountryCity extends AggregationByCountryCity with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_country_city" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_country_city" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_country_clst_city" -> toSomeColumns(columns :+ ("day", "day"))

  )
}

object TenSecsAggregationByCountryCity extends AggregationByCountryCity with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_country_city" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_country_city" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_country_clst_city" -> toSomeColumns(columns :+ ("day", "day"))
  )
}


case class PartnerCountryCity(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, country: String, city: String, value: Long)



