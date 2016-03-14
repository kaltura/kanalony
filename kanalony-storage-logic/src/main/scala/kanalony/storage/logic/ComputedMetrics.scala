package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.PlayRatioQuery

/**
 * Created by elad.benedict on 3/7/2016.
 */
object ComputedMetrics {

  private val metrics = Map((Metrics.playRatio, playRatioQueryCreator))

  val values = metrics.keys.toSet

  def getQueryCreator(value: Metrics.Value): (QueryParams) => List[(IQuery, List[Metrics.Value])] = {
    if (!values.contains(value))
    {
      throw new IllegalArgumentException(s"Unsupported computed metric ${value}")
    }
    metrics(value)
  }

  def playRatioQueryCreator: (QueryParams) => List[(IQuery, List[Metrics.Value])] = {
    (qp) => List((new PlayRatioQuery(qp), List(Metrics.playRatio)))
  }
}
