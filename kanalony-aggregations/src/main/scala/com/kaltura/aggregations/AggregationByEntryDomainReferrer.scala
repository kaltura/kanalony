package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryDomainReferrerKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByEntryDomainReferrer extends BaseAggregation[AggregationEntryDomainReferrerKey, EntryDomainReferrerRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("referrer","referrer"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryDomainReferrerKey = AggregationEntryDomainReferrerKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.urlParts.domain, e.urlParts.canonicalUrl)
  override def toRow(pair: (AggregationEntryDomainReferrerKey, Long)): EntryDomainReferrerRes = EntryDomainReferrerRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, domain = pair._1.domain, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, referrer = pair._1.referrer, value = pair._2)
}

object HourlyAggregationByEntryDomainReferrer extends AggregationByEntryDomainReferrer with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_referrer" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_clst_referrer" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_domain_clst_referrer" -> toSomeColumns(columns :+ ("year", "year") :+ ("domain", "domain")),
    "hourly_agg_prtn_referrer_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )
}

object MinutelyAggregationByEntryDomainReferrer extends AggregationByEntryDomainReferrer with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_referrer" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_clst_referrer" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_domain_clst_referrer" -> toSomeColumns(columns :+ ("day", "day") :+ ("domain", "domain")),
    "minutely_agg_prtn_referrer_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByEntryDomainReferrer extends AggregationByEntryDomainReferrer with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry_referrer" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_clst_referrer" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_domain_clst_referrer" -> toSomeColumns(columns :+ ("day", "day") :+ ("domain", "domain"))
  )
}

case class EntryDomainReferrerRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, domain: String, referrer: String, value: Long)
