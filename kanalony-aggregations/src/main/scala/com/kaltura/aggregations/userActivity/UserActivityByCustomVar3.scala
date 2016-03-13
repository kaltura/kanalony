package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityCustomVarKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByCustomVar3 extends BaseUserActivityAggregation[UserActivityCustomVarKey, PartnerCustomVar3] with IAggregate with Serializable{


  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("custom_var3","cv3"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityCustomVarKey = UserActivityCustomVarKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.customVar2)
  override def toRow(pair: (UserActivityCustomVarKey, Long)): PartnerCustomVar3 = PartnerCustomVar3(pair._1.partnerId, pair._1.cv, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)


}

object HourlyUserActivityByCustomVar3 extends UserActivityByCustomVar3 with IAggregateHourly {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_cv3" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_cv3" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCustomVar3 extends UserActivityByCustomVar3 with IAggregateMinutely {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_cv3" -> toSomeColumns(columns),
    "minutely_ua_clst_cv3" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByCustomVar3 extends UserActivityByCustomVar3 with IAggregateTenSecs {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_cv3" -> toSomeColumns(columns),
    "tensecs_ua_clst_cv3" -> toSomeColumns(columns)
  )
}

case class PartnerCustomVar3(partnerId: Int, cv3: String, metric: Int, year: Int, time: DateTime, value: Long)
