package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityKey, UserActivityApplicationKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime

/**
 * Created by orlylampert on 3/2/16.
 */
abstract class UserActivityByPartner extends BaseUserActivityAggregation[UserActivityKey, PartnerRes] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): UserActivityKey = UserActivityKey(e.partnerId, e.eventType, getAggrTime(e.eventTime))
  override def toRow(pair: (UserActivityKey, Long)): PartnerRes = PartnerRes(partnerId = pair._1.partnerId, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)

}


object HourlyUserActivityByPartner extends UserActivityByPartner with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByPartner extends UserActivityByPartner with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByPartner extends UserActivityByPartner with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua" -> toSomeColumns(columns)
  )
}

case class PartnerRes(partnerId: Int, metric: Int, year: Int, time: DateTime, value: Long)
