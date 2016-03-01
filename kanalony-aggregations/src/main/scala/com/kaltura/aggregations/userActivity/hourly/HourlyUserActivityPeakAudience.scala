package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityKey
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartner
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, State, StateSpec, Time}


object HourlyUserActivityPeakAudience extends BaseUserActivityAggregation[UserActivityKey, HourlyPartner] with IAggregateHourly {

  //TODO: change to metric enum
  val peakAudience = 101;
  val maxStateSpec = StateSpec.function(maxTrackStateFunc _).timeout(Seconds(ttl))

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua" -> SomeColumns(
      "partner_id" as "partnerId",
      "metric" as "metric",
      "hour" as "hour",
      "year" as "year",
      "value" as "value"))
  
  def maxTrackStateFunc(batchTime: Time, key: UserActivityKey, value: Option[Long], state: State[Long]): Option[(UserActivityKey, Long)] = {
    val max = value.getOrElse(0L) max state.getOption.getOrElse(0L)

    val output = (key, max)
    if (!state.isTimingOut())
      state.update(max)
    Some(output)
  }

  // return audience for 10 secs
  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityKey, Long)] = {
    enrichedEvents.filter(e => e.eventType == 99 && e.playbackType.equals("Live")).map(ev => (aggKey(ev), 1L)).reduceByKey(_ + _)
  }

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

  override def aggKey(e: EnrichedPlayerEvent): UserActivityKey = UserActivityKey(e.partnerId, e.eventType, e.eventTime.withSecondOfMinute(e.eventTime.getSecondOfMinute() / 10 * 10).withMillisOfSecond(0))
  override def toRow(pair: (UserActivityKey, Long)): HourlyPartner = HourlyPartner(pair._1.partnerId, peakAudience, pair._1.time.getYear, pair._1.time, pair._2)
}
