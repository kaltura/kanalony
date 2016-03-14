package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.PlayRatioQuery

object ComputedMetrics extends ComputedQueryFactory[Metrics.Value] {

  val queryCreatorGetter = Map((Metrics.playRatio, playRatioQueryCreator))

  def playRatioQueryCreator: (QueryParams) => List[(IQuery, List[Metrics.Value])] = {
    (qp) => List((new PlayRatioQuery(qp), List(Metrics.playRatio)))
  }

  override def getErrorMessage(value: Metrics.Value): String = s"Computed metric ${value} is currently not supported"
}
