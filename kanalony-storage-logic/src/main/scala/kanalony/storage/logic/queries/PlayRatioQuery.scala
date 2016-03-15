package kanalony.storage.logic.queries

import com.kaltura.model.entities.InternalMetrics
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 3/13/2016.
 */
class PlayRatioQuery(queryParams : QueryParams) extends IQuery {

  if (!queryParams.metrics.contains(InternalMetrics.playRatio))
  {
    throw new IllegalArgumentException("PlayRatioQuery expects query params with Metrics.playRatio")
  }

  val updatedQueryParams = convertQueryParams(queryParams)
  val queryLocationResult = QueryLocator.locate(updatedQueryParams)

  // All requested metrics must be available - otherwise QueryLocator would throw an exception
  val internalPlayQuery = queryLocationResult.find(_._2 contains(InternalMetrics.play)).get._1
  val internalPlayImpressionQuery = queryLocationResult.find(_._2 contains(InternalMetrics.playImpression)).get._1

  override val supportedMetrics: Set[InternalMetrics.Value] = Set(InternalMetrics.playRatio)

  override def query(params: QueryParams): Future[List[IQueryResult]] = {
    val playResultFuture = internalPlayQuery.query(QueryParams(params.dimensionDefinitions, List(InternalMetrics.play), params.start, params.end))
    val playImpressionResultFuture = internalPlayImpressionQuery.query(QueryParams(params.dimensionDefinitions, List(InternalMetrics.playImpression), params.start, params.end))

    for {
      playsResult <- playResultFuture
      playImpressionsResult <- playImpressionResultFuture
    } yield computeResult(playsResult, playImpressionsResult)
  }

  def computeResult(playsResult: List[IQueryResult], playImpressionsResult: List[IQueryResult]) = {
    val play = "play"
    val playImpression = "playImpression"
    val separator = "::"

    // There should be exactly one QueryResult for a single metric
    val playsQueryResult = playsResult.head
    val playImpressionsQueryResult = playImpressionsResult.head

    // Store the metric name for differentiation after grouping
    val playImpressionRows = playImpressionsQueryResult.rows.map(x => x :+ playImpression)
    val playRows = playsQueryResult.rows.map(x => x :+ play)

    // Group by all columns not including the metric name and metric value
    val combinedRows = (playImpressionRows ::: playRows).groupBy(x => x.take(x.length - 2).mkString(separator))
    val resultRows = combinedRows.map(kv => {
      val groupData = kv._1.split(separator).toList
      val groupMetricValue = for {
        playRow <- kv._2.find(_.last equals play)
        playValue <- Some(playRow(playRow.length - 2).toDouble)
        playImpressionRow <- kv._2.find(_.last equals playImpression)
        playImpressionValue <- Some(playImpressionRow(playImpressionRow.length - 2).toDouble)
      } yield playValue / playImpressionValue

      groupData :+ groupMetricValue.getOrElse(0).toString
    }).toList

    val resultHeaders = playsQueryResult.headers.take(playsQueryResult.headers.length - 1) :+ InternalMetrics.playRatio.toString
    List(QueryResult(resultHeaders, resultRows))
  }

  override val dimensionInformation: List[IDimensionDefinition] = queryParams.dimensionDefinitions

  private def convertQueryParams(queryParams : QueryParams): QueryParams = {
    QueryParams(queryParams.dimensionDefinitions, List(InternalMetrics.play, InternalMetrics.playImpression), queryParams.start, queryParams.end)
  }
}

