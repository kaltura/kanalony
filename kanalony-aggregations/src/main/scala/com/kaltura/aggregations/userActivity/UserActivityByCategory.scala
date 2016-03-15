package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateMinutely, IAggregateTenSecs, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityCategoryKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.DStream
import org.joda.time.DateTime


abstract class UserActivityByCategory extends BaseUserActivityAggregation[UserActivityCategoryKey, PartnerCategoryRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("category","category"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]) : DStream[(UserActivityCategoryKey,Long)] = {

    enrichedEvents.flatMap(e => e.categories.split(",").map(c => (UserActivityCategoryKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), c), 1L))).reduceByKey (_+ _)
  }

  override def aggKey(e: EnrichedPlayerEvent): UserActivityCategoryKey = UserActivityCategoryKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.categories)
  override def toRow(pair: (UserActivityCategoryKey, Long)): PartnerCategoryRes = PartnerCategoryRes(partnerId = pair._1.partnerId, category = pair._1.category, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)

}

object HourlyUserActivityByCategory extends UserActivityByCategory with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_category" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_category" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCategory extends UserActivityByCategory with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_category" -> toSomeColumns(columns),
    "minutely_ua_clst_category" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByCategory extends UserActivityByCategory with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_category" -> toSomeColumns(columns),
    "tensecs_ua_clst_category" -> toSomeColumns(columns)
  )
}

case class PartnerCategoryRes(partnerId: Int, metric: Int, year: Int, time: DateTime, category: String, value: Long)

