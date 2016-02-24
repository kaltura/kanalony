package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.HourlyPartner
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{Seconds, StateSpec, State, Time}


object HourlyUserActivityPeakAudience extends BaseEventsAggregation[UserActivityKey, HourlyPartner] with IAggregateHourly {

  //TODO: change to metric enum
  val peakAudience = 101;
  val maxStateSpec = StateSpec.function(maxTrackStateFunc _).timeout(Seconds(ttl))

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua" -> SomeColumns(
      "partner_id" as "partnerId",
      "metric" as "metric",
      "hour" as "hour",
      "year" as "year",
      "value" as "value"))

  override def trackStateFunc(batchTime: Time, key: UserActivityKey, value: Option[Long], state: State[Long]): Option[(UserActivityKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  def maxTrackStateFunc(batchTime: Time, key: UserActivityKey, value: Option[Long], state: State[Long]): Option[(UserActivityKey, Long)] = {
    val max = value.getOrElse(0L) max state.getOption.getOrElse(0L)

    val output = (key, max)
    if (!state.isTimingOut())
      state.update(max)
    Some(output)
  }

  // return audience for 10 secs
  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityKey, Long)] = {
    enrichedEvents.filter(x => x.eventType == 99 && x.playbackType.equals("Live")).map(x => (UserActivityKey(x.partnerId, x.eventType, x.eventTime.withSecondOfMinute(x.eventTime.getSecondOfMinute() / 10 * 10).withMillisOfSecond(0)), 1L)).reduceByKey(_ + _)
  }

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityKey, Long, Long, (UserActivityKey, Long)]): DStream[HourlyPartner] =
    aggregatedEvents.map({ case (k,v) => HourlyPartner(k.partnerId, peakAudience, k.time.getYear, k.time, v)})

  override def ttl(): Int = 60*60 + 5*60

  override def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit = {
    try {
      val aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
      val aggregatedEvents = aggregatedBatchEvents.mapWithState(stateSpec)


      val peakAudience = aggregatedEvents.map({case (k,v) => (UserActivityKey(k.partnerId, k.metric, k.time.hourOfDay().roundFloorCopy()), v)}).mapWithState(maxStateSpec)

      save(prepareForSave(peakAudience));

    } catch {
      case e: IllegalArgumentException => println("Failed to update state");
    }
  }
}
