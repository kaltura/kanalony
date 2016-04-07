package kanalony.storage.test.logic.queries

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.generated.{HourlyAggPrtnEntryClstAppRow, IHourlyAggPrtnEntryClstAppTableAccessor}
import kanalony.storage.logic.queries.{DailyMaxQuery, DailyCountQuery}
import kanalony.storage.logic.queries.model._
import kanalony.storage.logic._
import kanalony.storage.logic.generated.{Queries, HourlyAggPrtnEntryClstAppQuery}
import org.joda.time.{DateTime}
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import org.scalatest.concurrent.{ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}
import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global

class QueryLocatorTests extends FunSpec with MockFactory with BeforeAndAfterEach with ScalaFutures with Matchers with Inspectors {

  implicit val defaultPatience =
    PatienceConfig(timeout =  Span(600, Seconds), interval = Span(15, Millis))

  var computedDimensions : IComputedDimensions = null
  var computedMetrics : IComputedMetrics = null

  def createQueryLocator(availableQueries : List[IQuery]) : IQueryLocator = {
    new QueryLocator(availableQueries)
  }

  def initStubs(dimensions : Set[Dimensions.Value] = Set(), metrics : Set[Metric] = Set() ) = { // For some reason the suite fails when this logic is run in beforEach :S
    computedDimensions = stub[IComputedDimensions]
    (computedDimensions.values _).when().returning(dimensions)
    val stubDimensionQueryCreator = stubFunction[QueryParams, List[(IQuery, List[Metric])]]
    stubDimensionQueryCreator.when(*).returns(List((stub[IQuery], List())))
    dimensions.foreach(x => (computedDimensions.getQueryCreator(_)).when(x).returns(stubDimensionQueryCreator))
    computedMetrics = stub[IComputedMetrics]
    (computedMetrics.values _).when().returning(metrics)
    val stubMetricQueryCreator = stubFunction[QueryParams, List[(IQuery, List[Metric])]]
    stubMetricQueryCreator.when(*).returns(List((stub[IQuery], List())))
    metrics.foreach(x => (computedMetrics.getQueryCreator(_)).when(x).returns(stubMetricQueryCreator))
  }

