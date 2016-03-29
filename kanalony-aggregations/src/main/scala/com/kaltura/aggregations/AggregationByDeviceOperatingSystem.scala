package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationDeviceOperatingSystemKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByDeviceOperatingSystem extends BaseAggregation[AggregationDeviceOperatingSystemKey, PartnerDeviceOperatingSystemRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("device","device"),
    ("operating_system", "operatingSystem"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationDeviceOperatingSystemKey = AggregationDeviceOperatingSystemKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.userAgent.device.id, e.userAgent.operatingSystem.id)
  override def toRow(pair: (AggregationDeviceOperatingSystemKey, Long)): PartnerDeviceOperatingSystemRes = PartnerDeviceOperatingSystemRes(partnerId = pair._1.partnerId, device = pair._1.device, operatingSystem = pair._1.operatingSystem, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)


}

object HourlyAggregationByDeviceOperatingSystem extends AggregationByDeviceOperatingSystem with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_device_os" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_prtn_device_clst_os" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByDeviceOperatingSystem extends AggregationByDeviceOperatingSystem with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_device_os" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_prtn_device_clst_os" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByDeviceOperatingSystem extends AggregationByDeviceOperatingSystem with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_device_os" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_prtn_device_clst_os" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class PartnerDeviceOperatingSystemRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, device: Int, operatingSystem: Int, value: Long)

