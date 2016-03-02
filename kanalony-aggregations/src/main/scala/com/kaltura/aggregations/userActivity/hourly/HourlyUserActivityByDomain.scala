package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityDomainKey
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_domainRow

object HourlyUserActivityByDomain extends BaseUserActivityAggregation[UserActivityDomainKey, hourly_ua_prtn_domainRow] with IAggregateHourly with Serializable {

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
  override def toRow(pair: (UserActivityDomainKey, Long)): hourly_ua_prtn_domainRow = hourly_ua_prtn_domainRow(pair._1.partnerId, pair._1.domain, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
}
