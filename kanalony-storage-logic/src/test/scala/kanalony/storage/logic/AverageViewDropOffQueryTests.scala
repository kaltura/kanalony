package kanalony.storage.logic

import com.kaltura.model.entities.{Metrics, Metric}
import kanalony.storage.logic.queries.{AverageViewDropOffQuery, ComputedQuery, EstimatedMinutesWatchedQuery}

/**
  * Created by elad.benedict on 4/10/2016.
  */
class AverageViewDropOffQueryTests extends ComputedMetricTestsBase {
  override protected def createComputedMetricQuery(): (QueryParams, IQueryLocator) => ComputedQuery = (qp, ql) => new AverageViewDropOffQuery(qp, ql)

  override val dependentMetricsResultSet: Map[Metric, List[IQueryResult]] = {
    Map(Metrics.play -> List(QueryResult(List("partner","entry", "play"), List(List("1","2","16"),List("1","1","4")))),
      Metrics.playThrough25 -> List(QueryResult(List("partner","entry", "playThrough25"), List(List("1","2","16"),List("1","1","4")))),
      Metrics.playThrough50 -> List(QueryResult(List("partner","entry", "playThrough50"), List(List("1","2","12"),List("1","1","3")))),
      Metrics.playThrough75 -> List(QueryResult(List("partner","entry", "playThrough75"), List(List("1","2","8"),List("1","1","2")))),
      Metrics.playThrough100 -> List(QueryResult(List("partner","entry", "playThrough100"), List(List("1","2","4"),List("1","1","1")))))

  }
  override val expectedQueryResult: QueryResult = QueryResult(List("partner","entry","averageViewDropOff"), List(List("1","2","0.625"),List("1","1","0.625")))
}
