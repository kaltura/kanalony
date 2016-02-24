package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{HourlyPartnerOperatingSystemBrowser, HourlyPartnerCountryOperatingSystemBrowser, HourlyPartnerDeviceOperatingSystem}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object HourlyUserActivityByOperatingSystemBrowser extends BaseEventsAggregation[UserActivityOperatingSystemBrowserKey, HourlyPartnerOperatingSystemBrowser] with IAggregateHourly {

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_os_clst_browser" -> columns(),
    "hourly_ua_prtn_os_browser" -> columns()
    )

  override def trackStateFunc(batchTime: Time, key: UserActivityOperatingSystemBrowserKey, value: Option[Long], state: State[Long]): Option[(UserActivityOperatingSystemBrowserKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityOperatingSystemBrowserKey, Long)] =
    enrichedEvents.map(x => (UserActivityOperatingSystemBrowserKey(x.partnerId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy(), x.userAgent.operatingSystem, x.userAgent.browser),1L)).reduceByKey(_ + _)

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityOperatingSystemBrowserKey, Long, Long, (UserActivityOperatingSystemBrowserKey, Long)]): DStream[HourlyPartnerOperatingSystemBrowser] =
    aggregatedEvents.map({ case (k,v) => HourlyPartnerOperatingSystemBrowser(k.partnerId, k.metric, k.time.getYear, k.time, k.operatingSystem, k.browser, v)})

  override def ttl(): Int = 60*60 + 5*60

  def columns() : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "operating_system" as "operatingSystem",
    "browser" as "browser",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")
}
