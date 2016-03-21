package com.kaltura.aggregations.userActivity


import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityKey
import com.kaltura.aggregations.{IAggregateMinutely, IAggregateHourly}
import com.kaltura.aggregations.userActivity.HourlyUserActivityByApplication._
import com.kaltura.model.entities.Metrics
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_Row
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, State, StateSpec, Time}

abstract class UserActivityPeakAudience extends BaseUserActivityAggregation[UserActivityKey, PartnerRes] {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  val maxStateSpec = StateSpec.function(maxTrackStateFunc _).timeout(Seconds(ttl))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityKey = UserActivityKey(e.partnerId, e.eventType, e.eventTime.withSecondOfMinute(e.eventTime.getSecondOfMinute() / 10 * 10).withMillisOfSecond(0))

  override def toRow(pair: (UserActivityKey, Long)): PartnerRes = PartnerRes(pair._1.partnerId, Metrics.peakView.id, pair._1.time.getYear, pair._1.time, pair._2)

  def maxTrackStateFunc(batchTime: Time, key: UserActivityKey, value: Option[Long], state: State[Long]): Option[(UserActivityKey, Long)] = {
    val max = value.getOrElse(0L) max state.getOption.getOrElse(0L)

    val output = (key, max)
    if (!state.isTimingOut())
      state.update(max)
    Some(output)
  }

  override def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit = {
    try {
      val aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
      val aggregatedEvents = aggregatedBatchEvents.mapWithState(stateSpec)

      val peakAudience = aggregatedEvents.map({case (k,v) => (UserActivityKey(k.partnerId, k.metric, getAggrTime(k.time)), v)}).mapWithState(maxStateSpec)
      save(prepareForSave(peakAudience));

    } catch {
      case e: IllegalArgumentException => println("Failed to update state");
    }
  }

  // return audience for 10 secs
  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityKey, Long)] = {
    enrichedEvents.filter(e => e.eventType == 99 && e.playbackType.equals("Live")).map(ev => (aggKey(ev), 1L)).reduceByKey(_ + _)
  }
}
object HourlyUserActivityPeakAudience extends UserActivityPeakAudience with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua" -> toSomeColumns(columns :+("year", "year"))
  )
}

object MiutelyUserActivityPeakAudience extends UserActivityPeakAudience with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua" -> toSomeColumns(columns)
  )
}