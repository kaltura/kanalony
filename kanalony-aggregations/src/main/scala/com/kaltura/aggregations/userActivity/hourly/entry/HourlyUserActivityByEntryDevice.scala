package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryDeviceKey, UserActivityDeviceKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerDevice
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_entry_deviceRow

object HourlyUserActivityByEntryDevice extends BaseUserActivityAggregation[UserActivityEntryDeviceKey, hourly_ua_prtn_entry_deviceRow] with IAggregateHourly with Serializable{

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_device" -> columns,
    "hourly_ua_prtn_entry_clst_device" -> columns
  )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "entry_id" as "entryId",
    "device" as "device",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryDeviceKey = UserActivityEntryDeviceKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.userAgent.device.id)
  override def toRow(pair: (UserActivityEntryDeviceKey, Long)): hourly_ua_prtn_entry_deviceRow = hourly_ua_prtn_entry_deviceRow(pair._1.partnerId, pair._1.entryId, pair._1.device.toString, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
}
