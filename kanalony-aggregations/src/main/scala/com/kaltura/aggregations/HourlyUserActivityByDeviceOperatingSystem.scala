package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityDeviceOperatingSystemKey
import com.kaltura.model.aggregations.HourlyPartnerDeviceOperatingSystem
import com.kaltura.model.events.EnrichedPlayerEvent

object HourlyUserActivityByDeviceOperatingSystem extends BaseUserActivityAggregation[UserActivityDeviceOperatingSystemKey, HourlyPartnerDeviceOperatingSystem] with IAggregateHourly with Serializable {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_device_clst_os" -> columns,
    "hourly_ua_prtn_device_os" -> columns
  )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "device" as "device",
    "operating_system" as "operatingSystem",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityDeviceOperatingSystemKey = UserActivityDeviceOperatingSystemKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.userAgent.device, e.userAgent.operatingSystem)
  override def toRow(pair: (UserActivityDeviceOperatingSystemKey, Long)): HourlyPartnerDeviceOperatingSystem = HourlyPartnerDeviceOperatingSystem(pair._1.partnerId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._1.device, pair._1.operatingSystem, pair._2)
}
