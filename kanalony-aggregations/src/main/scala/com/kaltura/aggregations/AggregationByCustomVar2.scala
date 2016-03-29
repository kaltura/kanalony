package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationCustomVarKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByCustomVar2 extends BaseAggregation[AggregationCustomVarKey, PartnerCustomVar2Res] with IAggregate with Serializable{


  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("custom_var2","cv2"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationCustomVarKey = AggregationCustomVarKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.customVar2)
  override def toRow(pair: (AggregationCustomVarKey, Long)): PartnerCustomVar2Res = PartnerCustomVar2Res(pair._1.partnerId, pair._1.cv, pair._1.metric, pair._1.time.getYear, day = pair._1.time.getYearMonthDay, pair._1.time, pair._2)


}

object HourlyAggregationByCustomVar2 extends AggregationByCustomVar2 with IAggregateHourly {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_cv2" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_cv2" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByCustomVar2 extends AggregationByCustomVar2 with IAggregateMinutely {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_cv2" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_cv2" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByCustomVar2 extends AggregationByCustomVar2 with IAggregateTenSecs {

  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_cv2" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_cv2" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerCustomVar2Res(partnerId: Int, cv2: String, metric: String, year: Int, day: Int, time: DateTime, value: Long)
