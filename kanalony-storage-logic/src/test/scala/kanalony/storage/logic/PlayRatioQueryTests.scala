package kanalony.storage.logic

import com.kaltura.model.entities.{Metrics, Metric}
import kanalony.storage.logic.queries.{PlayRatioQuery, ComputedQuery}

/**
  * Created by elad.benedict on 4/10/2016.
  */
class PlayRatioQueryTests extends ComputedMetricTestsBase {
  override protected def createComputedMetricQuery(): (QueryParams, IQueryLocator) => ComputedQuery = (qp, ql) => new PlayRatioQuery(qp, ql)

  override val dependentMetricsResultSet: Map[Metric, List[IQueryResult]] = {
    Map(Metrics.play -> List(QueryResult(List("partner","entry", "play"), List(List("1","1","2"),List("1","2","3")))),
        Metrics.playerImpression -> List(QueryResult(List("partner","entry","playImpression"), List(List("1","1","4"),List("1","2","4")))))
  }
  override val expectedQueryResult: QueryResult = QueryResult(List("partner","entry","playRatio"), List(List("1","2","0.75"),List("1","1","0.5")))
}
