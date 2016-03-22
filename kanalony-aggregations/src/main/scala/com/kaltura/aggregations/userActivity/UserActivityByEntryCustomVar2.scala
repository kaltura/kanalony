package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityEntryCustomVarKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

abstract class UserActivityByEntryCustomVar2 extends BaseUserActivityAggregation[UserActivityEntryCustomVarKey, EntryCustomVar2Res] with IAggregate with Serializable{


  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("custom_var2","cv2"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCustomVarKey = UserActivityEntryCustomVarKey(e.partnerId, e.entryId, e.customVar2, e.eventType, getAggrTime(e.eventTime))
  override def toRow(pair: (UserActivityEntryCustomVarKey, Long)): EntryCustomVar2Res = EntryCustomVar2Res(partnerId = pair._1.partnerId, entryId = pair._1.entryId, cv2 = pair._1.cv, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)


}

object HourlyUserActivityByEntryCustomVar2 extends UserActivityByEntryCustomVar2 with IAggregateHourly {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_cv2" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_clst_cv2" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_cv2_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )
}

object MinutelyUserActivityByEntryCustomVar2 extends UserActivityByEntryCustomVar2 with IAggregateMinutely {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_cv2" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_clst_cv2" -> toSomeColumns(columns),
    "minutely_ua_prtn_cv2_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsUserActivityByEntryCustomVar2 extends UserActivityByEntryCustomVar2 with IAggregateTenSecs {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_cv2" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_clst_cv2" -> toSomeColumns(columns)
  )
}

case class EntryCustomVar2Res(partnerId: Int, entryId: String, cv2: String, metric: Int, year: Int, month: Int, day: Int, time: DateTime, value: Long)
