package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryApplicationKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByEntryApplication extends BaseAggregation[AggregationEntryApplicationKey, EntryApplicationRes] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("application","application"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryApplicationKey = AggregationEntryApplicationKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.application)
  override def toRow(pair: (AggregationEntryApplicationKey, Long)): EntryApplicationRes = EntryApplicationRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, application = pair._1.application, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)

}


object HourlyAggregationByEntryApplication extends AggregationByEntryApplication with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_app" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_clst_app" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_app_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))

  )
}

object MinutelyAggregationByEntryApplication extends AggregationByEntryApplication with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_app" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_clst_app" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_app_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))

  )
}

object TenSecsAggregationByEntryApplication extends AggregationByEntryApplication with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry_app" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_clst_app" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class EntryApplicationRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, application: String, value: Long)
