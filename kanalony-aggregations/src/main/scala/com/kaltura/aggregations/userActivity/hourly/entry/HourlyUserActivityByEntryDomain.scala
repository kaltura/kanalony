package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryDomainKey, UserActivityDomainKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerDomain
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_entry_domainRow

object HourlyUserActivityByEntryDomain extends BaseUserActivityAggregation[UserActivityEntryDomainKey, hourly_ua_prtn_entry_domainRow] with IAggregateHourly with Serializable {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_domain" -> columns,
    "hourly_ua_prtn_entry_clst_domain" -> columns
    )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "entry_id" as "entryId",
    "domain" as "domain",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryDomainKey = UserActivityEntryDomainKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.urlParts.domain)
  override def toRow(pair: (UserActivityEntryDomainKey, Long)): hourly_ua_prtn_entry_domainRow = hourly_ua_prtn_entry_domainRow(pair._1.partnerId, pair._1.entryId, pair._1.domain, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
}
