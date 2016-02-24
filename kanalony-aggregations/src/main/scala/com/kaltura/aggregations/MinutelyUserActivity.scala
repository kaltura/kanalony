package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{MinutelyPartner, HourlyPartner}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object MinutelyUserActivity extends BaseEventsAggregation[UserActivityKey, MinutelyPartner] with IAggregateMinutely{

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "minutely_ua" -> SomeColumns(
      "partner_id" as "partnerId",
      "metric" as "metric",
      "minute" as "minute",
      "value" as "value"))

  override def trackStateFunc(batchTime: Time, key: UserActivityKey, value: Option[Long], state: State[Long]): Option[(UserActivityKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityKey, Long)] =
    enrichedEvents.map(x => (UserActivityKey(x.partnerId, x.eventType, x.eventTime.minuteOfHour().roundFloorCopy()),1L)).reduceByKey(_ + _)

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityKey, Long, Long, (UserActivityKey, Long)]): DStream[MinutelyPartner] =
    aggregatedEvents.map({ case (k,v) => MinutelyPartner(k.partnerId, k.metric, k.time, v)})

  override def ttl(): Int = 5*60
}
