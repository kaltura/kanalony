package kanalony.storage.logic.queries

import com.kaltura.model.entities.InternalMetrics
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model.IDimensionDefinition
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 3/14/2016.
 */
class EstimatedMinutesWatchedQuery(queryParams: QueryParams) extends IQuery {

  if (!queryParams.metrics.contains(InternalMetrics.estimatedMinutesWatched))
  {
    throw new IllegalArgumentException("EstimatedMinutesWatchedQuery expects query params with Metrics.estimatedMinutesWatched")
  }

  val updatedQueryParams = convertQueryParams(queryParams)
  val query = QueryLocator.locate(updatedQueryParams).head._1

  override val supportedMetrics: Set[InternalMetrics.Value] = Set(InternalMetrics.estimatedMinutesWatched)

  override def query(params: QueryParams): Future[List[IQueryResult]] = {
    query.query(QueryParams(params.dimensionDefinitions, List(InternalMetrics.tenSecsViewed), params.start, params.end))
         .map(_.map(convertToMinutes))
  }

  def convertToMinutes: (IQueryResult) => IQueryResult = {
    qr => {
      val resultHeaders = qr.headers.take(qr.headers.length - 1) :+ InternalMetrics.estimatedMinutesWatched.toString
      val rows = qr.rows.map(convertRowToMinutes)
      QueryResult(resultHeaders, rows)
    }
  }

  def convertRowToMinutes: (List[String]) => List[String] = {
    row => {
      val updatedValue = (row.last.toDouble/6).toString
      row.take(row.length-1) :+ updatedValue
    }
  }

  override val dimensionInformation: List[IDimensionDefinition] = queryParams.dimensionDefinitions

  private def convertQueryParams(queryParams : QueryParams): QueryParams = {
    QueryParams(queryParams.dimensionDefinitions, List(InternalMetrics.tenSecsViewed), queryParams.start, queryParams.end)
  }
}
