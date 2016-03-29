package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByEntry extends BaseAggregation[AggregationEntryKey, PartnerEntryRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  // TODO - dropped month column temporarily
  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryKey = AggregationEntryKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime))
  override def toRow(pair: (AggregationEntryKey, Long)): PartnerEntryRes = PartnerEntryRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, metric = pair._1.metric, month = pair._1.time getYearMonth ,day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)
}

object HourlyAggregationByEntry extends AggregationByEntry with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry" -> toSomeColumns(columns :+ ("month", "month")),
    "hourly_agg_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )

}

object MinutelyAggregationByEntry extends AggregationByEntry with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByEntry extends AggregationByEntry with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerEntryRes(partnerId: Int, entryId: String, metric: String, month: Int, day: Int, time: DateTime, value: Long)

