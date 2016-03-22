package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.userActivity.MinutelyUserActivityByEntryCountry._
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityEntryCustomVarKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class UserActivityByEntryCustomVar1 extends BaseUserActivityAggregation[UserActivityEntryCustomVarKey, EntryCustomVar1Res] with IAggregate with Serializable{


  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("custom_var1","cv1"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCustomVarKey = UserActivityEntryCustomVarKey(e.partnerId, e.entryId, e.customVar1, e.eventType, getAggrTime(e.eventTime))
  override def toRow(pair: (UserActivityEntryCustomVarKey, Long)): EntryCustomVar1Res = EntryCustomVar1Res(partnerId = pair._1.partnerId, entryId = pair._1.entryId, cv1 = pair._1.cv, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)


}

object HourlyUserActivityByEntryCustomVar1 extends UserActivityByEntryCustomVar1 with IAggregateHourly {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_cv1" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_clst_cv1" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_cv1_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))

  )
}

object MinutelyUserActivityByEntryCustomVar1 extends UserActivityByEntryCustomVar1 with IAggregateMinutely {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_cv1" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_clst_cv1" -> toSomeColumns(columns),
    "minutely_ua_prtn_cv1_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsUserActivityByEntryCustomVar1 extends UserActivityByEntryCustomVar1 with IAggregateTenSecs {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_cv1" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_clst_cv1" -> toSomeColumns(columns)
  )
}

case class EntryCustomVar1Res(partnerId: Int, entryId: String, cv1: String, metric: Int, year: Int, month: Int, day: Int, time: DateTime, value: Long)
