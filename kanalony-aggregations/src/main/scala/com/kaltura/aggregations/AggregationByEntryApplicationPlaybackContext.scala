package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryApplicationPlaybackContextKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByEntryApplicationPlaybackContext extends BaseAggregation[AggregationEntryApplicationPlaybackContextKey, EntryApplicationPlaybackContextRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("application","application"),
    ("playback_context","playbackContext"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): AggregationEntryApplicationPlaybackContextKey = AggregationEntryApplicationPlaybackContextKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.application, e.playbackContext)
   override def toRow(pair: (AggregationEntryApplicationPlaybackContextKey, Long)): EntryApplicationPlaybackContextRes = EntryApplicationPlaybackContextRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, application = pair._1.application, playbackContext = pair._1.playbackContext, value = pair._2)
}

object HourlyAggregationByEntryApplicationPlaybackContext extends AggregationByEntryApplicationPlaybackContext with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_app_clst_playbackcontext" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_app_playbackcontext" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_app_playbackcontext_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))

  )
}

object MinutelyAggregationByEntryApplicationPlaybackContext extends AggregationByEntryApplicationPlaybackContext with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_app_clst_playbackcontext" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_app_playbackcontext" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_app_playbackcontext_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByEntryApplicationPlaybackContext extends AggregationByEntryApplicationPlaybackContext with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry_app_clst_playbackcontext" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_app_playbackcontext" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class EntryApplicationPlaybackContextRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, application: String, playbackContext: String, value: Long)




