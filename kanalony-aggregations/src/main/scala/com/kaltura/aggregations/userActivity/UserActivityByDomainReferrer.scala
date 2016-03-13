package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityDomainReferrerKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime

abstract class UserActivityByDomainReferrer extends BaseUserActivityAggregation[UserActivityDomainReferrerKey, PartnerDomainReferrerRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("referrer","referrer"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityDomainReferrerKey = UserActivityDomainReferrerKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.urlParts.domain, e.urlParts.canonicalUrl)
  override def toRow(pair: (UserActivityDomainReferrerKey, Long)): PartnerDomainReferrerRes = PartnerDomainReferrerRes(partnerId = pair._1.partnerId, domain = pair._1.domain, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, referrer = pair._1.referrer, value = pair._2)
}

object HourlyUserActivityByDomainReferrer extends UserActivityByDomainReferrer with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_referrer" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_referrer" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_domain_clst_referrer" -> toSomeColumns(columns :+ ("year", "year") :+ ("domain", "domain"))
  )
}

object MinutelyUserActivityByDomainReferrer extends UserActivityByDomainReferrer with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_referrer" -> toSomeColumns(columns),
    "minutely_ua_clst_referrer" -> toSomeColumns(columns),
    "minutely_ua_prtn_domain_clst_referrer" -> toSomeColumns(columns :+ ("domain", "domain"))
  )
}

object TenSecsUserActivityByDomainReferrer extends UserActivityByDomainReferrer with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_referrer" -> toSomeColumns(columns),
    "tensecs_ua_clst_referrer" -> toSomeColumns(columns),
    "tensecs_ua_prtn_domain_clst_referrer" -> toSomeColumns(columns :+ ("domain", "domain"))
  )
}

case class PartnerDomainReferrerRes(partnerId: Int, metric: Int, year: Int, time: DateTime, domain: String, referrer: String, value: Long)
