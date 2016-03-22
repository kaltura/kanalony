package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityCustomVarKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByCustomVar1 extends BaseUserActivityAggregation[UserActivityCustomVarKey, PartnerCustomVar1Res] with IAggregate with Serializable{


  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("custom_var1","cv1"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityCustomVarKey = UserActivityCustomVarKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.customVar1)
  override def toRow(pair: (UserActivityCustomVarKey, Long)): PartnerCustomVar1Res = PartnerCustomVar1Res(pair._1.partnerId, pair._1.cv, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)


}

object HourlyUserActivityByCustomVar1 extends UserActivityByCustomVar1 with IAggregateHourly {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_cv1" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_cv1" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCustomVar1 extends UserActivityByCustomVar1 with IAggregateMinutely {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_cv1" -> toSomeColumns(columns),
    "minutely_ua_clst_cv1" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByCustomVar1 extends UserActivityByCustomVar1 with IAggregateTenSecs {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_cv1" -> toSomeColumns(columns),
    "tensecs_ua_clst_cv1" -> toSomeColumns(columns)
  )
}

case class PartnerCustomVar1Res(partnerId: Int, cv1: String, metric: Int, year: Int, time: DateTime, value: Long)
