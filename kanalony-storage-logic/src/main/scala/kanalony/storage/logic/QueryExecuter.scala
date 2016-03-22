package kanalony.storage.logic

import com.kaltura.model.entities.InternalMetrics
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 3/2/2016.
 */
object QueryExecutor {

  val groupingSeparator = "::"

  def query(qp : QueryParams) : Future[IQueryResult] = {
    val queries = QueryLocator.locate(qp)
    val queryResults = queries.map(q => {
      val queryParamsPerQuery = QueryParams(qp.dimensionDefinitions, q._2, qp.start, qp.end)
      q._1.query(queryParamsPerQuery)
    })
    Future.sequence(queryResults)
          .map(_.flatten)
          .map(x => combineResults(qp)(x))
  }

  def combineMetrics(resultMetrics: List[InternalMetrics.Value]): (String, Iterable[(List[String], String)]) => List[String] = {
    (groping, rowsWithSameMetric) => {
      var resultantRow = if (groping equals "") { List() } else { groping.split(groupingSeparator).toList }
      resultMetrics.foreach(metric => {
        rowsWithSameMetric
          .find(_._2 eq metric.toString)
          .orElse(Some((List("0"), metric)))
          .foreach(r => { resultantRow = resultantRow :+ r._1.last })
      })
      resultantRow
    }
  }

  // Combine the sequence of QueryResult per metric to a single QueryResult
  def combineResults(queryParams: QueryParams): (List[IQueryResult]) => QueryResult = {
    queryResults => {
      val resultantRows = queryResults
        .flatMap(qr => qr.rows.map((_, qr.headers.last))) // Combine all rows to a single list, keeping the metric for each one
        // Group by all fields except the value field
        .groupBy((row: (List[String], String)) => row._1.take(row._1.length - 1).mkString(groupingSeparator)) // Map[grouping,Iterable[(row,metricHeader)]]
        .transform(combineMetrics(queryParams.metrics))
        .values
        .toList
      val headers = queryParams.dimensionDefinitions.filter(_.includeInResult).map(_.dimension.toString) ::: queryParams.metrics.map(_.toString)
      QueryResult(headers, resultantRows)
    }
  }
}
