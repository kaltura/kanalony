package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityEntryDomainReferrerKey, UserActivityDomainReferrerKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime

abstract class UserActivityByEntryDomainReferrer extends BaseUserActivityAggregation[UserActivityEntryDomainReferrerKey, EntryDomainReferrerRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("referrer","referrer"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryDomainReferrerKey = UserActivityEntryDomainReferrerKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.urlParts.domain, e.urlParts.canonicalUrl)
  override def toRow(pair: (UserActivityEntryDomainReferrerKey, Long)): EntryDomainReferrerRes = EntryDomainReferrerRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, domain = pair._1.domain, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, referrer = pair._1.referrer, value = pair._2)
}

object HourlyUserActivityByEntryDomainReferrer extends UserActivityByEntryDomainReferrer with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_referrer" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_clst_referrer" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_domain_clst_referrer" -> toSomeColumns(columns :+ ("year", "year") :+ ("domain", "domain")),
    "hourly_ua_prtn_referrer_clst_entry" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByEntryDomainReferrer extends UserActivityByEntryDomainReferrer with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_referrer" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_clst_referrer" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_domain_clst_referrer" -> toSomeColumns(columns :+ ("domain", "domain")),
    "minutely_ua_prtn_referrer_clst_entry" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByEntryDomainReferrer extends UserActivityByEntryDomainReferrer with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_referrer" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_clst_referrer" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_domain_clst_referrer" -> toSomeColumns(columns :+ ("domain", "domain"))
  )
}

case class EntryDomainReferrerRes(partnerId: Int, entryId: String, metric: Int, year: Int, time: DateTime, domain: String, referrer: String, value: Long)
