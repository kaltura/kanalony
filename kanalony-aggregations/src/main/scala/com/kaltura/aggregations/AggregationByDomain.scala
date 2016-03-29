package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationDomainKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByDomain extends BaseAggregation[AggregationDomainKey, PartnerDomainRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("domain","domain"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationDomainKey = AggregationDomainKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.urlParts.domain)
  override def toRow(pair: (AggregationDomainKey, Long)): PartnerDomainRes = PartnerDomainRes(partnerId = pair._1.partnerId, domain = pair._1.domain, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)
}

object HourlyAggregationByDomain extends AggregationByDomain with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_domain" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_domain" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByDomain extends AggregationByDomain with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_domain" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_domain" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByDomain extends AggregationByDomain with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_domain" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_domain" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class PartnerDomainRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, domain: String, value: Long)

