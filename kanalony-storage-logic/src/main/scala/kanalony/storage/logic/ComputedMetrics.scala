package kanalony.storage.logic

import com.kaltura.model.entities.InternalMetrics
import kanalony.storage.logic.queries.{EstimatedMinutesWatchedQuery, PlayRatioQuery}

object ComputedMetrics extends ComputedQueryFactory[InternalMetrics.Value] {

  val queryCreatorGetter = Map((InternalMetrics.playRatio, playRatioQueryCreator),
                               (InternalMetrics.estimatedMinutesWatched, estimatedMinutesWatchedQueryCreator))

  def playRatioQueryCreator: (QueryParams) => List[(IQuery, List[InternalMetrics.Value])] = {
    (qp) => List((new PlayRatioQuery(qp), List(InternalMetrics.playRatio)))
  }

  def estimatedMinutesWatchedQueryCreator: (QueryParams) => List[(IQuery, List[InternalMetrics.Value])] = {
    (qp) => List((new EstimatedMinutesWatchedQuery(qp), List(InternalMetrics.estimatedMinutesWatched)))
  }

  override def getErrorMessage(value: InternalMetrics.Value): String = s"Computed metric ${value} is currently not supported"
}
