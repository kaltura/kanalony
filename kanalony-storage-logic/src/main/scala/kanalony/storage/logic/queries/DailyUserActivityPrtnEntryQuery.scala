package kanalony.storage.logic.queries

import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.LocalDate
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by elad.benedict on 2/18/2016.
 */

class DailyUserActivityPrtnEntryQuery extends QueryBase[HourlyUserActivityPrtnEntryParams, dailyUserActivityPrtnEntryRow] with UserActivityQuery {

  val hourlyUserActivityPrtnEntryQuery = Queries.HourlyUserActivityPrtnEntryQuery

  override private[logic] def extractParams(queryParams: QueryParams): HourlyUserActivityPrtnEntryParams = hourlyUserActivityPrtnEntryQuery.extractParams(queryParams)

  override protected def getResultRow(row: dailyUserActivityPrtnEntryRow): List[String] = {
    List(row.partner_id.toString, row.entry_id, row.event_type.toString, row.day.toString, row.count.toString)
  }

  override private[logic] def getResultHeaders(): List[String] = {
    List(Dimensions.partner.toString, Dimensions.entry.toString, Dimensions.metric.toString, Dimensions.day.toString, metricValueHeaderName)
  }

  def createDailyGroups(group: (String, List[hourly_user_activity_prtn_entryRow])): (String, Map[String, dailyUserActivityPrtnEntryRow]) = {
    val dailyGroups = group._2.groupBy(row => group._1 + ":" + row.hour.toLocalDate.toString) // Map[day, list of hourly rows]
    val aggregatedDailyGroups = dailyGroups.mapValues(g => {
        val element = g.head
        val aggregatedValue =  g.foldLeft(0L)(_ + _.count)
        dailyUserActivityPrtnEntryRow(element.partner_id, element.entry_id, element.event_type, element.year, element.hour.toLocalDate, aggregatedValue)
      })
    (group._1, aggregatedDailyGroups)
  }

  override private[logic] def executeQuery(params: HourlyUserActivityPrtnEntryParams): Future[List[dailyUserActivityPrtnEntryRow]] = {
    val hourlyDataFuture = hourlyUserActivityPrtnEntryQuery.executeQuery(params)
    hourlyDataFuture map {
      hourlyRows => {
        val groups = hourlyRows groupBy(row => List(row.partner_id, row.entry_id, row.event_type).mkString(":"))
        val dailyGroups = groups.map({group : (String, List[hourly_user_activity_prtn_entryRow]) => createDailyGroups(group)})
        dailyGroups.flatMap(_._2).values.toList
      }
    }
  }

  override val metricValueLocationIndex: Int = hourlyUserActivityPrtnEntryQuery.metricValueLocationIndex()

  override val dimensionInformation: List[DimensionDefinition] = {
    lazy val result = hourlyUserActivityPrtnEntryQuery.dimensionInformation
      .filterNot(_.dimension eq Dimensions.hour) :+ new DimensionDefinition(Dimensions.day, new DimensionConstraintDeclaration(QueryConstraint.Range))
    result
  }
  override val tableName: String = hourlyUserActivityPrtnEntryQuery.tableName
}

case class dailyUserActivityPrtnEntryRow(partner_id:Int,
                                         entry_id:String,
                                         event_type:Int,
                                         year:Int,
                                         day:LocalDate,
                                         count:Long)

