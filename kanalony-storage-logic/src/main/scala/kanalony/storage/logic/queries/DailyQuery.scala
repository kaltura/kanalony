package kanalony.storage.logic.queries

import com.kaltura.model.entities.InternalMetrics
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.DateTime
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
 * Created by elad.benedict on 3/7/2016.
 */

class DailyQuery(queryParams: QueryParams) extends IQuery {

  if (!queryParams.dimensionDefinitions.map(_.dimension).contains(Dimensions.day))
  {
    throw new IllegalArgumentException("DailyQuery expects query params with Dimensions.day")
  }

  val updatedQueryParams = convertQueryParams(queryParams)
  val queryLocationResult = QueryLocator.locate(updatedQueryParams)

  if (queryLocationResult.length > 1)
  {
    throw new IllegalArgumentException("A daily query should be mapped to exactly one hourly table")
  }

  val query = queryLocationResult.head._1

  val separator = "::"
  override val supportedMetrics: Set[InternalMetrics.Value] = query.supportedMetrics

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

  def aggregateByDay: (IQueryResult) => IQueryResult = {
    qr => {
      val headers = qr.headers.map {
        case h if h eq Dimensions.hour.toString => Dimensions.day.toString
        case header : String => header
      }

      val groupByKey = getDailyGroupByKey(qr.headers)
      val rows = qr.rows
        .groupBy(groupByKey)
        .mapValues(_.foldLeft(0.0)(_ + countFieldExtractor(_)))
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

    val internalQueryParams = QueryParams(internalQueryDimensionDefinitions, params.metrics, params.start, params.end)
    query.query(internalQueryParams)
         .map(qrList => qrList.map(aggregateByDay))

  }

  override val dimensionInformation: List[IDimensionDefinition] = queryParams.dimensionDefinitions

  private def convertQueryParams(queryParams : QueryParams): QueryParams = {
    val dimDefs = queryParams.dimensionDefinitions.map {
      case QueryDimensionDefinition(Dimensions.day, constraint, includeInResult) => QueryDimensionDefinition(Dimensions.hour, constraint, includeInResult)
      case dimensionDefinition: QueryDimensionDefinition => dimensionDefinition
    }
    QueryParams(dimDefs, queryParams.metrics, queryParams.start, queryParams.end)
  }
}
