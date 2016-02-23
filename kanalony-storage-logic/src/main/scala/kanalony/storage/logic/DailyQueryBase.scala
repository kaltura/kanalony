package kanalony.storage.logic

import kanalony.storage.logic.queries.model.DimensionDefinition
import org.joda.time.DateTime
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/18/2016.
 */

abstract class DailyQueryBase[Q <: QueryBase[TReq, TInternalQueryRow], TReq, TInternalQueryRow, TQueryRow] extends QueryBase[TReq, TQueryRow] with UserActivityQuery {

  def internalQuery : Q
  def getDailyGroupByKey(row: TInternalQueryRow) : String
  def hourFieldExtractor(row: TInternalQueryRow) : DateTime
  def countFieldExtractor(row: TInternalQueryRow) : Long
  def createQueryRow(row: TInternalQueryRow, aggregatedValue : Long) : TQueryRow

  override private[logic] def extractParams(queryParams: QueryParams): TReq = internalQuery.extractParams(queryParams)

  override private[logic] def executeQuery(params: TReq): Future[List[TQueryRow]] = {
    val hourlyDataFuture = internalQuery.executeQuery(params)
    hourlyDataFuture map {
      hourlyRows => {
        val groups = hourlyRows groupBy(getDailyGroupByKey)
        val dailyGroups = groups.map({group : (String, List[TInternalQueryRow]) => createDailyGroups(group)})
        dailyGroups.flatMap(_._2).values.toList
      }
    }
  }

  def createDailyGroups(group: (String, List[TInternalQueryRow])): (String, Map[String, TQueryRow]) = {
    val dailyGroups = group._2.groupBy(row => group._1 + ":" + hourFieldExtractor(row).toLocalDate.toString) // Map[day, list of hourly rows]
    val aggregatedDailyGroups = dailyGroups.mapValues(g => {
        val element = g.head
        val aggregatedValue =  g.foldLeft(0L)(_ + countFieldExtractor(_))
        createQueryRow(element, aggregatedValue)
      })
    (group._1, aggregatedDailyGroups)
  }

  override private[logic] def getResultHeaders(): List[String] = {
    val hourHeader = Dimensions.hour.toString
    internalQuery.getResultHeaders().map {
      case h if h eq hourHeader => Dimensions.day.toString
      case header : String => header
    }
  }

  override def metricValueLocationIndex: Int = internalQuery.metricValueLocationIndex

  override def tableName: String = { internalQuery.tableName }
  override val dimensionInformation: List[DimensionDefinition] = {
    internalQuery.dimensionInformation.map {
      case DimensionDefinition(Dimensions.hour, constraint) => DimensionDefinition(Dimensions.day, constraint)
      case dimensionDefinition: DimensionDefinition => dimensionDefinition
    }
  }
}
