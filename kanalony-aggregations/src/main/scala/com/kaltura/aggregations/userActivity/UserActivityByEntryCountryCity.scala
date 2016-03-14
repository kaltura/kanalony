package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateMinutely, IAggregateTenSecs, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityEntryCountryCityKey, UserActivityCountryCityKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByEntryCountryCity extends BaseUserActivityAggregation[UserActivityEntryCountryCityKey, EntryCountryCity] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("country","country"),
    ("city","city"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCountryCityKey = UserActivityEntryCountryCityKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.location.country, e.location.city)
  override def toRow(pair: (UserActivityEntryCountryCityKey, Long)): EntryCountryCity = EntryCountryCity(partnerId = pair._1.partnerId, entryId = pair._1.entryId, country = pair._1.country, city = pair._1.city, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)
}

object HourlyUserActivityByEntryCountryCity extends UserActivityByEntryCountryCity with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_country_city" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_clst_country_city" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_country_clst_city" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByEntryCountryCity extends UserActivityByEntryCountryCity with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_country_city" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_clst_country_city" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_country_clst_city" -> toSomeColumns(columns)

  )
}

object TenSecsUserActivityByEntryCountryCity extends UserActivityByEntryCountryCity with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_country_city" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_clst_country_city" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_country_clst_city" -> toSomeColumns(columns)
  )
}


case class EntryCountryCity(partnerId: Int, entryId: String, metric: Int, year: Int, time: DateTime, country: String, city: String, value: Long)



