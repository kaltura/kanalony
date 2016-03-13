package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityCustomVar1CustomVar2CustomVar3Key
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByCustomVar1CustomVar2CustomVar3 extends BaseUserActivityAggregation[UserActivityCustomVar1CustomVar2CustomVar3Key, PartnerCustomVar1CustomVar2CustomVar3Res] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("custom_var1","cv1"),
    ("custom_var2","cv2"),
    ("custom_var3","cv3"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


   override def aggKey(e: EnrichedPlayerEvent): UserActivityCustomVar1CustomVar2CustomVar3Key = UserActivityCustomVar1CustomVar2CustomVar3Key(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.customVar1, e.customVar2, e.customVar3)
   override def toRow(pair: (UserActivityCustomVar1CustomVar2CustomVar3Key, Long)): PartnerCustomVar1CustomVar2CustomVar3Res = PartnerCustomVar1CustomVar2CustomVar3Res(pair._1.partnerId, pair._1.cv1, pair._1.cv2, pair._1.cv3, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
}

object HourlyUserActivityByCustomVar1CustomVar2CustomVar3 extends UserActivityByCustomVar1CustomVar2CustomVar3 with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_cv1_cv2_cv3" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_cv1_cv2_clst_cv3" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCustomVar1CustomVar2CustomVar3 extends UserActivityByCustomVar1CustomVar2CustomVar3 with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_cv1_cv2_cv3" -> toSomeColumns(columns),
    "minutely_ua_prtn_cv1_cv2_clst_cv3" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByCustomVar1CustomVar2CustomVar3 extends UserActivityByCustomVar1CustomVar2CustomVar3 with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_cv1_cv2_cv3" -> toSomeColumns(columns),
    "tensecs_ua_prtn_cv1_cv2_clst_cv3" -> toSomeColumns(columns)
  )
}

case class PartnerCustomVar1CustomVar2CustomVar3Res(partner_id:Int, cv1:String, cv2:String, cv3:String, metric:Int, year:Int, time:DateTime, value:Long)

