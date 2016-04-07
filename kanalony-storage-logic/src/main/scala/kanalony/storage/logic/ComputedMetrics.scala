package kanalony.storage.logic

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic.queries.{AverageViewDropOffQuery, AverageTimeViewedQuery, EstimatedMinutesWatchedQuery, PlayRatioQuery}

trait IComputedMetrics {
  def getQueryCreator(m : Metric) : (QueryParams) => List[(IQuery, List[Metric])]
  def values : Set[Metric]
}

object ComputedMetrics extends ComputedQueryFactory[Metric] with IComputedMetrics {

  val queryCreatorGetter = Map((Metrics.playRatio, playRatioQueryCreator),
                               (Metrics.estimatedMinutesWatched, estimatedMinutesWatchedQueryCreator),
                               (Metrics.averageViewDuration, averageViewDurationQueryCreator),
                               (Metrics.averageViewDropOff, averageViewDropOffQueryCreator))

  def playRatioQueryCreator: (QueryParams) => List[(IQuery, List[Metric])] = {
    (qp) => List((new PlayRatioQuery(qp), List(Metrics.playRatio)))
  }

  def estimatedMinutesWatchedQueryCreator: (QueryParams) => List[(IQuery, List[Metric])] = {
    (qp) => List((new EstimatedMinutesWatchedQuery(qp), List(Metrics.estimatedMinutesWatched)))
  }

  def averageViewDurationQueryCreator: (QueryParams) => List[(IQuery, List[Metric])] = {
    (qp) => List((new AverageTimeViewedQuery(qp), List(Metrics.averageViewDuration)))
  }

  def averageViewDropOffQueryCreator: (QueryParams) => List[(IQuery, List[Metric])] = {
    (qp) => List((new AverageViewDropOffQuery(qp), List(Metrics.averageViewDropOff)))
  }

  override def getErrorMessage(value: Metric): String = s"Computed metric ${value} is currently not supported"
}
