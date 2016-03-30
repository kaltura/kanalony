package kanalony.storage.logic.queries

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 3/13/2016.
 */

class PlayRatioQuery(queryParams: QueryParams) extends ComputedQuery(Metrics.playRatio, queryParams) {
  override val requiredMetrics: List[Metric] = List(Metrics.play, Metrics.playImpression)

  override def computeValue(groupMetricsValues: List[SingleMetricValue]): Double = {
    val playValue = groupMetricsValues.find(_.metric == Metrics.play).get.value
    val playImpressionValue = groupMetricsValues.find(_.metric == Metrics.playImpression).get.value
    playValue/playImpressionValue
  }
}
