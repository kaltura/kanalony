package kanalony.storage.logic

import com.kaltura.model.entities.InternalMetrics
import kanalony.storage.logic.queries.{AverageViewDropOffQuery, AverageTimeViewedQuery, EstimatedMinutesWatchedQuery, PlayRatioQuery}

object ComputedMetrics extends ComputedQueryFactory[InternalMetrics.Value] {

  val queryCreatorGetter = Map((InternalMetrics.playRatio, playRatioQueryCreator),
                               (InternalMetrics.estimatedMinutesWatched, estimatedMinutesWatchedQueryCreator),
                               (InternalMetrics.averageViewDuration, averageViewDurationQueryCreator),
                               (InternalMetrics.averageViewDropOff, averageViewDropOffQueryCreator))

  def playRatioQueryCreator: (QueryParams) => List[(IQuery, List[InternalMetrics.Value])] = {
    (qp) => List((new PlayRatioQuery(qp), List(InternalMetrics.playRatio)))
  }

  def estimatedMinutesWatchedQueryCreator: (QueryParams) => List[(IQuery, List[InternalMetrics.Value])] = {
    (qp) => List((new EstimatedMinutesWatchedQuery(qp), List(InternalMetrics.estimatedMinutesWatched)))
  }

  def averageViewDurationQueryCreator: (QueryParams) => List[(IQuery, List[InternalMetrics.Value])] = {
    (qp) => List((new AverageTimeViewedQuery(qp), List(InternalMetrics.averageViewDuration)))
  }

  def averageViewDropOffQueryCreator: (QueryParams) => List[(IQuery, List[InternalMetrics.Value])] = {
    (qp) => List((new AverageViewDropOffQuery(qp), List(InternalMetrics.averageViewDropOff)))
  }

  override def getErrorMessage(value: InternalMetrics.Value): String = s"Computed metric ${value} is currently not supported"
}
