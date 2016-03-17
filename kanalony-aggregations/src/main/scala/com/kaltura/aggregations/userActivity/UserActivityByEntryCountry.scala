package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.userActivity.HourlyUserActivityByEntryApplicationPlaybackContext._
import com.kaltura.aggregations.{IAggregateMinutely, IAggregateTenSecs, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityEntryCountryKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class UserActivityByEntryCountry extends BaseUserActivityAggregation[UserActivityEntryCountryKey, EntryCountryRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("country","country"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCountryKey = UserActivityEntryCountryKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.location.country)
  override def toRow(pair: (UserActivityEntryCountryKey, Long)): EntryCountryRes = EntryCountryRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, country = pair._1.country, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)

}

object HourlyUserActivityByEntryCountry extends UserActivityByEntryCountry with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_country" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_clst_country" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_country_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))

  )
}

object MinutelyUserActivityByEntryCountry extends UserActivityByEntryCountry with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_country" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_clst_country" -> toSomeColumns(columns),
    "minutely_ua_prtn_country_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))

  )
}

object TenSecsUserActivityByEntryCountry extends UserActivityByEntryCountry with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_country" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_clst_country" -> toSomeColumns(columns)
  )
}

case class EntryCountryRes(partnerId: Int, entryId: String, metric: Int, year: Int, month: Int, day: Int, time: DateTime, country: String, value: Long)

