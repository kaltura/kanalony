package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{HourlyPartnerDeviceOperatingSystem, HourlyPartnerCountryCity}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object HourlyUserActivityByDeviceOperatingSystem extends BaseEventsAggregation[UserActivityDeviceOperatingSystemKey, HourlyPartnerDeviceOperatingSystem] with IAggregateHourly{

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_device_clst_os" -> columns(),
    "hourly_ua_prtn_device_os" -> columns()
    )

  override def trackStateFunc(batchTime: Time, key: UserActivityDeviceOperatingSystemKey, value: Option[Long], state: State[Long]): Option[(UserActivityDeviceOperatingSystemKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityDeviceOperatingSystemKey, Long)] =
    enrichedEvents.map(x => (UserActivityDeviceOperatingSystemKey(x.partnerId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy(), x.userAgent.device, x.userAgent.operatingSystem),1L)).reduceByKey(_ + _)

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityDeviceOperatingSystemKey, Long, Long, (UserActivityDeviceOperatingSystemKey, Long)]): DStream[HourlyPartnerDeviceOperatingSystem] =
    aggregatedEvents.map({ case (k,v) => HourlyPartnerDeviceOperatingSystem(k.partnerId, k.metric, k.time.getYear, k.time, k.device, k.operatingSystem, v)})

  override def ttl(): Int = 60*60 + 5*60

  def columns() : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "device" as "device",
    "operating_system" as "operatingSystem",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")
}
