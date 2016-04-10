package kanalony.storage.logic.queries

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic.{IQueryLocator, QueryParams}

/**
 * Created by elad.benedict on 3/15/2016.
 */

class AverageViewDropOffQuery(queryParams: QueryParams, queryLocator: IQueryLocator) extends ComputedQuery(Metrics.averageViewDropOff, queryParams, queryLocator) {
  override val requiredMetrics: List[Metric] = List(Metrics.play, Metrics.playThrough25, Metrics.playThrough50, Metrics.playThrough75, Metrics.playThrough100)

  override def computeValue(groupMetricsValues: List[SingleMetricValue]): Double = {
    val plays = groupMetricsValues.find(_.metric == Metrics.play).get.value
    val playThrough25 = groupMetricsValues.find(_.metric == Metrics.playThrough25).get.value
    val playThrough50 = groupMetricsValues.find(_.metric == Metrics.playThrough50).get.value
    val playThrough75 = groupMetricsValues.find(_.metric == Metrics.playThrough75).get.value
    val playThrough100 = groupMetricsValues.find(_.metric == Metrics.playThrough100).get.value
    if (plays == 0) { 0 }
    else { (0.25*playThrough25 + 0.25*playThrough50 + 0.25*playThrough75 + 0.25*playThrough100) / plays }
  }
}