package kanalony.storage.logic.queries

import kanalony.storage.logic.{IQueryLocator, QueryParams}

/**
 * Created by elad.benedict on 3/28/2016.
 */
class DailyMaxQuery(queryParams: QueryParams, queryLocator: IQueryLocator) extends DailyQueryBase(queryParams, queryLocator) {
  override def computeGroupAggregatedValue: (List[List[String]]) => Double = _.map(countFieldExtractor(_)).max
}
