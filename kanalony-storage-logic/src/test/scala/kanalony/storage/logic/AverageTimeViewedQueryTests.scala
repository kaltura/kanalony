package kanalony.storage.logic

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic.queries.{AverageTimeViewedQuery, AverageViewDropOffQuery, ComputedQuery}

/**
  * Created by elad.benedict on 4/10/2016.
  */
class AverageTimeViewedQueryTests extends ComputedMetricTestsBase {
  override protected def createComputedMetricQuery(): (QueryParams, IQueryLocator) => ComputedQuery = (qp, ql) => new AverageTimeViewedQuery(qp, ql)

  override val dependentMetricsResultSet: Map[Metric, List[IQueryResult]] = {
    Map(Metrics.play -> List(QueryResult(List("partner","entry", "play"), List(List("1","2","4"),List("1","1","2")))),
      Metrics.estimatedMinutesWatched -> List(QueryResult(List("partner","entry", "estimatedMinutesWatched"), List(List("1","2","240"),List("1","1","120")))))

  }
  override val expectedQueryResult: QueryResult = QueryResult(List("partner","entry","averageViewDuration"), List(List("1","2","1.0"),List("1","1","1.0")))
}
