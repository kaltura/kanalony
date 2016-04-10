package kanalony.storage.logic

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic.queries.ComputedQuery
import kanalony.storage.logic.queries.model.{QueryDimensionDefinition, DimensionEqualityConstraint}
import org.joda.time.DateTime
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.Promise

abstract class ComputedMetricTestsBase extends FunSpec with MockFactory with BeforeAndAfterEach with ScalaFutures with Matchers with Inspectors {

  protected def createComputedMetricQuery() : (QueryParams, IQueryLocator) => ComputedQuery
  val dependentMetricsResultSet: Map[Metric, List[IQueryResult]]
  val expectedQueryResult: QueryResult

  var query : ComputedQuery = null
  var queryLocatorStub : IQueryLocator = null

  implicit val defaultPatience =
    PatienceConfig(timeout =  Span(600, Seconds), interval = Span(15, Millis))

  def initStubs() = {
    queryLocatorStub = stub[IQueryLocator]
  }

  describe("ComputedQuery tests") {
    it("Should throw when queried with incorrect metric")({
      initStubs()
      val queryParams = QueryParams(List(), List(Metrics.play), new DateTime(1), new DateTime(1000))
      query = createComputedMetricQuery()(queryParams, queryLocatorStub)
      intercept[IllegalArgumentException] {
        query.query(queryParams)
      }
    })

    it("Should call query locator with dependent metrics")({
      initStubs()
      val queryParams = QueryParams(List(), List(query.metric), new DateTime(1), new DateTime(1000))
      query = createComputedMetricQuery()(queryParams, queryLocatorStub)
      val locatedQueryStub = stub[IQuery]
      (locatedQueryStub.query _).when(*).returns(createCompletedFuture(List()))
      (queryLocatorStub.locate _).when(*, *, *).returns(List((locatedQueryStub,query.requiredMetrics)))
      val expectedParams = QueryParams(List(), query.requiredMetrics, new DateTime(1), new DateTime(1000))

      query.query(queryParams)

      (queryLocatorStub.locate(_, _, _)).verify(expectedParams, *, *)
    })

    it("Should query each dependant metric's query with its metric")({
      initStubs()
      val queryParams = QueryParams(List(), List(query.metric), new DateTime(1), new DateTime(1000))
      query = createComputedMetricQuery()(queryParams, queryLocatorStub)
      val dependentQueryStub = stub[IQuery]
      (dependentQueryStub.query _).when(*).returns(createCompletedFuture(List()))
      val queryLocatorResults = List((dependentQueryStub, query.requiredMetrics))
      (queryLocatorStub.locate _).when(*, *, *).returns(queryLocatorResults)

      query.query(queryParams)

      query.requiredMetrics.foreach(m => {
        val expectedParams = QueryParams(List(), List(m), new DateTime(1), new DateTime(1000))
        (dependentQueryStub.query _).verify(expectedParams)
      })
    })

    it("Should correctly compute metric value")({
      initStubs()
      val partnerDimensionDefinition = new QueryDimensionDefinition(Dimensions.partner, new DimensionEqualityConstraint[Int](Set(1)), true)
      val entryDimensionDefinition = new QueryDimensionDefinition(Dimensions.entry, new DimensionEqualityConstraint[String](Set("1")), true)
      val queryParams = QueryParams(List(partnerDimensionDefinition, entryDimensionDefinition), List(query.metric), new DateTime(1), new DateTime(1000))
      query = createComputedMetricQuery()(queryParams, queryLocatorStub)
      val dependentQueryStub = stub[IQuery]
      query.requiredMetrics.foreach(m => {
        val expectedParams = QueryParams(List(partnerDimensionDefinition, entryDimensionDefinition), List(m), new DateTime(1), new DateTime(1000))
        (dependentQueryStub.query _).when(expectedParams).returns(createCompletedFuture(dependentMetricsResultSet(m)))
      })
      val queryLocatorResults = List((dependentQueryStub, query.requiredMetrics))
      (queryLocatorStub.locate _).when(*, *, *).returns(queryLocatorResults)

      whenReady(query.query(queryParams)){ res => assert(res == List(expectedQueryResult)) }

    })

  }

  protected def createCompletedFuture[T](value : T) = Promise[T]().success(value).future
}


