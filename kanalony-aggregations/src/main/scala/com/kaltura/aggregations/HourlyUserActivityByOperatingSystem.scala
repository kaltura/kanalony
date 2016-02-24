package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{HourlyPartnerOperatingSystem, HourlyPartnerDevice}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object HourlyUserActivityByOperatingSystem extends BaseEventsAggregation[UserActivityOperatingSystemKey, HourlyPartnerOperatingSystem] with IAggregateHourly {

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_os" -> columns(),
    "hourly_ua_clst_os" -> columns()
    )

  override def trackStateFunc(batchTime: Time, key: UserActivityOperatingSystemKey, value: Option[Long], state: State[Long]): Option[(UserActivityOperatingSystemKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityOperatingSystemKey, Long)] =
    enrichedEvents.map(x => (UserActivityOperatingSystemKey(x.partnerId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy(), x.userAgent.operatingSystem),1L)).reduceByKey(_ + _)

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityOperatingSystemKey, Long, Long, (UserActivityOperatingSystemKey, Long)]): DStream[HourlyPartnerOperatingSystem] =
    aggregatedEvents.map({ case (k,v) => HourlyPartnerOperatingSystem(k.partnerId, k.metric, k.time.getYear, k.time, k.operatingSystem, v)})

  override def ttl(): Int = 60*60 + 5*60

  def columns() : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "operating_system" as "operatingSystem",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")
}
