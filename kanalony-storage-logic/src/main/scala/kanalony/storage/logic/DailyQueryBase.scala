package kanalony.storage.logic

import kanalony.storage.logic.queries.model.DimensionDefinition
import org.joda.time.DateTime
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/18/2016.
 */

abstract class DailyQueryBase[Q <: QueryBase[TReq, TInternalQueryRow], TReq, TInternalQueryRow, TQueryRow, TDailyGroupByKey] extends QueryBase[TReq, TQueryRow] with UserActivityQuery {

  def internalQuery : Q
  def getDailyGroupByKey(row: TInternalQueryRow) : TDailyGroupByKey
  def countFieldExtractor(row: TInternalQueryRow) : Long
  def queryRowCreator(tuple: (TDailyGroupByKey, Long)): TQueryRow

  override private[logic] def extractParams(queryParams: QueryParams): TReq = internalQuery.extractParams(queryParams)

  override private[logic] def executeQuery(params: TReq): Future[List[TQueryRow]] = {
    val hourlyDataFuture = internalQuery.executeQuery(params)
    hourlyDataFuture map {
      hourlyRows => {
        val groups = hourlyRows groupBy(getDailyGroupByKey) // => Map[TDailyGroupByKey,List[TInternalQueryRow]]
        val aggregatedGroups = groups.mapValues(_.foldLeft(0L)(_ + countFieldExtractor(_))) // => Map[TDailyGroupByKey , Long]
        aggregatedGroups.map(queryRowCreator(_)).toList // => List[TQueryRow]
      }
    }
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
