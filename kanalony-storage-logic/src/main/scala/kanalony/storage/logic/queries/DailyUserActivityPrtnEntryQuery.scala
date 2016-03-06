package kanalony.storage.logic.queries

import kanalony.storage.generated._
import kanalony.storage.logic._
import org.joda.time.{DateTime, LocalDate}

class DailyUserActivityPrtnEntryQuery extends DailyQueryBase[hourly_ua_prtn_entryQuery, hourly_ua_prtn_entryQueryParams, hourly_ua_prtn_entryRow, dailyUserActivityPrtnEntryRow, dailyUserActivityPrtnEntryRowAggregationKey] with UserActivityQuery {

  override def internalQuery = Queries.hourly_ua_prtn_entryQuery

  override def countFieldExtractor(row : hourly_ua_prtn_entryRow): Long = { row.value }

  override protected def getResultRow(row: dailyUserActivityPrtnEntryRow): List[String] = {
    List(row.partner_id.toString, row.entry_id, row.event_type.toString, row.day.toString, row.count.toString)
  }

  override def getDailyGroupByKey(row: hourly_ua_prtn_entryRow): dailyUserActivityPrtnEntryRowAggregationKey = {
    dailyUserActivityPrtnEntryRowAggregationKey(row.partner_id, row.entry_id, row.metric, row.year, row.metric, row.hour.toLocalDate)
  }

  override def queryRowCreator(t : (dailyUserActivityPrtnEntryRowAggregationKey, Long)): dailyUserActivityPrtnEntryRow = {
    val key = t._1
    dailyUserActivityPrtnEntryRow(key.partner_id, key.entry_id, key.event_type, key.year, key.day, key.metric, t._2)
  }

  override private[logic] def extractMetric(row: dailyUserActivityPrtnEntryRow): Int = row.metric
}

case class dailyUserActivityPrtnEntryRowAggregationKey(partner_id:Int, entry_id:String, event_type:Int, year:Int, metric:Int, day:LocalDate)
case class dailyUserActivityPrtnEntryRow(partner_id:Int, entry_id:String, event_type:Int, year:Int, day:LocalDate, metric:Int, count:Long)