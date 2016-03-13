package kanalony.storage.logic.queries

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.{QueryResult, IQueryResult, QueryParams, IQuery}
import kanalony.storage.logic.queries.model.{QueryDimensionDefinition, DimensionDefinition}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
 * Created by elad.benedict on 3/13/2016.
 */
class PlayRatioQuery(internalQuery : IQuery) extends IQuery {
  override val supportedMetrics: Set[Metrics.Value] = Set(Metrics.playRatio)

  def computeResult(playsResult: List[IQueryResult], playImpressionsResult: List[IQueryResult]) = {

    val play = "play"
    val playImpression = "playImpression"
    val separator = "::"

    // There should be exactly one QueryResult for a single metric
    val playsQueryResult = playsResult.head
    val playImpressionsQueryResult = playImpressionsResult.head
    val playImpressionRows = playImpressionsQueryResult.rows.map(x => x :+ playImpression)
    val playRows = playsQueryResult.rows.map(x => x :+ play)

    val combinedRows = (playImpressionRows ::: playRows).groupBy(x => x.take(x.length - 2).mkString(separator))
    val resultRows = combinedRows.map(kv => {
      val groupData = kv._1.split(separator).toList
      val groupMetricValue1 = for {
        playRow <- kv._2.find(_.last equals play)
        playValue <- Some(playRow(playRow.length - 2).toDouble)
        playImpressionRow <- kv._2.find(_.last equals playImpression)
        playImpressionValue <- Some(playImpressionRow(playImpressionRow.length - 2).toDouble)
      } yield playValue / playImpressionValue
      groupData :+ groupMetricValue1.getOrElse(0).toString
    }).toList

    val resultHeaders = playsQueryResult.headers.take(playsQueryResult.headers.length - 1) :+ Metrics.playRatio.toString
    List(QueryResult(resultHeaders, resultRows))
  }

  override def query(params: QueryParams): Future[List[IQueryResult]] = {
    val playResultFuture = internalQuery.query(QueryParams(params.dimensionDefinitions, List(Metrics.play), params.start, params.end))
    val playImpressionResultFuture = internalQuery.query(QueryParams(params.dimensionDefinitions, List(Metrics.playImpression), params.start, params.end))

    for {
      playsResult <- playResultFuture
      playImpressionsResult <- playImpressionResultFuture
    } yield computeResult(playsResult, playImpressionsResult)

  }

  override val dimensionInformation: List[DimensionDefinition] = internalQuery.dimensionInformation
}

object PlayRatioQuery {
  def getExpandedMetricInformation(queryParams : QueryParams): QueryParams = {
    val metrics = queryParams.metrics.flatMap {
      case Metrics.playRatio => List(Metrics.play, Metrics.playImpression)
      case metric : Metrics.Value => List(metric)
    }
    QueryParams(queryParams.dimensionDefinitions, metrics, queryParams.start, queryParams.end)
  }
}
