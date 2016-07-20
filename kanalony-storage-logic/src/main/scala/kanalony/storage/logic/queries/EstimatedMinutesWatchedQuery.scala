package kanalony.storage.logic.queries

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic._

/**
 * Created by elad.benedict on 3/14/2016.
 */

class EstimatedMinutesWatchedQuery(queryParams: QueryParams, queryLocator: IQueryLocator) extends ComputedQuery(Metrics.estimatedMinutesWatched, queryParams, queryLocator) {
  override val requiredMetrics: List[Metric] = List(Metrics.view)

  override def computeValue(groupMetricsValues: List[SingleMetricValue]): Double = {
    groupMetricsValues.head.value / 6
  }
}
