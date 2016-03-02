package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryKey, UserActivityKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartner
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_entryRow
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, State, StateSpec, Time}


object HourlyUserActivityEntryPeakAudience extends BaseUserActivityAggregation[UserActivityEntryKey, hourly_ua_prtn_entryRow] with IAggregateHourly {

  //TODO: change to metric enum
  val peakAudience = 101;
  val maxStateSpec = StateSpec.function(maxTrackStateFunc _).timeout(Seconds(ttl))

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry" -> SomeColumns(
      "partner_id" as "partnerId",
      "entry_id" as "entryId",
      "metric" as "metric",
      "hour" as "hour",
      "year" as "year",
      "value" as "value"))
  
  def maxTrackStateFunc(batchTime: Time, key: UserActivityEntryKey, value: Option[Long], state: State[Long]): Option[(UserActivityEntryKey, Long)] = {
    val max = value.getOrElse(0L) max state.getOption.getOrElse(0L)

    val output = (key, max)
    if (!state.isTimingOut())
      state.update(max)
    Some(output)
  }

  // return audience for 10 secs
  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityEntryKey, Long)] = {
    enrichedEvents.filter(e => e.eventType == 99 && e.playbackType.equals("Live")).map(ev => (aggKey(ev), 1L)).reduceByKey(_ + _)
  }

  override def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit = {
    try {
      val aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
      val aggregatedEvents = aggregatedBatchEvents.mapWithState(stateSpec)

      val peakAudience = aggregatedEvents.map({case (k,v) => (UserActivityEntryKey(k.partnerId, k.entryId, k.metric, k.time.hourOfDay().roundFloorCopy()), v)}).mapWithState(maxStateSpec)
      save(prepareForSave(peakAudience));

    } catch {
      case e: IllegalArgumentException => println("Failed to update state");
    }
  }

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryKey = UserActivityEntryKey(e.partnerId, e.entryId, e.eventType, e.eventTime.withSecondOfMinute(e.eventTime.getSecondOfMinute() / 10 * 10).withMillisOfSecond(0))
  override def toRow(pair: (UserActivityEntryKey, Long)): hourly_ua_prtn_entryRow = hourly_ua_prtn_entryRow(pair._1.partnerId, pair._1.entryId, peakAudience, pair._1.time.getYear, pair._1.time, pair._2)
}
