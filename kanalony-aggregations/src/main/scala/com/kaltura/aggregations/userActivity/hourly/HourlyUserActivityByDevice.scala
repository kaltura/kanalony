package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityDeviceKey
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_deviceRow

object HourlyUserActivityByDevice extends BaseUserActivityAggregation[UserActivityDeviceKey, hourly_ua_prtn_deviceRow] with IAggregateHourly with Serializable{

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_device" -> columns,
    "hourly_ua_clst_device" -> columns
  )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "device" as "device",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityDeviceKey = UserActivityDeviceKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.userAgent.device.id)
  override def toRow(pair: (UserActivityDeviceKey, Long)): hourly_ua_prtn_deviceRow = hourly_ua_prtn_deviceRow(pair._1.partnerId, pair._1.device, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
}
