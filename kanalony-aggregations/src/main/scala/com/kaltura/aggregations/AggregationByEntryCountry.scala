package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryCountryKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByEntryCountry extends BaseAggregation[AggregationEntryCountryKey, EntryCountryRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("country","country"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryCountryKey = AggregationEntryCountryKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.location.country)
  override def toRow(pair: (AggregationEntryCountryKey, Long)): EntryCountryRes = EntryCountryRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, country = pair._1.country, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)

}

object HourlyAggregationByEntryCountry extends AggregationByEntryCountry with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_country" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_clst_country" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_country_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))

  )
}

object MinutelyAggregationByEntryCountry extends AggregationByEntryCountry with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_country" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_clst_country" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_country_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))

  )
}

object TenSecsAggregationByEntryCountry extends AggregationByEntryCountry with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry_country" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_clst_country" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class EntryCountryRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, country: String, value: Long)

