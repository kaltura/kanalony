package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateMinutely, IAggregateTenSecs, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityEntryCategoryKey, UserActivityCategoryKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.DStream
import org.joda.time.DateTime


abstract class UserActivityByEntryCategory extends BaseUserActivityAggregation[UserActivityEntryCategoryKey, EntryCategoryRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("category","category"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]) : DStream[(UserActivityEntryCategoryKey,Long)] = {
    enrichedEvents.flatMap(e => e.categories.split(",").map(c => (UserActivityEntryCategoryKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), c), 1L))).reduceByKey (_+ _)
  }

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCategoryKey = UserActivityEntryCategoryKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.categories)
  override def toRow(pair: (UserActivityEntryCategoryKey, Long)): EntryCategoryRes = EntryCategoryRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, category = pair._1.category, metric = pair._1.metric, month = (pair._1.time.getYear*100 + pair._1.time.getMonthOfYear), time = pair._1.time, value = pair._2)

}

object HourlyUserActivityByEntryCategory extends UserActivityByEntryCategory with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_category_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )
}

object MinutelyUserActivityByEntryCategory extends UserActivityByEntryCategory with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_prtn_category_clst_entry" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByEntryCategory extends UserActivityByEntryCategory with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_prtn_category_clst_entry" -> toSomeColumns(columns)
  )
}

case class EntryCategoryRes(partnerId: Int, entryId: String, metric: Int, month: Int, time: DateTime, category: String, value: Long)

