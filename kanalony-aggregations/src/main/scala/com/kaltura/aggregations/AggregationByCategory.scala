package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationCategoryKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.DStream
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits



abstract class AggregationByCategory extends BaseAggregation[AggregationCategoryKey, PartnerCategoryRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("category","category"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]) : DStream[(AggregationCategoryKey,Long)] = {

    enrichedEvents.flatMap(e => e.categories.split(",").map(c => (AggregationCategoryKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), c), 1L))).reduceByKey (_+ _)
  }

  override def aggKey(e: EnrichedPlayerEvent): AggregationCategoryKey = AggregationCategoryKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.categories)
  override def toRow(pair: (AggregationCategoryKey, Long)): PartnerCategoryRes = PartnerCategoryRes(partnerId = pair._1.partnerId, category = pair._1.category, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)

}

object HourlyAggregationByCategory extends AggregationByCategory with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_category" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_category" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByCategory extends AggregationByCategory with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_category" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_category" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByCategory extends AggregationByCategory with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_category" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_category" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerCategoryRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, category: String, value: Long)

