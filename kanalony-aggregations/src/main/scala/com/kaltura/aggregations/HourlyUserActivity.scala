package com.kaltura.aggregations

import com.datastax.spark.connector._
import com.datastax.spark.connector.SomeColumns
import com.kaltura.model.aggregations.{MinutelyEntry, HourlyPartner, HourlyEntry}
import com.kaltura.model.events.EnrichedPlayerEvent

import org.apache.spark.streaming.{State, Time}
import org.apache.spark.streaming.dstream.{MapWithStateDStream, DStream}


object HourlyUserActivity extends BaseEventsAggregation[UserActivityKey, HourlyPartner] with IAggregateHourly {

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua" -> SomeColumns(
      "partner_id" as "partnerId",
      "metric" as "metric",
      "hour" as "hour",
      "year" as "year",
      "value" as "value"))

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityKey, Long)] =
    enrichedEvents.map(x => (UserActivityKey(x.partnerId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy()),1L)).reduceByKey(_ + _)

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityKey, Long, Long, (UserActivityKey, Long)]): DStream[HourlyPartner] =
    aggregatedEvents.map({ case (k,v) => HourlyPartner(k.partnerId, k.metric, k.time.getYear, k.time, v)})

  override def ttl(): Int = 60*60 + 5*60
}
