package com.kaltura.aggregations


import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationKey
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.entities.{PlayerEventTypes, Metrics}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, State, StateSpec, Time}
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits
import com.datastax.spark.connector.streaming._

abstract class AggregationPeakAudience extends BaseAggregation[AggregationKey, PartnerRes] {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  val maxStateSpec = StateSpec.function(maxTrackStateFunc _).timeout(Seconds(ttl))

  override def aggKey(e: EnrichedPlayerEvent): AggregationKey = AggregationKey(e.partnerId, e.eventType, e.eventTime.withSecondOfMinute(e.eventTime.getSecondOfMinute() / 10 * 10).withMillisOfSecond(0))

  override def toRow(pair: (AggregationKey, Long)): PartnerRes = PartnerRes(pair._1.partnerId, Metrics.peakView.toString, pair._1.time.getYear, pair._1.time getYearMonthDay, pair._1.time, pair._2)

  def maxTrackStateFunc(batchTime: Time, key: AggregationKey, value: Option[Long], state: State[Long]): Option[(AggregationKey, Long)] = {
    val max = value.getOrElse(0L) max state.getOption.getOrElse(0L)

    val output = (key, max)
    if (!state.isTimingOut())
      state.update(max)
    Some(output)
  }

  override def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit = {
    if (ConfigurationManager.get("kanalony.events_aggregations.enabled_aggregations").split(",").contains(this.getClass.getSimpleName.stripSuffix("$"))) {
      val aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
      val aggregatedEvents = aggregatedBatchEvents.mapWithState(stateSpec)

      val peakAudience = aggregatedEvents.map({ case (k, v) => (AggregationKey(k.partnerId, k.metric, getAggrTime(k.time)), v) }).mapWithState(maxStateSpec)
      save(prepareForSave(peakAudience))
    }
  }

  val viewEvent = PlayerEventTypes.view.toString

  // return audience for 10 secs
  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(AggregationKey, Long)] = {
    enrichedEvents.filter(e => e.eventType.equals(viewEvent) && e.playbackType.equals("Live")).map(ev => (aggKey(ev), 1L)).reduceByKey(_ + _)
  }
}
object HourlyAggregationPeakAudience extends AggregationPeakAudience with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg" -> toSomeColumns(columns :+("year", "year"))
  )
}

object MinutelyAggregationPeakAudience extends AggregationPeakAudience with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg" -> toSomeColumns(columns :+("day", "day"))
  )
}
