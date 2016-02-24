package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{HourlyPartnerDevice, HourlyPartnerCountry}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object HourlyUserActivityByDevice extends BaseEventsAggregation[UserActivityDeviceKey, HourlyPartnerDevice] with IAggregateHourly{

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_device" -> columns(),
    "hourly_ua_clst_device" -> columns()
    )

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityDeviceKey, Long)] =
    enrichedEvents.map(x => (UserActivityDeviceKey(x.partnerId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy(), x.userAgent.device),1L)).reduceByKey(_ + _)

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityDeviceKey, Long, Long, (UserActivityDeviceKey, Long)]): DStream[HourlyPartnerDevice] =
    aggregatedEvents.map({ case (k,v) => HourlyPartnerDevice(k.partnerId, k.metric, k.time.getYear, k.time, k.device, v)})

  override def ttl(): Int = 60*60 + 5*60

  def columns() : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "device" as "device",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")
}
