package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationEntryCountryCityKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class AggregationByEntryCountryCity extends BaseAggregation[AggregationEntryCountryCityKey, EntryCountryCityRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("country","country"),
    ("city","city"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationEntryCountryCityKey = AggregationEntryCountryCityKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.location.country, e.location.city)
  override def toRow(pair: (AggregationEntryCountryCityKey, Long)): EntryCountryCityRes = EntryCountryCityRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, country = pair._1.country, city = pair._1.city, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)
}

object HourlyAggregationByEntryCountryCity extends AggregationByEntryCountryCity with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_entry_country_city" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_clst_country_city" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_entry_country_clst_city" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_country_city_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )
}

object MinutelyAggregationByEntryCountryCity extends AggregationByEntryCountryCity with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_entry_country_city" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_clst_country_city" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_entry_country_clst_city" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_country_city_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))

  )
}

object TenSecsAggregationByEntryCountryCity extends AggregationByEntryCountryCity with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_entry_country_city" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_clst_country_city" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_entry_country_clst_city" -> toSomeColumns(columns :+ ("day", "day"))
  )
}


case class EntryCountryCityRes(partnerId: Int, entryId: String, metric: String, year: Int, month: Int, day: Int, time: DateTime, country: String, city: String, value: Long)



