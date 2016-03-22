package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityDomainKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime

abstract class UserActivityByDomain extends BaseUserActivityAggregation[UserActivityDomainKey, PartnerDomainRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("domain","domain"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityDomainKey = UserActivityDomainKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.urlParts.domain)
  override def toRow(pair: (UserActivityDomainKey, Long)): PartnerDomainRes = PartnerDomainRes(partnerId = pair._1.partnerId, domain = pair._1.domain, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)
}

object HourlyUserActivityByDomain extends UserActivityByDomain with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_domain" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_domain" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByDomain extends UserActivityByDomain with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_domain" -> toSomeColumns(columns),
    "minutely_ua_clst_domain" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByDomain extends UserActivityByDomain with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_domain" -> toSomeColumns(columns),
    "tensecs_ua_clst_domain" -> toSomeColumns(columns)
  )
}
case class PartnerDomainRes(partnerId: Int, metric: Int, year: Int, time: DateTime, domain: String, value: Long)

