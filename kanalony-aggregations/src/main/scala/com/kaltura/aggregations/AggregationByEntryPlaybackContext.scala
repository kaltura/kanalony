package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryPlaybackContextKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByEntryPlaybackContext extends BaseAggregation[AggregationEntryPlaybackContextKey, EntryPlaybackContextRes] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("playback_context","playbackContext"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryPlaybackContextKey = AggregationEntryPlaybackContextKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.playbackContext)
  override def toRow(pair: (AggregationEntryPlaybackContextKey, Long)): EntryPlaybackContextRes = EntryPlaybackContextRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, playbackContext = pair._1.playbackContext, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)

}


object HourlyAggregationByEntryPlaybackContext extends AggregationByEntryPlaybackContext with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_playbackcontext" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_clst_playbackcontext" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_playbackcontext_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )
}

object MinutelyAggregationByEntryPlaybackContext extends AggregationByEntryPlaybackContext with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_playbackcontext" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_clst_playbackcontext" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_playbackcontext_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByEntryPlaybackContext extends AggregationByEntryPlaybackContext with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry_playbackcontext" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_clst_playbackcontext" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class EntryPlaybackContextRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, playbackContext: String, value: Long)
