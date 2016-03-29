package com.kaltura.aggregations

import com.datastax.spark.connector.{SomeColumns, _}
import com.kaltura.aggregations.keys.AggregationCountryBrowserKey
import com.kaltura.model.events.EnrichedPlayerEvent
import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits


abstract class AggregationByCountryBrowser extends BaseAggregation[AggregationCountryBrowserKey, PartnerCountryBrowserRes] with IAggregate with Serializable{

  val columns: List[(String, String)] = List[(String, String)](
    ("partner_id","partner_id"),
    ("country","country"),
    ("browser","browser"),
    ("metric","metric"),
    (getAggrTimeUnit,"time"),
    ("value","value"))

   override def aggKey(e: EnrichedPlayerEvent): AggregationCountryBrowserKey = AggregationCountryBrowserKey(e.partnerId, e.eventType, getAggrTime(e.eventTime), e.location.country, e.userAgent.browser.id)
   override def toRow(pair: (AggregationCountryBrowserKey, Long)): PartnerCountryBrowserRes = PartnerCountryBrowserRes(partnerId= pair._1.partnerId, country = pair._1.country, metric = pair._1.metric, year = pair._1.time.getYear, day = pair._1.time.getYearMonthDay, time = pair._1.time, browser = pair._1.browser, value = pair._2)
}

object HourlyAggregationByCountryBrowser extends AggregationByCountryBrowser with IAggregateHourly {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "hourly_agg_prtn_country_clst_browser" -> toSomeColumns(columns :+ ("year", "year"))
  )
}

object MinutelyAggregationByCountryBrowser extends AggregationByCountryBrowser with IAggregateMinutely {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "minutely_agg_prtn_country_clst_browser" -> toSomeColumns(columns :+ ("day", "day"))
  )
}

object TenSecsAggregationByCountryBrowser extends AggregationByCountryBrowser with IAggregateTenSecs {
  override lazy val tableMetadata: Map[String, SomeColumns] = Map(
    "tensecs_agg_prtn_country_clst_browser" -> toSomeColumns(columns :+ ("day", "day"))
  )
}



case class PartnerCountryBrowserRes(partnerId: Int, metric: String, year: Int, day: Int, time: DateTime, country: String, browser: Int, value: Long)

