package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationDomainReferrerKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByDomainReferrer extends BaseAggregation[AggregationDomainReferrerKey, PartnerDomainReferrerRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("referrer","referrer"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationDomainReferrerKey = AggregationDomainReferrerKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.urlParts.domain, e.urlParts.canonicalUrl)
  override def toRow(pair: (AggregationDomainReferrerKey, Long)): PartnerDomainReferrerRes = PartnerDomainReferrerRes(partnerId = pair._1.partnerId, domain = pair._1.domain, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, referrer = pair._1.referrer, value = pair._2)
}

object HourlyAggregationByDomainReferrer extends AggregationByDomainReferrer with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_referrer" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_referrer" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_domain_clst_referrer" -> toSomeColumns(columns :+ ("year", "year") :+ ("domain", "domain"))
  )
}

object MinutelyAggregationByDomainReferrer extends AggregationByDomainReferrer with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_referrer" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_referrer" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_domain_clst_referrer" -> toSomeColumns(columns :+ ("day", "day") :+ ("domain", "domain"))
  )
}

object TenSecsAggregationByDomainReferrer extends AggregationByDomainReferrer with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_referrer" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_referrer" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_domain_clst_referrer" -> toSomeColumns(columns :+ ("day", "day") :+ ("domain", "domain"))
  )
}

case class PartnerDomainReferrerRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, domain: String, referrer: String, value: Long)
