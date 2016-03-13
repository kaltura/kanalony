package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityEntryKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime

abstract class UserActivityByEntry extends BaseUserActivityAggregation[UserActivityEntryKey, PartnerEntryRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  // TODO - dropped month column temporarily
  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryKey = UserActivityEntryKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime))
  override def toRow(pair: (UserActivityEntryKey, Long)): PartnerEntryRes = PartnerEntryRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, metric = pair._1.metric, month = (pair._1.time.getYear*100 + pair._1.time.getMonthOfYear),day = (pair._1.time.getYear*100 + pair._1.time.getMonthOfYear)*100 + pair._1.time.getDayOfWeek, time = pair._1.time, value = pair._2)
}

object HourlyUserActivityByEntry extends UserActivityByEntry with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry" -> toSomeColumns(columns :+ ("month", "month")),
    "hourly_ua_clst_entry" -> toSomeColumns(columns :+ ("month", "month"))
  )

}

object MinutelyUserActivityByEntry extends UserActivityByEntry with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_ua_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsUserActivityByEntry extends UserActivityByEntry with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_ua_clst_entry" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

case class PartnerEntryRes(partnerId: Int, entryId: String, metric: Int, month: Int, day: Int, time: DateTime, value: Long)

