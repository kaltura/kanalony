package kanalony.storage.logic.queries

import com.kaltura.model.entities.InternalMetrics
import kanalony.storage.logic.QueryParams

/**
 * Created by elad.benedict on 3/15/2016.
 */

class AverageTimeViewedQuery(queryParams: QueryParams) extends ComputedQuery(InternalMetrics.averageViewDuration, queryParams) {
  override val requiredMetrics: List[InternalMetrics.Value] = List(InternalMetrics.estimatedMinutesWatched, InternalMetrics.play)

  override def computeValue(groupMetricsValues: List[SingleMetricValue]): Double = {
    val estimatedMinutesWatched = groupMetricsValues.find(_.metric == InternalMetrics.estimatedMinutesWatched).get.value
    val plays = groupMetricsValues.find(_.metric == InternalMetrics.play).get.value
    if (plays == 0) { 0 }
    else { estimatedMinutesWatched/60/plays }
  }
}
