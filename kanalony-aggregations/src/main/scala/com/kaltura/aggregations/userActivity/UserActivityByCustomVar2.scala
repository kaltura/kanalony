package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityCustomVarKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByCustomVar2 extends BaseUserActivityAggregation[UserActivityCustomVarKey, PartnerCustomVar2] with IAggregate with Serializable{


  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("custom_var2","cv2"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityCustomVarKey = UserActivityCustomVarKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.customVar2)
  override def toRow(pair: (UserActivityCustomVarKey, Long)): PartnerCustomVar2 = PartnerCustomVar2(pair._1.partnerId, pair._1.cv, pair._1.metric, pair._1.time.getYear, pair._1.time, pair._2)


}

object HourlyUserActivityByCustomVar2 extends UserActivityByCustomVar2 with IAggregateHourly {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_cv2" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_cv2" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByCustomVar2 extends UserActivityByCustomVar2 with IAggregateMinutely {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_cv2" -> toSomeColumns(columns),
    "minutely_ua_clst_cv2" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByCustomVar2 extends UserActivityByCustomVar2 with IAggregateTenSecs {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_cv2" -> toSomeColumns(columns),
    "tensecs_ua_clst_cv2" -> toSomeColumns(columns)
  )
}

case class PartnerCustomVar2(partnerId: Int, cv2: String, metric: Int, year: Int, time: DateTime, value: Long)
