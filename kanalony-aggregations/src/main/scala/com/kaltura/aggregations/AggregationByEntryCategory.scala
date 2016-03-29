package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryCategoryKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.DStream
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByEntryCategory extends BaseAggregation[AggregationEntryCategoryKey, EntryCategoryRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("category","category"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]) : DStream[(AggregationEntryCategoryKey,Long)] = {
    enrichedEvents.flatMap(e => e.categories.split(",").map(c => (AggregationEntryCategoryKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), c), 1L))).reduceByKey (_+ _)
  }

  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryCategoryKey = AggregationEntryCategoryKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.categories)
  override def toRow(pair: (AggregationEntryCategoryKey, Long)): EntryCategoryRes = EntryCategoryRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, category = pair._1.category, metric = pair._1.metric, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)

}

object HourlyAggregationByEntryCategory extends AggregationByEntryCategory with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_category_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )
}

object MinutelyAggregationByEntryCategory extends AggregationByEntryCategory with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_prtn_category_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByEntryCategory extends AggregationByEntryCategory with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_prtn_category_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class EntryCategoryRes(partnerId: Int, entryId: String, metric: String, month: Int, day: Int, time: DateTime, category: String, value: Long)

