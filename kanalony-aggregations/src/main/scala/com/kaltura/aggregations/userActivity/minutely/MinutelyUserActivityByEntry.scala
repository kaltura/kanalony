package com.kaltura.aggregations.userActivity.minutely

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.EntryKey
import com.kaltura.aggregations.IAggregateMinutely
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.MinutelyEntry
import com.kaltura.model.events.EnrichedPlayerEvent




object MinutelyUserActivityByEntry extends BaseUserActivityAggregation[EntryKey, MinutelyEntry] with IAggregateMinutely with Serializable {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry" -> SomeColumns(
      "partner_id" as "partnerId",
      "entry_id" as "entryId",
      "metric" as "metric",
      "minute" as "minute",
      "value" as "value"),
    "minutely_ua_clst_entry" -> SomeColumns(
      "partner_id" as "partnerId",
      "entry_id" as "entryId",
      "metric" as "metric",
      "day" as "day",
      "minute" as "minute",
      "value" as "value")
    )

  override def aggKey(e: EnrichedPlayerEvent): EntryKey = EntryKey(e.partnerId, e.entryId, e.eventType, e.eventTime.minuteOfHour().roundFloorCopy())
  override def toRow(pair: (EntryKey, Long)): MinutelyEntry = MinutelyEntry(pair._1.partnerId, pair._1.entryId, pair._1.metric, pair._1.time.getDayOfYear, pair._1.time, pair._2)
}
