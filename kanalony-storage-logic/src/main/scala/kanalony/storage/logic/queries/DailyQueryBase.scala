package kanalony.storage.logic.queries

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model.{IDimensionDefinition, QueryDimensionDefinition}
import org.joda.time.DateTime
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 3/7/2016.
 */

abstract class DailyQueryBase(queryParams: QueryParams, queryLocator: IQueryLocator) extends IQuery {

  if (!queryParams.dimensionDefinitions.map(_.dimension).contains(Dimensions.day))
  {
    throw new IllegalArgumentException("DailyQuery expects query params with Dimensions.day")
  }

  val updatedQueryParams = convertQueryParams(queryParams)
  val queryLocationResult = queryLocator.locate(updatedQueryParams, ComputedDimensions, ComputedMetrics)

  if (queryLocationResult.length < 1)
  {
    throw new IllegalArgumentException("No suitable hourly query found for this daily query")
  }

  val query = queryLocationResult.head._1

  val separator = "::"

  override def isMetricSupported(metric: Metric) = query.isMetricSupported(metric)

  def getDailyGroupByKey(headers : List[String]) : (List[String]) => String = {

    val headersWithIndex = headers.zipWithIndex
    val relevantIndexes = headersWithIndex.take(headersWithIndex.length-1).map(_._2)
    val hourIndex = headersWithIndex.find(_._1.equals(Dimensions.hour.toString)).get._2

    rowData => relevantIndexes.map({
      case h if h equals hourIndex => new DateTime(rowData(h)).toLocalDate.toString()
      case i => rowData(i)
    })
      .mkString(separator)
  }

  def countFieldExtractor(row : List[String]) : Double = {
    row(row.length-1).toDouble
  }

  def createRow: (String, Double) => List[String] = (grouping, aggregatedValue) => (grouping.split(separator) :+ aggregatedValue.toString).toList

  def computeGroupAggregatedValue: (List[List[String]]) => Double

  def aggregateByDay: (IQueryResult) => IQueryResult = {
    qr => {
      val headers = qr.headers.map {
        case h if h eq Dimensions.hour.toString => Dimensions.day.toString
        case header : String => header
      }

      val groupByKey = getDailyGroupByKey(qr.headers)
      val rows = qr.rows
        .groupBy(groupByKey)
        .mapValues(computeGroupAggregatedValue)
        .transform(createRow)
        .values
        .toList

      QueryResult(headers, rows)
    }
  }

  override def query(params: QueryParams): Future[List[IQueryResult]] = {

    // Remove the day dimension definition and replace with an hour dimension definition
    // (while keeping the same order of dimensions)
    val internalQueryDimensionDefinitions = params.dimensionDefinitions.map(dimDef => {
      if (dimDef.dimension.equals(Dimensions.day)) { new QueryDimensionDefinition(Dimensions.hour, dimDef.constraint, true) } // Hour must be included in result for aggregation
      else { dimDef }
    })

    val internalQueryParams = QueryParams(internalQueryDimensionDefinitions, params.metrics, params.start, params.end, params.timezoneOffset)
    query.query(internalQueryParams)
         .map(qrList => qrList.map(aggregateByDay))

  }

  override val dimensionInformation: List[IDimensionDefinition] = queryParams.dimensionDefinitions

  private def convertQueryParams(queryParams : QueryParams): QueryParams = {
    val dimDefs = queryParams.dimensionDefinitions.map {
      case QueryDimensionDefinition(Dimensions.day, constraint, includeInResult) => QueryDimensionDefinition(Dimensions.hour, constraint, includeInResult)
      case dimensionDefinition: QueryDimensionDefinition => dimensionDefinition
    }
    QueryParams(dimDefs, queryParams.metrics, queryParams.start, queryParams.end, queryParams.timezoneOffset)
  }

  override val supportedWellKnownMetrics: Set[Metric] = query.supportedWellKnownMetrics
}
