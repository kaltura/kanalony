package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.DailyQuery
import kanalony.storage.logic.queries.model.QueryDimensionDefinition

/**
 * Created by elad.benedict on 3/7/2016.
 */
object ComputedDimensions {
  def getQueryCreator(value: Dimensions.Value): (QueryParams) => List[(IQuery, List[Metrics.Value])] = {
    if (value != Dimensions.day)
    {
      throw new IllegalArgumentException("Only day is currently supported")
    }
    (qp) => {
      val updatedDimensionInformation = DailyQuery.getExpandedDimensionInformation(qp.dimensionDefinitions)
      val updatedQueryParams = QueryParams(updatedDimensionInformation, qp.metrics, qp.start, qp.end)
      QueryLocator.locate(updatedQueryParams).map(x => (new DailyQuery(x._1), x._2))
    }
  }
}
