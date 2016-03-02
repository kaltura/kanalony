package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityEntryKey
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyEntry
import com.kaltura.model.events.EnrichedPlayerEvent

object HourlyUserActivityByEntry extends BaseUserActivityAggregation[UserActivityEntryKey, HourlyEntry] with IAggregateHourly with Serializable {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry" -> SomeColumns(
    "partner_id" as "partnerId",
    "entry_id" as "entryId",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value"),
  "hourly_ua_clst_entry" -> SomeColumns(
    "partner_id" as "partnerId",
    "entry_id" as "entryId",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value"))

  // TODO - dropped month column temporarily
  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryKey = UserActivityEntryKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy())
  override def toRow(pair: (UserActivityEntryKey, Long)): HourlyEntry = HourlyEntry(pair._1.partnerId, pair._1.entryId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
}
