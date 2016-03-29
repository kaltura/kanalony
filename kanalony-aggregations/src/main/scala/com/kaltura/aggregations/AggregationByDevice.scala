package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationDeviceKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByDevice extends BaseAggregation[AggregationDeviceKey, PartnerDeviceRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("device","device"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationDeviceKey = AggregationDeviceKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.userAgent.device.id)
  override def toRow(pair: (AggregationDeviceKey, Long)): PartnerDeviceRes = PartnerDeviceRes(partnerId = pair._1.partnerId, device = pair._1.device, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)


}

object HourlyAggregationByDevice extends AggregationByDevice with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_device" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_device" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByDevice extends AggregationByDevice with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_device" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_device" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByDevice extends AggregationByDevice with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_device" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_device" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class PartnerDeviceRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, device: Int, value: Long)

