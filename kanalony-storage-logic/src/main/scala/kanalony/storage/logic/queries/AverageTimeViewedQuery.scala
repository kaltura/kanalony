package kanalony.storage.logic.queries

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic.{IQueryLocator, QueryParams}

/**
 * Created by elad.benedict on 3/15/2016.
 */

class AverageTimeViewedQuery(queryParams: QueryParams, queryLocator: IQueryLocator) extends ComputedQuery(Metrics.averageViewDuration, queryParams, queryLocator) {
  override val requiredMetrics: List[Metric] = List(Metrics.estimatedMinutesWatched, Metrics.play)

  override def computeValue(groupMetricsValues: List[SingleMetricValue]): Double = {
    val estimatedMinutesWatched = groupMetricsValues.find(_.metric == Metrics.estimatedMinutesWatched).get.value
    val plays = groupMetricsValues.find(_.metric == Metrics.play).get.value
    if (plays == 0) { 0 }
    else { estimatedMinutesWatched/60/plays }
  }
}
