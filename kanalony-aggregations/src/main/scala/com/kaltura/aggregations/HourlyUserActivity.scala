package com.kaltura.aggregations

import com.datastax.spark.connector._
import com.kaltura.aggregations.keys.UserActivityKey
import com.kaltura.model.aggregations.HourlyPartner
import com.kaltura.model.events.EnrichedPlayerEvent

object HourlyUserActivity extends BaseUserActivityAggregation[UserActivityKey, HourlyPartner] with IAggregateHourly with Serializable {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua" -> SomeColumns(
      "partner_id" as "partnerId",
      "metric" as "metric",
      "hour" as "hour",
      "year" as "year",
      "value" as "value"))

  override def aggKey(e:EnrichedPlayerEvent) = UserActivityKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy())
  override def toRow(pair:(UserActivityKey,Long)) = HourlyPartner(pair._1.partnerId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)

}
