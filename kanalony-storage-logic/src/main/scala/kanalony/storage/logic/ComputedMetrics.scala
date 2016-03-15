package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.{EstimatedMinutesWatchedQuery, PlayRatioQuery}

object ComputedMetrics extends ComputedQueryFactory[Metrics.Value] {

  val queryCreatorGetter = Map((Metrics.playRatio, playRatioQueryCreator),
                               (Metrics.estimatedMinutesWatched, estimatedMinutesWatchedQueryCreator))

  def playRatioQueryCreator: (QueryParams) => List[(IQuery, List[Metrics.Value])] = {
    (qp) => List((new PlayRatioQuery(qp), List(Metrics.playRatio)))
  }

  def estimatedMinutesWatchedQueryCreator: (QueryParams) => List[(IQuery, List[Metrics.Value])] = {
    (qp) => List((new EstimatedMinutesWatchedQuery(qp), List(Metrics.estimatedMinutesWatched)))
  }

  override def getErrorMessage(value: Metrics.Value): String = s"Computed metric ${value} is currently not supported"
}
