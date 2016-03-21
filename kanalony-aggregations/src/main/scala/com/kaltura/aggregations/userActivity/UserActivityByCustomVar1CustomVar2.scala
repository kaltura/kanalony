package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations
import com.kaltura.aggregations.userActivity.HourlyUserActivityByCustomVar1._
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityCustomVar1CustomVar2Key
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByCustomVar1CustomVar2 extends BaseUserActivityAggregation[UserActivityCustomVar1CustomVar2Key, PartnerCustomVar1CustomVar2Res] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("custom_var1","cv1"),
    ("custom_var2","cv2"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): UserActivityCustomVar1CustomVar2Key = UserActivityCustomVar1CustomVar2Key(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.customVar1, e.customVar2)
   override def toRow(pair: (UserActivityCustomVar1CustomVar2Key, Long)): PartnerCustomVar1CustomVar2Res = PartnerCustomVar1CustomVar2Res(pair._1.partnerId, pair._1.cv1, pair._1.cv2, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)
}

object HourlyUserActivityByCustomVar1CustomVar2 extends UserActivityByCustomVar1CustomVar2 with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_cv1_cv2" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_cv1_clst_cv2" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCustomVar1CustomVar2 extends UserActivityByCustomVar1CustomVar2 with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_cv1_cv2" -> toSomeColumns(columns),
    "minutely_ua_prtn_cv1_clst_cv2" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByCustomVar1CustomVar2 extends UserActivityByCustomVar1CustomVar2 with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_cv1_cv2" -> toSomeColumns(columns),
    "tensecs_ua_prtn_cv1_clst_cv2" -> toSomeColumns(columns)
  )
}
case class PartnerCustomVar1CustomVar2Res(partner_id:Int, cv1:String, cv2:String, metric:Int, year:Int, time:DateTime, value:Long)
