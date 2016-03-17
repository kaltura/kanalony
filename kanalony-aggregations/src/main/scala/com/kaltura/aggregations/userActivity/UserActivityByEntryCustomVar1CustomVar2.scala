package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityEntryCustomVar1CustomVar2Key
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class UserActivityByEntryCustomVar1CustomVar2 extends BaseUserActivityAggregation[UserActivityEntryCustomVar1CustomVar2Key, EntryCustomVar1CustomVar2Res] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("custom_var1","cv1"),
    ("custom_var2","cv2"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCustomVar1CustomVar2Key = UserActivityEntryCustomVar1CustomVar2Key(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.customVar1, e.customVar2)
   override def toRow(pair: (UserActivityEntryCustomVar1CustomVar2Key, Long)): EntryCustomVar1CustomVar2Res = EntryCustomVar1CustomVar2Res(partnerId = pair._1.partnerId, entryId = pair._1.entryId, cv1 = pair._1.cv1, cv2 = pair._1.cv2, metric = pair._1.metric, year = pair._1.time getYear, month = pair._1.time getYearMonth, day = pair._1.time getYearMonthDay, time = pair._1.time, value = pair._2)
}

object HourlyUserActivityByEntryCustomVar1CustomVar2 extends UserActivityByEntryCustomVar1CustomVar2 with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_cv1_cv2" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_cv1_clst_cv2" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_cv1_cv2_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))

  )
}

object MinutelyUserActivityByEntryCustomVar1CustomVar2 extends UserActivityByEntryCustomVar1CustomVar2 with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_cv1_cv2" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_cv1_clst_cv2" -> toSomeColumns(columns),
    "minutely_ua_prtn_cv1_cv2_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))

  )
}

object TenSecsUserActivityByEntryCustomVar1CustomVar2 extends UserActivityByEntryCustomVar1CustomVar2 with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_cv1_cv2" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_cv1_clst_cv2" -> toSomeColumns(columns)
  )
}
case class EntryCustomVar1CustomVar2Res(partnerId:Int, entryId: String, cv1:String, cv2:String, metric:Int, year:Int, month: Int, day: Int, time:DateTime, value:Long)
