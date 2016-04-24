package kanalony.storage.logic

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic.queries.{EstimatedMinutesWatchedQuery, ComputedQuery, PlayRatioQuery}

/**
  * Created by elad.benedict on 4/10/2016.
  */
class EstimatedMinutesWatchedQueryTests extends ComputedMetricTestsBase {
  override protected def createComputedMetricQuery(): (QueryParams, IQueryLocator) => ComputedQuery = (qp, ql) => new EstimatedMinutesWatchedQuery(qp, ql)

  override val dependentMetricsResultSet: Map[Metric, List[IQueryResult]] = {
    Map(Metrics.tenSecsViewed -> List(QueryResult(List("partner","entry", "tenSecsViewed"), List(List("1","1","6"),List("1","2","12")))))
  }
  override val expectedQueryResult: QueryResult = QueryResult(List("partner","entry","estimatedMinutesWatched"), List(List("1","2","2.0"),List("1","1","1.0")))
}
