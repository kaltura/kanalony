package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationApplicationKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


/**
 * Created by orlylampert on 3/2/16.
 */
abstract class AggregationByApplication extends BaseAggregation[AggregationApplicationKey, PartnerApplicationRes] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("application","application"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): AggregationApplicationKey = AggregationApplicationKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.application)
  override def toRow(pair: (AggregationApplicationKey, Long)): PartnerApplicationRes = PartnerApplicationRes(partnerId = pair._1.partnerId, application = pair._1.application, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)

}


object HourlyAggregationByApplication extends AggregationByApplication with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_app" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_app" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByApplication extends AggregationByApplication with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_app" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_app" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByApplication extends AggregationByApplication with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_app" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_app" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerApplicationRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, application: String, value: Long)
