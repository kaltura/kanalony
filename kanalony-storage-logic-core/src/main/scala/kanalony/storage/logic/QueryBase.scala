package kanalony.storage.logic

import com.kaltura.model.entities.InternalMetrics
import kanalony.storage.DbClientFactory
import kanalony.storage.generated._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/10/2016.
 */

abstract class QueryBase[TReq, TQueryRow] extends IQuery {

  val groupingSeparator = "::"

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
      groupValues.mkString(groupingSeparator)
    }
  }

  def getGroupAggregatedValue(v: List[List[String]], metric : Int): Double = {
    val x = metric
    val metricKind = InternalMetrics.values.find(_.id == metric)
    val values = v.map(row => row(metricValueLocationIndex).toDouble)
    if (metricKind.isDefined && metricKind.get == InternalMetrics.peakView)
    {
      values.max
    }
    else
    {
      values.sum
    }
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
      val resultHeaders = resultDimensions.map(_.toString) :+ InternalMetrics(metric).toString
      val groupedData = queryResult.rows.groupBy(getGroupByKey(headerDimensionIndexes))

      val resultRows = groupedData.toList.map((group: (String, List[List[String]])) => {
        var rowData = if (group._1.isEmpty) {
          List()
        } else {
          group._1.split(groupingSeparator).toList
        }
        val groupAggregatedValue = getGroupAggregatedValue(group._2, metric).toString
        rowData = rowData :+ groupAggregatedValue
        rowData
      })

      QueryResult(resultHeaders, resultRows)
    }
  }

  def groupByMetric: (List[TQueryRow]) => Map[Int, List[TQueryRow]] = {
    rows => rows.groupBy(extractMetric)
  }

  def processMetric(params: QueryParams): (Map[Int, List[TQueryRow]]) => List[QueryResult] = {
    // For each (group of rows with the same) metric
    x => {
      if (x.isEmpty)
      {
        List(QueryResult(getResultHeaders(), List()))
      }
      else {
        x.map((kvp: (Int, List[TQueryRow])) => {
          // Turn to QueryResult
          val processedRows = kvp._2.map(row => getResultRow(row))
          val queryResult = QueryResult(getResultHeaders(), processedRows)
          // Group by requested dimensions and aggregate
          groupAndAggregate(params, kvp._1)(queryResult) // => Iterable[QueryResult]
        }).toList
      }
    }
  }

  def query(params: QueryParams): Future[List[IQueryResult]] = {
    val inputParams = extractParams(params)
    val retrievedRowsFuture = executeQuery(inputParams)

    retrievedRowsFuture
      // Group retrieved data by metric
      .map(groupByMetric)
      // Calculate aggregation per metric
      .map(processMetric(params))
  }
}