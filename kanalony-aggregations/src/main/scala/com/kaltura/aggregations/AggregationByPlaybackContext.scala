package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationPlaybackContextKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByPlaybackContext extends BaseAggregation[AggregationPlaybackContextKey, PartnerPlaybackContextRes] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("playback_context","playbackContext"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): AggregationPlaybackContextKey = AggregationPlaybackContextKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.playbackContext)
  override def toRow(pair: (AggregationPlaybackContextKey, Long)): PartnerPlaybackContextRes = PartnerPlaybackContextRes(partnerId = pair._1.partnerId, playbackContext = pair._1.playbackContext, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)

}


object HourlyAggregationByPlaybackContext extends AggregationByPlaybackContext with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_playbackcontext" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_playbackcontext" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByPlaybackContext extends AggregationByPlaybackContext with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_playbackcontext" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_playbackcontext" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByPlaybackContext extends AggregationByPlaybackContext with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_playbackcontext" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_playbackcontext" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerPlaybackContextRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, playbackContext: String, value: Long)
