package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.model.aggregations.{HourlyPartnerDomainReferrer, HourlyPartnerCountryCity}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.apache.spark.streaming.{State, Time}


object HourlyUserActivityByDomainReferrer extends BaseEventsAggregation[UserActivityDomainReferrerKey, HourlyPartnerDomainReferrer] with IAggregateHourly {

  override def tableMetadata(): Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_referrer" -> columns(),
    "hourly_ua_clst_referrer" -> columns(),
    "hourly_ua_prtn_domain_clst_referrer" -> SomeColumns(
      "partner_id" as "partnerId",
      "domain" as "domain",
      "referrer" as "referrer",
      "metric" as "metric",
      "hour" as "hour",
      "year" as "year",
      "value" as "value")
    )

  /*override def trackStateFunc(batchTime: Time, key: UserActivityDomainReferrerKey, value: Option[Long], state: State[Long]): Option[(UserActivityDomainReferrerKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }*/

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(UserActivityDomainReferrerKey, Long)] =
    enrichedEvents.map(x => (UserActivityDomainReferrerKey(x.partnerId, x.eventType, x.eventTime.hourOfDay().roundFloorCopy(), x.urlParts.domain, x.urlParts.canonicalUrl),1L)).reduceByKey(_ + _)

  override def prepareForSave(aggregatedEvents: MapWithStateDStream[UserActivityDomainReferrerKey, Long, Long, (UserActivityDomainReferrerKey, Long)]): DStream[HourlyPartnerDomainReferrer] =
    aggregatedEvents.map({ case (k,v) => HourlyPartnerDomainReferrer(k.partnerId, k.metric, k.time.getYear, k.time, k.domain, k.referrer, v)})

  override def ttl(): Int = 60*60 + 5*60

  def columns() : SomeColumns = SomeColumns(
    "partner_id" as "partnerId",
    "referrer" as "referrer",
    "metric" as "metric",
    "hour" as "hour",
    "year" as "year",
    "value" as "value")
}
