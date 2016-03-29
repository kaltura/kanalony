package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationCustomVar1CustomVar2Key
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits



abstract class AggregationByCustomVar1CustomVar2 extends BaseAggregation[AggregationCustomVar1CustomVar2Key, PartnerCustomVar1CustomVar2Res] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("custom_var1","cv1"),
    ("custom_var2","cv2"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): AggregationCustomVar1CustomVar2Key = AggregationCustomVar1CustomVar2Key(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.customVar1, e.customVar2)
   override def toRow(pair: (AggregationCustomVar1CustomVar2Key, Long)): PartnerCustomVar1CustomVar2Res = PartnerCustomVar1CustomVar2Res(pair._1.partnerId, pair._1.cv1, pair._1.cv2, pair._1.metric, pair._1.time.getYear, pair._1.time.getYearMonthDay, pair._1.time, pair._2)
}

object HourlyAggregationByCustomVar1CustomVar2 extends AggregationByCustomVar1CustomVar2 with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_cv1_cv2" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_cv1_clst_cv2" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByCustomVar1CustomVar2 extends AggregationByCustomVar1CustomVar2 with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_cv1_cv2" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_cv1_clst_cv2" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByCustomVar1CustomVar2 extends AggregationByCustomVar1CustomVar2 with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_cv1_cv2" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_cv1_clst_cv2" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class PartnerCustomVar1CustomVar2Res(partner_id:Int, cv1:String, cv2:String, metric:String, year:Int, day: Int, time:DateTime, value:Long)
