package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{HourlyPartnerCountryOperatingSystemBrowser, HourlyPartnerOperatingSystemBrowser}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object HourlyUserActivityByCountryOperatingSystemBrowser extends BaseEventsAggregation[UserActivityCountryOperatingSystemBrowserKey, HourlyPartnerCountryOperatingSystemBrowser] with IAggregateHourly {

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_country_clst_os_browser" -> columns(),
    "hourly_ua_prtn_country_os_browser" -> columns()
    )

  override def trackStateFunc(batchTime: Time, key: UserActivityCountryOperatingSystemBrowserKey, value: Option[Long], state: State[Long]): Option[(UserActivityCountryOperatingSystemBrowserKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityCountryOperatingSystemBrowserKey, Long)] =
    enrichedEvents.map(x => (UserActivityCountryOperatingSystemBrowserKey(x.partnerId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy(), x.location.country, x.userAgent.operatingSystem, x.userAgent.browser),1L)).reduceByKey(_ + _)

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityCountryOperatingSystemBrowserKey, Long, Long, (UserActivityCountryOperatingSystemBrowserKey, Long)]): DStream[HourlyPartnerCountryOperatingSystemBrowser] =
    aggregatedEvents.map({ case (k,v) => HourlyPartnerCountryOperatingSystemBrowser(k.partnerId, k.metric, k.time.getYear, k.time, k.country, k.operatingSystem, k.browser, v)})

  override def ttl(): Int = 60*60 + 5*60

  def columns() : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "country" as "country",
    "operating_system" as "operatingSystem",
    "browser" as "browser",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")
}
