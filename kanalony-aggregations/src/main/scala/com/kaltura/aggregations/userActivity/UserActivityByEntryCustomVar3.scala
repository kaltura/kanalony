package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.userActivity.HourlyUserActivityByEntryCustomVar1._
import com.kaltura.aggregations.userActivity.MinutelyUserActivityByEntryCustomVar1._
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityEntryCustomVarKey, UserActivityCustomVarKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByEntryCustomVar3 extends BaseUserActivityAggregation[UserActivityEntryCustomVarKey, EntryCustomVar3] with IAggregate with Serializable{


  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("custom_var3","cv3"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCustomVarKey = UserActivityEntryCustomVarKey(e.partnerId, e.entryId, e.customVar3, e.eventType, getAggrTime(e.eventTime))
  override def toRow(pair: (UserActivityEntryCustomVarKey, Long)): EntryCustomVar3 = EntryCustomVar3(pair._1.partnerId, pair._1.entryId, pair._1.cv, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)


}

object HourlyUserActivityByEntryCustomVar3 extends UserActivityByEntryCustomVar3 with IAggregateHourly {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_cv3" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_clst_cv3" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_cv3_clst_entry" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByEntryCustomVar3 extends UserActivityByEntryCustomVar3 with IAggregateMinutely {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_cv3" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_clst_cv3" -> toSomeColumns(columns),
    "minutely_ua_prtn_cv3_clst_entry" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByEntryCustomVar3 extends UserActivityByEntryCustomVar3 with IAggregateTenSecs {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_cv3" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_clst_cv3" -> toSomeColumns(columns)
  )
}

case class EntryCustomVar3(partnerId: Int, entryId: String, cv3: String, metric: Int, year: Int, time: DateTime, value: Long)
