package kanalony.storage.logic.queries

import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, LocalDate}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class DailyUserActivityPrtnEntryQuery extends DailyQueryBase[HourlyUserActivityPrtnEntryQuery, HourlyUserActivityPrtnEntryParams, hourly_user_activity_prtn_entryRow, dailyUserActivityPrtnEntryRow] with UserActivityQuery {

  override def internalQuery: HourlyUserActivityPrtnEntryQuery = Queries.HourlyUserActivityPrtnEntryQuery

  override def getDailyGroupByKey(row: hourly_user_activity_prtn_entryRow): String = {
    List(row.partner_id, row.entry_id, row.event_type).mkString(":")
  }

  override def hourFieldExtractor(row : hourly_user_activity_prtn_entryRow): DateTime = { row.hour }

  override def countFieldExtractor(row : hourly_user_activity_prtn_entryRow): Long = { row.count }

  override def createQueryRow(row : hourly_user_activity_prtn_entryRow, aggregatedValue: Long): dailyUserActivityPrtnEntryRow = {
    dailyUserActivityPrtnEntryRow(row.partner_id, row.entry_id, row.event_type, row.year, row.hour.toLocalDate, aggregatedValue)
  }

  override protected def getResultRow(row: dailyUserActivityPrtnEntryRow): List[String] = {
    List(row.partner_id.toString, row.entry_id, row.event_type.toString, row.day.toString, row.count.toString)
  }
}

case class dailyUserActivityPrtnEntryRow(partner_id:Int,
                                         entry_id:String,
                                         event_type:Int,
                                         year:Int,
                                         day:LocalDate,
                                         count:Long)

