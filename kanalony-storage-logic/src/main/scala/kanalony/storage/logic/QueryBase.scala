package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.api.DbClientFactory
import scala.collection.GenTraversableOnce
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/10/2016.
 */

abstract class QueryBase[TReq, TQueryRow] extends IQuery {

  val dbApi = DbClientFactory

  val metricValueHeaderName = "value"

  private[logic] def extractParams(queryParams: QueryParams): TReq

  private[logic] def executeQuery(params: TReq): Future[List[TQueryRow]]

  private[logic] def getResultHeaders(): List[String]

  private[logic] def extractMetric(row : TQueryRow): Int

  protected def getResultRow(row: TQueryRow): List[String]

  def metricValueLocationIndex: Int

  def getGroupByKey(headerDimensionIndexes: List[Int]): (List[String]) => String = {
    row => {
      var groupValues: List[String] = List()
      headerDimensionIndexes.foreach(i => groupValues = groupValues :+ row(i))
      groupValues.mkString(":")
    }
  }

  def getGroupAggregatedValue(v: List[List[String]]): Double = {
    v.map(row => row(metricValueLocationIndex).toDouble).sum
  }

  def groupAndAggregate(params: QueryParams, metric: Int): QueryResult => QueryResult = {

    val resultDimensions = params.dimensionDefinitions.filter(_.includeInResult).map(_.dimension)

    def getGroupDimensionIndexes(queryResult: QueryResult) = {
      val indexedResultDimensions = queryResult.headers.zipWithIndex
      resultDimensions.map(value => {
        indexedResultDimensions.find(_._1 eq value.toString).map(_._2).get
      })
    }

    queryResult => {
      val headerDimensionIndexes = getGroupDimensionIndexes(queryResult)
      val resultHeaders = resultDimensions.map(_.toString) :+ Metrics(metric).toString
      val groupedData = queryResult.rows.groupBy(getGroupByKey(headerDimensionIndexes))

      val resultRows = groupedData.toList.map((group: (String, List[List[String]])) => {
        var rowData = if (group._1.isEmpty) {
          List()
        } else {
          group._1.split(':').toList
        }
        val groupAggregatedValue = getGroupAggregatedValue(group._2).toString
        rowData = rowData :+ groupAggregatedValue
        rowData
      })

      QueryResult(resultHeaders, resultRows)
    }
  }

  def groupByMetric: (List[TQueryRow]) => Map[Int, List[TQueryRow]] = {
    rows => rows.groupBy(extractMetric)
  }

  def combineMetrics(resultMetrics: List[Metrics.Value]): (String, Iterable[(List[String], String)]) => List[String] = {
    (groping, rowsWithSameMetric) => {
      var resultantRow = groping.split(":").toList
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
  def combineResults(queryParams: QueryParams): (Iterable[QueryResult]) => QueryResult = {
    queryResults => {
      val resultantRows = queryResults
        .flatMap(qr => qr.rows.map((_, qr.headers.last))) // Combine all rows to a single list, keeping the metric for each one
        // Group by all fields except the value field
        .groupBy((row: (List[String], String)) => row._1.take(row._1.length - 1).mkString(":")) // Map[grouping,Iterable[(row,metricHeader)]]
        .transform(combineMetrics(queryParams.metrics))
        .values
        .toList
      val headers = queryParams.dimensionDefinitions.filter(_.includeInResult).map(_.dimension.toString) ::: queryParams.metrics.map(_.toString)
      QueryResult(headers, resultantRows)
    }
  }

  def processMetric(params: QueryParams): (Map[Int, List[TQueryRow]]) => Iterable[QueryResult] = {
    // For each (group of rows with the same) metric
    x => x.map((kvp: (Int, List[TQueryRow])) => {
      // Turn to QueryResult
      val processedRows = kvp._2.map(row => getResultRow(row))
      val queryResult = QueryResult(getResultHeaders(), processedRows)
      // Group by requested dimensions and aggregate
      groupAndAggregate(params, kvp._1)(queryResult) // => Iterable[QueryResult]
    })
  }

  def query(params: QueryParams): Future[IQueryResult] = {
    val inputParams = extractParams(params)
    val retrievedRowsFuture = executeQuery(inputParams)

    retrievedRowsFuture
      // Group retrieved data by metric
      .map(groupByMetric)
      // Calculate aggregation per metric
      .map(processMetric(params))
      // Combine all metrics (for the same grouping key) to a single row
      .map(combineResults(params))
  }
}