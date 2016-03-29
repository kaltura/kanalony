package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationBrowserKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits



abstract class AggregationByBrowser extends BaseAggregation[AggregationBrowserKey, PartnerBrowserRes] with IAggregate with Serializable {

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

  override def aggKey(e: EnrichedPlayerEvent): AggregationBrowserKey = AggregationBrowserKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.userAgent.browser.id)
  override def toRow(pair: (AggregationBrowserKey, Long)): PartnerBrowserRes = PartnerBrowserRes(partnerId = pair._1.partnerId, browser = pair._1.browser, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, value = pair._2)


}

object HourlyAggregationByBrowser extends AggregationByBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_browser" -> toSomeColumns(columns :+ ("year", "year")),
    "hourly_agg_clst_browser" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByBrowser extends AggregationByBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "minutely_agg_clst_browser" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByBrowser extends AggregationByBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_browser" -> toSomeColumns(columns :+ ("day", "day")),
    "tensecs_agg_clst_browser" -> toSomeColumns(columns :+ ("day", "day"))
  )
}
case class PartnerBrowserRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, browser: Int, value: Long)

