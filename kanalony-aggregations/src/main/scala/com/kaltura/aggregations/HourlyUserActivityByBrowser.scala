package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{HourlyPartnerBrowser, HourlyPartnerDevice}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object HourlyUserActivityByBrowser extends BaseEventsAggregation[UserActivityBrowserKey, HourlyPartnerBrowser] {

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_browser" -> columns(),
    "hourly_ua_clst_browser" -> columns()
    )

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityBrowserKey, Long)] = {
    enrichedEvents.map(x => (UserActivityBrowserKey(x.partnerId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy(), x.userAgent.browser), 1L)).reduceByKey(_ + _)
  }
  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityBrowserKey, Long, Long, (UserActivityBrowserKey, Long)]): DStream[HourlyPartnerBrowser] =
    aggregatedEvents.map({ case (k,v) => HourlyPartnerBrowser(k.partnerId, k.metric, k.time.getYear, k.time, k.browser, v)})

  override def ttl(): Int = 60*60 + 5*60

  def columns() : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "browser" as "browser",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")
}
