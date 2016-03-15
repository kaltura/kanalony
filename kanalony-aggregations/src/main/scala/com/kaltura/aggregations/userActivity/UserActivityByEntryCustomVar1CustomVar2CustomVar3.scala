package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityEntryCustomVar1CustomVar2CustomVar3Key
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByEntryCustomVar1CustomVar2CustomVar3 extends BaseUserActivityAggregation[UserActivityEntryCustomVar1CustomVar2CustomVar3Key, EntryCustomVar1CustomVar2CustomVar3Res] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("entry_id","entryId"),
    ("custom_var1","cv1"),
    ("custom_var2","cv2"),
    ("custom_var3","cv3"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


   override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryCustomVar1CustomVar2CustomVar3Key = UserActivityEntryCustomVar1CustomVar2CustomVar3Key(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.customVar1, e.customVar2, e.customVar3)
   override def toRow(pair: (UserActivityEntryCustomVar1CustomVar2CustomVar3Key, Long)): EntryCustomVar1CustomVar2CustomVar3Res = EntryCustomVar1CustomVar2CustomVar3Res(pair._1.partnerId, pair._1.entryId,  pair._1.cv1, pair._1.cv2, pair._1.cv3, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
}

object HourlyUserActivityByEntryCustomVar1CustomVar2CustomVar3 extends UserActivityByEntryCustomVar1CustomVar2CustomVar3 with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_cv1_cv2_cv3" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_cv1_cv2_clst_cv3" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_cv1_cv2_cv3_clst_entry" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByEntryCustomVar1CustomVar2CustomVar3 extends UserActivityByEntryCustomVar1CustomVar2CustomVar3 with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_cv1_cv2_cv3" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_cv1_cv2_clst_cv3" -> toSomeColumns(columns),
    "minutely_ua_prtn_cv1_cv2_cv3_clst_entry" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByEntryCustomVar1CustomVar2CustomVar3 extends UserActivityByEntryCustomVar1CustomVar2CustomVar3 with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_cv1_cv2_cv3" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_cv1_cv2_clst_cv3" -> toSomeColumns(columns)
  )
}

case class EntryCustomVar1CustomVar2CustomVar3Res(partner_id:Int, entryId: String,  cv1:String, cv2:String, cv3:String, metric:Int, year:Int, time:DateTime, value:Long)

