package com.kaltura.aggregations

import com.kaltura.model.aggregations.MinutelyEntry
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.{State, Time}
import org.apache.spark.streaming.dstream.{MapWithStateDStream, DStream}


import com.datastax.spark.connector._
import com.datastax.spark.connector.SomeColumns




object MinutelyUserActivityByEntry extends BaseEventsAggregation[EntryKey, MinutelyEntry] with IAggregateMinutely {

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(EntryKey, Long)] = enrichedEvents.map(x => (EntryKey(x.partnerId, x.entryId, x.eventType, x.eventTime.minuteOfHour().roundFloorCopy()),1L)).reduceByKey(_ + _)

  override def trackStateFunc(batchTime: Time, key: EntryKey, value: Option[Long], state: State[Long]): Option[(EntryKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[EntryKey, Long, Long, (EntryKey, Long)]): DStream[MinutelyEntry] = {
    aggregatedEvents.map({ case (k,v) => MinutelyEntry(k.partnerId, k.entryId, k.metric, k.time.getDayOfYear, k.time, v)})
  }

  override def tableMetadata(): Map[String, SomeColumns] = Map(
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



  override def ttl(): Int = 120
}
