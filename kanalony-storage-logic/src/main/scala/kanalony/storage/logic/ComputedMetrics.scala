package kanalony.storage.logic

import com.kaltura.model.entities.Metrics

/**
 * Created by elad.benedict on 3/7/2016.
 */
object ComputedMetrics {
  def getQueryCreator(value: Metrics.Value): (QueryParams) => List[(IQuery, List[Metrics.Value])] = ???
}
