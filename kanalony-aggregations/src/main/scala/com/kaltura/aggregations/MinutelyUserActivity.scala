package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.UserActivityKey
import com.kaltura.model.aggregations.MinutelyPartner
import com.kaltura.model.events.EnrichedPlayerEvent


object MinutelyUserActivity extends BaseUserActivityAggregation[UserActivityKey, MinutelyPartner] with IAggregateMinutely with Serializable{

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua" -> SomeColumns(
      "partner_id" as "partnerId",
      "metric" as "metric",
      "minute" as "minute",
      "value" as "value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityKey = UserActivityKey(e.partnerId, e.eventType, e.eventTime.minuteOfHour().roundFloorCopy())
  override def toRow(pair: (UserActivityKey, Long)): MinutelyPartner = MinutelyPartner(pair._1.partnerId, pair._1.metric, pair._1.time, pair._2)
}
