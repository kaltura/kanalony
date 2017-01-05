package kanalony.storage.logic

import com.kaltura.model.entities.{Metrics, Metric}
import kanalony.storage.DbClientFactory
import kanalony.storage.generated.EntryRow
import kanalony.storage.logic.queries.model.OrderDirection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 3/2/2016.
 */
object QueryExecutor {

  val groupingSeparator = "::"

  def query(qp : QueryParams) : Future[IQueryResult] = {
    val queries = QueryLocator.locate(qp, ComputedDimensions, ComputedMetrics)
    val queryResults = queries.map(q => {
      val queryParamsPerQuery = QueryParams(qp.dimensionDefinitions, q._2, qp.start, qp.end, qp.timezoneOffset, qp.orderBy, qp.pager)
      q._1.query(queryParamsPerQuery)
    })
    val result = Future.sequence(queryResults)
                        .map(_.flatten)
                        .map(x => combineResults(qp)(x))

    if (qp.dimensionDefinitions.map(_.dimension).toSet.contains(Dimensions.entry)) {
      val resultWithEntries = result.flatMap(getEntries()).zip(result)
      resultWithEntries.map(x => {
        val queryResult = x._2
        val entries = x._1
        val names = entries.map(entry => (entry.entryId, entry.entryName)).toMap
        val index = queryResult.headers.indexOf(Dimensions.entry.toString)
        val enrichedRows = queryResult.rows.map(row => names(row(index)) :: row)

        QueryResult("entryName" :: queryResult.headers, enrichedRows)
      })
    } else {
      result
    }

  }

  def getEntries() : (QueryResult => Future[List[EntryRow]]) = {
    queryResult => {
      val index = queryResult.headers.indexOf(Dimensions.entry.toString)
      val entryIds = queryResult.rows.map(row => row(index)).toSet
      DbClientFactory.EntryTableAccessor.query(entryIds.toList)

    }
  }

  def combineMetrics(resultMetrics: List[Metric]): (String, Iterable[(List[String], String)]) => List[String] = {
    (groping, rowsWithSameMetric) => {
      var resultantRow = if (groping equals "") { List() } else { groping.split(groupingSeparator).toList }
      resultMetrics.foreach(metric => {
        rowsWithSameMetric
          .find(_._2 == metric.name)
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
      val headers = queryParams.dimensionDefinitions.filter(_.includeInResult).map(_.dimension.toString) ::: queryParams.metrics.map(_.name)
      sortQueryResult(queryParams, QueryResult(headers, resultantRows))
    }
  }

  def sortResults(params: QueryParams): (List[QueryResult]) => List[QueryResult] =  {
    QueryResults => {
      QueryResults.map(queryResult => sortQueryResult(params, queryResult) )
    }
  }

  def sortQueryResult(params: QueryParams, queryResult: QueryResult): QueryResult = {
    val index = queryResult.headers.indexOf(params.orderBy.header)

    val sorted = {
      if (index >= 0) {
        queryResult.rows.sortWith { (row1, row2) =>
          if (params.metrics.contains(Metrics.get(params.orderBy.header)))
            (row1(index).toDouble < row2(index).toDouble)
          else
            (row1(index).compareTo(row2(index)) < 0)
        }
      }
      else
        queryResult.rows
    }

    val (start, end) =
      if (params.pager.index > 0) {
        ((params.pager.index - 1) * params.pager.size, (params.pager.size * params.pager.index))
      } else {
        (0,sorted.size)
      }

    params.orderBy.order match {
      case OrderDirection.DESC => new QueryResult(queryResult.headers, sorted.reverse.slice(start, end))
      case _ => new QueryResult(queryResult.headers, sorted.slice(start, end))
    }

  }

}
