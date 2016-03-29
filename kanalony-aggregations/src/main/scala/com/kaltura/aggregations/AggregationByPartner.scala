package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

/**
 * Created by orlylampert on 3/2/16.
 */
abstract class AggregationByPartner extends BaseAggregation[AggregationKey, PartnerRes] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): AggregationKey = AggregationKey(e.partnerId, e.eventType, getAggrTime(e.eventTime))
  override def toRow(pair: (AggregationKey, Long)): PartnerRes = PartnerRes(partnerId = pair._1.partnerId, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)

}


object HourlyAggregationByPartner extends AggregationByPartner with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByPartner extends AggregationByPartner with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByPartner extends AggregationByPartner with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, value: Long)
