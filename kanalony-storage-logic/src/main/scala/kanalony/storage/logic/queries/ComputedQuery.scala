package kanalony.storage.logic.queries

import com.kaltura.model.entities.Metric
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model.IDimensionDefinition

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 3/15/2016.
 */

case class SingleMetricQuery(metric : Metric, query : IQuery)
case class SingleMetricValue(metric : Metric, value : Double)
case class SingleMetricQueryResult(metric : Metric, queryResult : IQueryResult)

abstract class ComputedQuery(val metric : Metric, queryParams: QueryParams, queryLocator: IQueryLocator) extends IQuery {

  val separator = "::"
  val requiredMetrics : List[Metric]

  def computeValue(groupMetricsValues: List[SingleMetricValue]): Double

  private def computation(singleMetricQueryResults : List[SingleMetricQueryResult]) : List[IQueryResult] = {

    var combinedRows : List[List[String]] = List()
    singleMetricQueryResults.foreach(smqr => {
      combinedRows = combinedRows ::: smqr.queryResult.rows.map(x => x :+ smqr.metric.toString)
    })

    // Group by all columns not including the metric name and metric value
    val groupedRows = combinedRows.groupBy(x => x.take(x.length - 2).mkString(separator))
    val resultRows = groupedRows.map(kv => {
      val groupData = if (kv._1 equals "") { List() } else {kv._1.split(separator).toList }
      val groupMetricsValues = requiredMetrics.map(m => {
        val metricValue = kv._2.find(_.last equals m.toString).map(row => row(row.length-2)).getOrElse("0").toDouble
        SingleMetricValue(m, metricValue)
      })
      val computedValue = computeValue(groupMetricsValues)
      groupData :+ computedValue.toString
    }).toList

    var resultHeaders = singleMetricQueryResults.head.queryResult.headers.take(singleMetricQueryResults.head.queryResult.headers.length - 1)
    resultHeaders = resultHeaders :+ metric.name.toString
    List(QueryResult(resultHeaders, resultRows))
  }

  private def convertQueryParams(queryParams: QueryParams) : QueryParams = {
    convertQueryParams(queryParams, requiredMetrics)
  }

  private def convertQueryParams(queryParams: QueryParams, metric : Metric) : QueryParams = {
    convertQueryParams(queryParams, List(metric))
  }

  private def convertQueryParams(queryParams: QueryParams, metrics : List[Metric]) : QueryParams = {
    QueryParams(queryParams.dimensionDefinitions, metrics , queryParams.start, queryParams.end, queryParams.timezoneOffset, queryParams.orderBy, queryParams.pager)
  }

  override def query(params: QueryParams): Future[List[IQueryResult]] = {
    if (!queryParams.metrics.contains(metric))
    {
      throw new IllegalArgumentException(s"Expected query param metrics to contain ${metric.toString}")
    }

    val updatedQueryParams = convertQueryParams(queryParams)
    val queryLocationResult = queryLocator.locate(updatedQueryParams, ComputedDimensions, ComputedMetrics)
    val metricResults = requiredMetrics.map(m => {
      val query = queryLocationResult.find(_._2.contains(m)).get._1
      val singleMetricQueryParams = convertQueryParams(queryParams, m)
      query.query(singleMetricQueryParams).map(queryResults => SingleMetricQueryResult(m, queryResults.head))
    })

    Future.sequence(metricResults).map(computation)
  }

  override val dimensionInformation: List[IDimensionDefinition] = queryParams.dimensionDefinitions

  override def isMetricSupported(m : Metric): Boolean = m == metric

  val supportedWellKnownMetrics = Set(metric)

}
