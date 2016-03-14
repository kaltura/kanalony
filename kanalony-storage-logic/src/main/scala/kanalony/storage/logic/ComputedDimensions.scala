package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.DailyQuery
import kanalony.storage.logic.queries.model.QueryDimensionDefinition

/**
 * Created by elad.benedict on 3/7/2016.
 */
object ComputedDimensions {

  private val dimensions = Map((Dimensions.day, dailyQueryCreator))

  val values = dimensions.keys.toSet

  def getQueryCreator(value: Dimensions.Value): (QueryParams) => List[(IQuery, List[Metrics.Value])] = {
    if (!values.contains(value))
    {
      throw new IllegalArgumentException(s"Unsupported computed dimension ${value}")
    }
    dimensions(value)
  }

  def dailyQueryCreator : (QueryParams) => List[(IQuery, List[Metrics.Value])] = {
    (qp) => List((new DailyQuery(qp), qp.metrics))
  }
}
