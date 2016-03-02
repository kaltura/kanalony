package com.kaltura.aggregations.userActivity.hourly.entry

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.keys.{UserActivityEntryOperatingSystemKey, UserActivityOperatingSystemKey}
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.aggregations.HourlyPartnerOperatingSystem
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_entry_osRow

object HourlyUserActivityByEntryOperatingSystem extends BaseUserActivityAggregation[UserActivityEntryOperatingSystemKey, hourly_ua_prtn_entry_osRow] with IAggregateHourly with Serializable{

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_os" -> columns,
    "hourly_ua_prtn_entry_clst_os" -> columns
  )

  val columns : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "entry_id" as "entryId",
    "operating_system" as "operatingSystem",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryOperatingSystemKey = UserActivityEntryOperatingSystemKey(e.partnerId, e.entryId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy(), e.userAgent.operatingSystem.id)
  override def toRow(pair: (UserActivityEntryOperatingSystemKey, Long)): hourly_ua_prtn_entry_osRow = hourly_ua_prtn_entry_osRow(pair._1.partnerId, pair._1.entryId, pair._1.operatingSystem, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
}
