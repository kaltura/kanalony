package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{HourlyPartnerCountryCity, HourlyPartnerCountry}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object HourlyUserActivityByCountryCity extends BaseEventsAggregation[UserActivityCountryCityKey, HourlyPartnerCountryCity] with IAggregateHourly {

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_country_city" -> columns(),
    "hourly_ua_clst_country_city" -> columns()
    )

  override def trackStateFunc(batchTime: Time, key: UserActivityCountryCityKey, value: Option[Long], state: State[Long]): Option[(UserActivityCountryCityKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityCountryCityKey, Long)] =
    enrichedEvents.map(x => (UserActivityCountryCityKey(x.partnerId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy(), x.location.country, x.location.city),1L)).reduceByKey(_ + _)

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityCountryCityKey, Long, Long, (UserActivityCountryCityKey, Long)]): DStream[HourlyPartnerCountryCity] =
    aggregatedEvents.map({ case (k,v) => HourlyPartnerCountryCity(k.partnerId, k.metric, k.time.getYear, k.time, k.country, k.city, v)})

  override def ttl(): Int = 60*60 + 5*60

  def columns() : SomeColumns = new SomeColumns(
    "partner_id" as "partnerId",
    "country" as "country",
    "city" as "city",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")
}
