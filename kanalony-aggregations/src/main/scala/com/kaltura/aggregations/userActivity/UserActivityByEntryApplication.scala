package com.kaltura.aggregations.userActivity

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.{IAggregateTenSecs, IAggregateMinutely, IAggregate, IAggregateHourly}
import com.kaltura.aggregations.keys.{UserActivityEntryApplicationKey, UserActivityApplicationKey}
import com.kaltura.model.events.EnrichedPlayerEvent
import kanalony.storage.generated.hourly_ua_prtn_applicationRow
import org.joda.time.DateTime

/**
 * Created by orlylampert on 3/2/16.
 */
abstract class UserActivityByEntryApplication extends BaseUserActivityAggregation[UserActivityEntryApplicationKey, EntryApplicationRes] with IAggregate with Serializable{
  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partnerId"),
    ("entry_id","entryId"),
    ("application","application"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))


  override def aggKey(e: EnrichedPlayerEvent): UserActivityEntryApplicationKey = UserActivityEntryApplicationKey(e.partnerId, e.entryId, e.eventType, getAggrTime(e.eventTime), e.application)
  override def toRow(pair: (UserActivityEntryApplicationKey, Long)): EntryApplicationRes = EntryApplicationRes(partnerId = pair._1.partnerId, entryId = pair._1.entryId, application = pair._1.application, metric = pair._1.metric, year = pair._1.time.getYear, time = pair._1.time, value = pair._2)

}


object HourlyUserActivityByEntryApplication extends UserActivityByEntryApplication with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_ua_prtn_entry_app" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_ua_prtn_entry_clst_app" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyUserActivityByEntryApplication extends UserActivityByEntryApplication with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_ua_prtn_entry_app" -> toSomeColumns(columns),
    "minutely_ua_prtn_entry_clst_app" -> toSomeColumns(columns)
  )
}

object TenSecsUserActivityByEntryApplication extends UserActivityByEntryApplication with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_ua_prtn_entry_app" -> toSomeColumns(columns),
    "tensecs_ua_prtn_entry_clst_app" -> toSomeColumns(columns)
  )
}

case class EntryApplicationRes(partnerId: Int, entryId: String, metric: Int, year: Int, time: DateTime, application: String, value: Long)
