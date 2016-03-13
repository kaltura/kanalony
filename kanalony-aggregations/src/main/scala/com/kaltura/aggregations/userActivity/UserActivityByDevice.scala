package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityDeviceKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByDevice extends BaseUserActivityAggregation[UserActivityDeviceKey, PartnerDeviceRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("device","device"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityDeviceKey = UserActivityDeviceKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.userAgent.device.id)
  override def toRow(pair: (UserActivityDeviceKey, Long)): PartnerDeviceRes = PartnerDeviceRes(partnerId = pair._1.partnerId, device = pair._1.device, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)


}

object HourlyUserActivityByDevice extends UserActivityByDevice with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_device" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_device" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByDevice extends UserActivityByDevice with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_device" -> toSomeColumns(columns),
    "minutely_ua_clst_device" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByDevice extends UserActivityByDevice with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_device" -> toSomeColumns(columns),
    "tensecs_ua_clst_device" -> toSomeColumns(columns)
  )
}
case class PartnerDeviceRes(partnerId: Int, metric: Int, year: Int, time: DateTime, device: Int, value: Long)

