package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationCustomVar1CustomVar2CustomVar3Key
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits



abstract class AggregationByCustomVar1CustomVar2CustomVar3 extends BaseAggregation[AggregationCustomVar1CustomVar2CustomVar3Key, PartnerCustomVar1CustomVar2CustomVar3Res] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("custom_var1","cv1"),
    ("custom_var2","cv2"),
    ("custom_var3","cv3"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


   override def aggKey(e: EnrichedPlayerEvent): AggregationCustomVar1CustomVar2CustomVar3Key = AggregationCustomVar1CustomVar2CustomVar3Key(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.customVar1, e.customVar2, e.customVar3)
   override def toRow(pair: (AggregationCustomVar1CustomVar2CustomVar3Key, Long)): PartnerCustomVar1CustomVar2CustomVar3Res = PartnerCustomVar1CustomVar2CustomVar3Res(pair._1.partnerId, pair._1.cv1, pair._1.cv2, pair._1.cv3, pair._1.metric, pair._1.time.getYear, pair._1.time.getYearMonthDay, pair._1.time, pair._2)
}

object HourlyAggregationByCustomVar1CustomVar2CustomVar3 extends AggregationByCustomVar1CustomVar2CustomVar3 with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_cv1_cv2_cv3" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_cv1_cv2_clst_cv3" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByCustomVar1CustomVar2CustomVar3 extends AggregationByCustomVar1CustomVar2CustomVar3 with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_cv1_cv2_cv3" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_cv1_cv2_clst_cv3" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByCustomVar1CustomVar2CustomVar3 extends AggregationByCustomVar1CustomVar2CustomVar3 with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_cv1_cv2_cv3" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_cv1_cv2_clst_cv3" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerCustomVar1CustomVar2CustomVar3Res(partner_id:Int, cv1:String, cv2:String, cv3:String, metric:String, year:Int, day: Int, time:DateTime, value:Long)

