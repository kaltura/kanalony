package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationCountryOperatingSystemKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByCountryOperatingSystem extends BaseAggregation[AggregationCountryOperatingSystemKey, PartnerCountryOperatingSystemRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("country","country"),
    ("operating_system","operatingSystem"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): AggregationCountryOperatingSystemKey = AggregationCountryOperatingSystemKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.location.country, e.userAgent.browser.id)
   override def toRow(pair: (AggregationCountryOperatingSystemKey, Long)): PartnerCountryOperatingSystemRes = PartnerCountryOperatingSystemRes(partnerId= pair._1.partnerId, country = pair._1.country, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, operatingSystem = pair._1.operatingSystem, value = pair._2)
}

object HourlyAggregationByCountryOperatingSystem extends AggregationByCountryOperatingSystem with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_country_clst_os" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByCountryOperatingSystem extends AggregationByCountryOperatingSystem with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_country_clst_os" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByCountryOperatingSystem extends AggregationByCountryOperatingSystem with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_country_clst_os" -> toSomeColumns(columns :+ ("day", "day"))
  )
}


case class PartnerCountryOperatingSystemRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, country: String, operatingSystem: Int, value: Long)

