package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.UserActivityApplicationKey
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_applicationRow
import org.joda.time.DateTime

/**
 * Created by orlylampert on 3/2/16.
 */
abstract class UserActivityByApplication extends BaseUserActivityAggregation[UserActivityApplicationKey, EntryApplicationRes] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("application","application"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): UserActivityApplicationKey = UserActivityApplicationKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.application)
  override def toRow(pair: (UserActivityApplicationKey, Long)): EntryApplicationRes = EntryApplicationRes(partnerId = pair._1.partnerId, application = pair._1.application, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)

}


object HourlyUserActivityByApplication extends UserActivityByApplication with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_app" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_clst_app" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByApplication extends UserActivityByApplication with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_app" -> toSomeColumns(columns),
    "minutely_ua_clst_app" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByApplication extends UserActivityByApplication with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_app" -> toSomeColumns(columns),
    "tensecs_ua_clst_app" -> toSomeColumns(columns)
  )
}

case class EntryApplicationRes(partnerId: Int, metric: Int, year: Int, time: DateTime, application: String, value: Long)
