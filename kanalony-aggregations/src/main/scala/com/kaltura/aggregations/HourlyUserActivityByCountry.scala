package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{HourlyPartnerCountry, HourlyPartner}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object HourlyUserActivityByCountry extends BaseEventsAggregation[UserActivityCountryKey, HourlyPartnerCountry] with IAggregateHourly{

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_country" -> columns(),
    "hourly_ua_clst_country" -> columns()
    )

  override def trackStateFunc(batchTime: Time, key: UserActivityCountryKey, value: Option[Long], state: State[Long]): Option[(UserActivityCountryKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityCountryKey, Long)] =
    enrichedEvents.map(x => (UserActivityCountryKey(x.partnerId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy(), x.location.country),1L)).reduceByKey(_ + _)

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityCountryKey, Long, Long, (UserActivityCountryKey, Long)]): DStream[HourlyPartnerCountry] =
    aggregatedEvents.map({ case (k,v) => HourlyPartnerCountry(k.partnerId, k.metric, k.time.getYear, k.time, k.country, v)})

  override def ttl(): Int = 60*60 + 5*60

  def columns() : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "country" as "country",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")
}
