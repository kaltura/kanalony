package kanalony.storage.logic.queries

import com.kaltura.model.entities.InternalMetrics
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 3/13/2016.
 */

class PlayRatioQuery(queryParams: QueryParams) extends ComputedQuery(InternalMetrics.playRatio, queryParams) {
  override val requiredMetrics: List[InternalMetrics.Value] = List(InternalMetrics.play, InternalMetrics.playImpression)

  override def computeValue(groupMetricsValues: List[SingleMetricValue]): Double = {
    val playValue = groupMetricsValues.find(_.metric == InternalMetrics.play).get.value
    val playImpressionValue = groupMetricsValues.find(_.metric == InternalMetrics.playImpression).get.value
    playValue/playImpressionValue
  }
}
