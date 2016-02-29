package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityDomainKey
import com.kaltura.model.aggregations.HourlyPartnerDomain
import com.kaltura.model.events.EnrichedPlayerEvent

object HourlyUserActivityByDomain extends BaseUserActivityAggregation[UserActivityDomainKey, HourlyPartnerDomain] with IAggregateHourly with Serializable {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_domain" -> columns,
    "hourly_ua_clst_domain" -> columns
    )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "domain" as "domain",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityDomainKey = UserActivityDomainKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.urlParts.domain)
  override def toRow(pair: (UserActivityDomainKey, Long)): HourlyPartnerDomain = HourlyPartnerDomain(pair._1.partnerId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._1.domain, pair._2)
}
