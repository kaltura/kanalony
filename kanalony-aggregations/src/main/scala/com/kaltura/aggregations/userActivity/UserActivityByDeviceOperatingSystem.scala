package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityDeviceOperatingSystemKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByDeviceOperatingSystem extends BaseUserActivityAggregation[UserActivityDeviceOperatingSystemKey, PartnerDeviceOperatingSystemRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("device","device"),
    ("operating_system", "operatingSystem"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityDeviceOperatingSystemKey = UserActivityDeviceOperatingSystemKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.userAgent.device.id, e.userAgent.operatingSystem.id)
  override def toRow(pair: (UserActivityDeviceOperatingSystemKey, Long)): PartnerDeviceOperatingSystemRes = PartnerDeviceOperatingSystemRes(partnerId = pair._1.partnerId, device = pair._1.device, operatingSystem = pair._1.operatingSystem, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)


}

object HourlyUserActivityByDeviceOperatingSystem extends UserActivityByDeviceOperatingSystem with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_device_os" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_device_clst_os" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByDeviceOperatingSystem extends UserActivityByDeviceOperatingSystem with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_device_os" -> toSomeColumns(columns),
    "minutely_ua_prtn_device_clst_os" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByDeviceOperatingSystem extends UserActivityByDeviceOperatingSystem with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_device_os" -> toSomeColumns(columns),
    "tensecs_ua_prtn_device_clst_os" -> toSomeColumns(columns)
  )
}
case class PartnerDeviceOperatingSystemRes(partnerId: Int, metric: Int, year: Int, time: DateTime, device: Int, operatingSystem: Int, value: Long)

