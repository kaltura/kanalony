package kanalony.storage.logic

import kanalony.storage.api.DbClientFactory
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/10/2016.
 */

abstract class QueryBase[TReq, TQueryRow] extends IQuery {

  val dbApi = DbClientFactory

  val metricValueHeaderName = "value"

  private[logic] def extractParams(queryParams : QueryParams) : TReq

  private[logic] def executeQuery(params : TReq) : Future[List[TQueryRow]]

  private[logic] def getResultHeaders() : List[String]

  protected def getResultRow(row: TQueryRow) : List[String]

  def metricValueLocationIndex : Int

  def getGroupByKey(headerDimensionIndexes : List[Int]): (List[String]) => String = {
    row => {
      var groupValues :List[String] = List()
      headerDimensionIndexes.foreach(i => groupValues = groupValues :+ row(i))
      groupValues.mkString(":")
    }
  }

  def getGroupAggregatedValue(v: List[List[String]]) : Double = {
    v.map(row => row(metricValueLocationIndex).toDouble).sum
  }

  def groupAndAggregate(params : QueryParams): QueryResult => QueryResult = {

    val resultDimensions = params.dimensionDefinitions.filter(_.includeInResult).map(_.dimension)

    def getGroupDimensionIndexes(queryResult : QueryResult) = {
      val indexedResultDimensions = queryResult.headers.zipWithIndex
      resultDimensions.map(value =>  {
        indexedResultDimensions.find(_._1 eq value.toString).map(_._2).get
      })
    }

    queryResult => {
      val headerDimensionIndexes = getGroupDimensionIndexes(queryResult)
      val resultHeaders = resultDimensions.map(_.toString) :+ params.metric.toString
      val groupedData = queryResult.rows.groupBy(getGroupByKey(headerDimensionIndexes))

      val resultRows = groupedData.toList.map((group : (String,List[List[String]])) => {
        var rowData = if (group._1.isEmpty) { List() } else { group._1.split(':').toList }
        val groupAggregatedValue = getGroupAggregatedValue(group._2).toString
        rowData = rowData :+ groupAggregatedValue
        rowData
      })

      QueryResult(resultHeaders, resultRows)
    }
  }

  def query(params : QueryParams): Future[IQueryResult] = {
    val inputParams = extractParams(params)
    val retrievedRowsFuture = executeQuery(inputParams)
    val rawData = retrievedRowsFuture.map {
      retrievedRows => {
        val processedRows = retrievedRows.map(row => getResultRow(row))
        new QueryResult(getResultHeaders(), processedRows)
      }
    }

    val groupedData = rawData map groupAndAggregate(params)
    groupedData
  }
}