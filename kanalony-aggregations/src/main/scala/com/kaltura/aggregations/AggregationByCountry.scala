package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationCountryKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits



abstract class AggregationByCountry extends BaseAggregation[AggregationCountryKey, PartnerCountryRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("country","country"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): AggregationCountryKey = AggregationCountryKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.location.country)
  override def toRow(pair: (AggregationCountryKey, Long)): PartnerCountryRes = PartnerCountryRes(partnerId = pair._1.partnerId, country = pair._1.country, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)

}

object HourlyAggregationByCountry extends AggregationByCountry with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_country" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_country" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByCountry extends AggregationByCountry with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_country" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_country" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByCountry extends AggregationByCountry with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_country" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_country" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerCountryRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, country: String, value: Long)