  describe("QueryLocatorTests tests") {

    it("Should throw when there are no queries matching the dimensions")({

      initStubs()

      val params = QueryParams(List(createPartnerDimensionDefintion(Set(1)),createEntryDimensionDefintion(Set("1"))), List(Metrics.play), new DateTime(1), new DateTime(1000))
      val availableQueries = List(createQuery(List(Dimensions.partner), List(), Set(Metrics.play))) ::: List(createQuery(List(Dimensions.partner), List(Dimensions.browser), Set(Metrics.play)))
      val ql = createQueryLocator(availableQueries)
      intercept[QueryNotSupportedException] {
        ql.locate(params, computedDimensions, computedMetrics)
      }
    })

    it("Should throw when the total set of dimensions matches overall, but the equality constraint columns do not match the partition key columns")({

      initStubs()

      val params = QueryParams(List(createPartnerDimensionDefintion(Set(1)),createEntryDimensionDefintion(Set("1"))), List(Metrics.play), new DateTime(1), new DateTime(1000))
      val availableQueries = List(createQuery(List(Dimensions.partner), List(Dimensions.entry), Set(Metrics.play))) ::: List(createQuery(List(Dimensions.partner), List(Dimensions.browser), Set(Metrics.play)))
      val ql = createQueryLocator(availableQueries)
      intercept[QueryNotSupportedException] {
        ql.locate(params, computedDimensions, computedMetrics)
      }
    })

    it("Should throw when dimensions match but there are insufficient metrics")({

      initStubs()

      val params = QueryParams(List(createPartnerDimensionDefintion(Set(1)),createEntryDimensionDefintion(Set("1"))), List(Metrics.play, Metrics.playerImpression), new DateTime(1), new DateTime(1000))
      val availableQueries = List(createQuery(List(Dimensions.partner, Dimensions.entry), List(), Set(Metrics.play))) ::: List(createQuery(List(Dimensions.partner), List(Dimensions.browser), Set(Metrics.play)))
      val ql = createQueryLocator(availableQueries)
      intercept[QueryNotSupportedException] {
        ql.locate(params, computedDimensions, computedMetrics)
      }
    })

    it("Should succeed when there's a query satisfying both equality constraint and overall dimension and metric requirements")({

      initStubs()

      val params = QueryParams(List(createPartnerDimensionDefintion(Set(1)),createEntryDimensionDefintion(Set("1"))), List(Metrics.play), new DateTime(1), new DateTime(1000))
      val resQuery = createQuery(List(Dimensions.partner, Dimensions.entry), List(), Set(Metrics.play, Metrics.playRatio))
      val availableQueries = List(resQuery) ::: List(createQuery(List(Dimensions.partner), List(Dimensions.browser), Set(Metrics.play)))
      val ql = createQueryLocator(availableQueries)
      val locatedQueries = ql.locate(params, computedDimensions, computedMetrics)
      assert(locatedQueries.length == 1)
      assert(locatedQueries.head._1 == resQuery)
      assert(locatedQueries.head._2 == List(Metrics.play))
    })

    it("Should succeed when there's an hourly query appropriate for the requested daily query")({

      initStubs(Set(Dimensions.day))

      val params = QueryParams(List(createPartnerDimensionDefintion(Set(1)),createEntryDimensionDefintion(Set("1")), createDayDimensionDefintion()), List(Metrics.play), new DateTime(1), new DateTime(1000))
      val resQuery = createQuery(List(Dimensions.partner, Dimensions.entry, Dimensions.hour), List(), Set(Metrics.play, Metrics.playRatio))
      val availableQueries = List(resQuery) ::: List(createQuery(List(Dimensions.partner), List(Dimensions.browser), Set(Metrics.play)))
      val ql = createQueryLocator(availableQueries)
      val locatedQueries = ql.locate(params, computedDimensions, computedMetrics)
      assert(locatedQueries.length == 1)
      // TODO: Use ComputedDimensions mock to assert that getQueryCreator was called
    })

    it("Should return a few queries when a single query does not satisfy all query param requirements")({

      initStubs()

      val params = QueryParams(List(createPartnerDimensionDefintion(Set(1)),createEntryDimensionDefintion(Set("1"))), List(Metrics.play, Metrics.bufferingTime), new DateTime(1), new DateTime(1000))
      val resQuery1 = createQuery(List(Dimensions.partner, Dimensions.entry), List(), Set(Metrics.play, Metrics.playRatio))
      val resQuery2 = createQuery(List(Dimensions.partner, Dimensions.entry), List(), Set(Metrics.play, Metrics.bufferingTime))
      val availableQueries = List(resQuery1) ::: List(resQuery2) ::: List(createQuery(List(Dimensions.partner), List(Dimensions.browser), Set(Metrics.play)))
      val ql = createQueryLocator(availableQueries)
      val locatedQueries = ql.locate(params, computedDimensions, computedMetrics)
      assert(locatedQueries.length == 2)
      assert(locatedQueries(0)._1 == resQuery1)
      assert(locatedQueries(1)._1 == resQuery2)
    })


  }


  private def createQuery(eqConsCols : List[Dimensions.Value], additionalCols : List[Dimensions.Value], supportedMetrics : Set[Metric]) = {
    val res = stub[IQuery]
    (res.isMetricSupported(_)).when(where { m : Metric => supportedMetrics.contains(m) } ).returns(true)
    (res.isMetricSupported(_)).when(where { m : Metric => !supportedMetrics.contains(m) } ).returns(false)
    var dimDefs = eqConsCols.map(x => DimensionDefinition(x, new DimensionEqualityConstraint[String](Set())))
    dimDefs = dimDefs ::: additionalCols.map(x => DimensionDefinition(x, new DimensionUnconstrained))
    (res.dimensionInformation _).when().returns(dimDefs)
    (res.supportedWellKnownMetrics _).when().returns(supportedMetrics)
    res
  }

  protected def createPartnerDimensionDefintion(partners : Set[Int], includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.partner, new DimensionEqualityConstraint[Int](partners), includeInResult)
  }

  protected def createEntryDimensionDefintion(entries : Set[String], includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.entry, new DimensionEqualityConstraint[String](entries), includeInResult)
  }

  protected def createDayDimensionDefintion(includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.day, new DimensionUnconstrained, includeInResult)
  }

  protected def createHourDimensionDefintion(includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.hour, new DimensionUnconstrained, includeInResult)
  }

  protected def createAppDimensionDefintion(includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.application, new DimensionUnconstrained, includeInResult)
  }
}