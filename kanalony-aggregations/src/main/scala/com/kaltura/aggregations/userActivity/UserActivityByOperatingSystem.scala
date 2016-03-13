package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityOperatingSystemKey, UserActivityDeviceKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime


abstract class UserActivityByOperatingSystem extends BaseUserActivityAggregation[UserActivityOperatingSystemKey, PartnerOperatingSystemRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("operating_system","operatingSystem"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): UserActivityOperatingSystemKey = UserActivityOperatingSystemKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.userAgent.device.id)
  override def toRow(pair: (UserActivityOperatingSystemKey, Long)): PartnerOperatingSystemRes = PartnerOperatingSystemRes(partnerId = pair._1.partnerId, operatingSystem = pair._1.operatingSystem, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)

}

object HourlyUserActivityByOperatingSystem extends UserActivityByOperatingSystem with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_os" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_os" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByOperatingSystem extends UserActivityByOperatingSystem with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_os" -> toSomeColumns(columns),
    "minutely_ua_clst_os" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByOperatingSystem extends UserActivityByOperatingSystem with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_os" -> toSomeColumns(columns),
    "tensecs_ua_clst_os" -> toSomeColumns(columns)
  )
}
case class PartnerOperatingSystemRes(partnerId: Int, metric: Int, year: Int, time: DateTime, operatingSystem: Int, value: Long)

