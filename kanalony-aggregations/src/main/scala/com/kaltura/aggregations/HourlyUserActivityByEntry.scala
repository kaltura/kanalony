package com.kaltura.aggregations

import com.datastax.spark.connector.SomeColumns
import com.datastax.spark.connector._
import com.kaltura.model.aggregations.HourlyEntry

import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.{State, Time}
import org.apache.spark.streaming.dstream.{MapWithStateDStream, DStream}
import org.joda.time.DateTimeField

object HourlyUserActivityByEntry extends BaseEventsAggregation[EntryKey, HourlyEntry] with IAggregateHourly {

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(EntryKey, Long)] = enrichedEvents.map(x => (EntryKey(x.partnerId, x.entryId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy()),1L)).reduceByKey(_ + _)

  override def trackStateFunc(batchTime: Time, key: EntryKey, value: Option[Long], state: State[Long]): Option[(EntryKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    state.update(sum)
    Some(output)
  }

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[EntryKey, Long, Long, (EntryKey, Long)]): DStream[HourlyEntry] = {
    aggregatedEvents.map({ case (k,v) => HourlyEntry(k.partnerId, k.entryId, k.metric, k.time.getYear, k.time.getMonthOfYear, k.time, v)})
  }

  override def tableMetadata(): Map[String, SomeColumns] = Map(
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
    "month" as "month",
    "year" as "year",
    "value" as "value"))


  override def ttl(): Int = 60*60 + 5*60
}
