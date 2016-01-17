package com.kaltura.aggregations

import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.{State, Time}
import org.apache.spark.streaming.dstream.{MapWithStateDStream, DStream}

object EntryByHourAggr extends BaseEventsAggregation[EntryKey, Long, Long, (EntryKey, Long), EntryResult] {

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(EntryKey, Long)] = enrichedEvents.map(x => (EntryKey(x.entryId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy()),1L)).reduceByKey(_ + _)

  override def trackStateFunc(batchTime: Time, key: EntryKey, value: Option[Long], state: State[Long]): Option[(EntryKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    state.update(sum)
    Some(output)
  }

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[EntryKey, Long, Long, (EntryKey, Long)]): DStream[EntryResult] = {
    aggregatedEvents.map({ case (k,v) => EntryResult(EntryKey(k.entryId, k.eventType, k.time), k.time.year().roundFloorCopy(),v)})
  }

  override def tableName(): String = "entry_events_by_hour"

}
