package kanalony.storage.logic.queries

import com.kaltura.model.entities.InternalMetrics
import kanalony.storage.logic.QueryParams

/**
 * Created by elad.benedict on 3/15/2016.
 */

class AverageViewDropOffQuery(queryParams: QueryParams) extends ComputedQuery(InternalMetrics.averageViewDropOff, queryParams) {
  override val requiredMetrics: List[InternalMetrics.Value] = List(InternalMetrics.play, InternalMetrics.playThrough25, InternalMetrics.playThrough50, InternalMetrics.playThrough75, InternalMetrics.playThrough100)

  override def computeValue(groupMetricsValues: List[SingleMetricValue]): Double = {
    val plays = groupMetricsValues.find(_.metric == InternalMetrics.play).get.value
    val playThrough25 = groupMetricsValues.find(_.metric == InternalMetrics.playThrough25).get.value
    val playThrough50 = groupMetricsValues.find(_.metric == InternalMetrics.playThrough50).get.value
    val playThrough75 = groupMetricsValues.find(_.metric == InternalMetrics.playThrough75).get.value
    val playThrough100 = groupMetricsValues.find(_.metric == InternalMetrics.playThrough100).get.value
    (0.25*playThrough25 + 0.25*playThrough50 + 0.25*playThrough75 + 0.25*playThrough100) / plays
  }
}