package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.PlayRatioQuery

/**
 * Created by elad.benedict on 3/7/2016.
 */
object ComputedMetrics {
  def getQueryCreator(value: Metrics.Value): (QueryParams) => List[(IQuery, List[Metrics.Value])] = {
    if (value != Metrics.playRatio)
    {
      throw new IllegalArgumentException("Only playRatio is currently supported")
    }
    (qp) => {
      // TODO: pass only metric specific metrics - no need to pass all metrics here...
      val updatedQueryParams = PlayRatioQuery.getExpandedMetricInformation(qp)
      QueryLocator.locate(updatedQueryParams).map(x => (new PlayRatioQuery(x._1), x._2))
    }
  }
}
