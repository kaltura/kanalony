package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationApplicationPlaybackContextKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByApplicationPlaybackContext extends BaseAggregation[AggregationApplicationPlaybackContextKey, PartnerApplicationPlaybackContextRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("application","application"),
    ("playback_context","playbackContext"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): AggregationApplicationPlaybackContextKey = AggregationApplicationPlaybackContextKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.application, e.playbackContext)
   override def toRow(pair: (AggregationApplicationPlaybackContextKey, Long)): PartnerApplicationPlaybackContextRes = PartnerApplicationPlaybackContextRes(partnerId = pair._1.partnerId, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, application = pair._1.application, playbackContext = pair._1.playbackContext, value = pair._2)
}

object HourlyAggregationByApplicationPlaybackContext extends AggregationByApplicationPlaybackContext with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_app_clst_playbackcontext" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_app_playbackcontext" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByApplicationPlaybackContext extends AggregationByApplicationPlaybackContext with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_app_clst_playbackcontext" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_app_playbackcontext" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByApplicationPlaybackContext extends AggregationByApplicationPlaybackContext with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_app_clst_playbackcontext" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_app_playbackcontext" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerApplicationPlaybackContextRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, application: String, playbackContext: String, value: Long)




