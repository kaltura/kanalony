package kanalony.storage.logic.queries

import kanalony.storage.logic.QueryParams

/**
 * Created by elad.benedict on 3/28/2016.
 */
class DailyMaxQuery(queryParams: QueryParams) extends DailyQueryBase(queryParams) {
  override def computeGroupAggregatedValue: (List[List[String]]) => Double = _.map(countFieldExtractor(_)).max
}
