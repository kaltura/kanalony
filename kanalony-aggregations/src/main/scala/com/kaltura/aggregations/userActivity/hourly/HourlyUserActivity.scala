package com.kaltura.aggregations.userActivity.hourly

import com.datastax.spark.connector._
import com.kaltura.aggregations.keys.UserActivityKey
import com.kaltura.aggregations.IAggregateHourly
import com.kaltura.aggregations.userActivity.BaseUserActivityAggregation
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_Row

object HourlyUserActivity extends BaseUserActivityAggregation[UserActivityKey, hourly_ua_Row] with IAggregateHourly with Serializable {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua" -> SomeColumns(
      "partner_id" as "partnerId",
      "metric" as "metric",
      "hour" as "hour",
      "year" as "year",
      "value" as "value"))

  override def aggKey(e:EnrichedPlayerEvent) = UserActivityKey(e.partnerId, e.eventType, e.eventTime.hourOfDay().roundFloorCopy())
  override def toRow(pair:(UserActivityKey,Long)) = hourly_ua_Row(pair._1.partnerId, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)

}
