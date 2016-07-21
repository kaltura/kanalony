package com.kaltura.aggregations

import com.datastax.spark.connector.SomeColumns
import com.kaltura.aggregations.keys.AggregationOperatingSystemKey
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class AggregationByOperatingSystem extends BaseAggregation[AggregationOperatingSystemKey, PartnerOperatingSystemRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("operating_system","operatingSystem"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationOperatingSystemKey = AggregationOperatingSystemKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.userAgent.operatingSystem.id)
  override def toRow(pair: (AggregationOperatingSystemKey, Long)): PartnerOperatingSystemRes = PartnerOperatingSystemRes(partnerId = pair._1.partnerId, operatingSystem = pair._1.operatingSystem, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)

}

object HourlyAggregationByOperatingSystem extends AggregationByOperatingSystem with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_os" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_os" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByOperatingSystem extends AggregationByOperatingSystem with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_os" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_os" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByOperatingSystem extends AggregationByOperatingSystem with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_os" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_os" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class PartnerOperatingSystemRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, operatingSystem: Int, value: Long)

