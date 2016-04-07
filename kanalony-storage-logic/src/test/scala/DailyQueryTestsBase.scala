package kanalony.storage.test.logic.queries

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.generated.{HourlyAggPrtnEntryClstAppRow, IHourlyAggPrtnEntryClstAppTableAccessor}
import kanalony.storage.logic.queries.{DailyMaxQuery, DailyCountQuery}
import kanalony.storage.logic.queries.model.{DimensionUnconstrained, DimensionRangeConstraint, DimensionEqualityConstraint, QueryDimensionDefinition}
import kanalony.storage.logic._
import kanalony.storage.logic.generated.{Queries, HourlyAggPrtnEntryClstAppQuery}
import org.joda.time.{DateTime}
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import org.scalatest.concurrent.{ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}
import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global

abstract class DailyQueryTestsBase[T <: IQuery] extends FunSpec with MockFactory with BeforeAndAfterEach with ScalaFutures with Matchers with Inspectors {

  def createDailyQuery(queryParams: QueryParams, queryLocator: IQueryLocator) : T
  var dailyQueryCreatorWithStub : (QueryParams) => T = null
  var dailyQueryCreatorWithMock : (QueryParams) => T = null
  var queryLocatorStub : IQueryLocator = null
  var queryLocatorMock : IQueryLocator = null

  implicit val defaultPatience =
    PatienceConfig(timeout =  Span(600, Seconds), interval = Span(15, Millis))


  override def beforeEach = {
    queryLocatorStub = stub[IQueryLocator]
    queryLocatorMock = mock[IQueryLocator]
    dailyQueryCreatorWithStub = createDailyQuery(_ , queryLocatorStub)
    dailyQueryCreatorWithMock = createDailyQuery(_ , queryLocatorMock)
  }

  describe("DailyCountQueryTests tests") {
    it("Should throw when query params don't contain 'day' dimension")({
      intercept[IllegalArgumentException] {
        dailyQueryCreatorWithStub(QueryParams(List(), List(), new DateTime(1), new DateTime(2)))
      }
    })

    it("Should throw when no relevant hourly queries are found for the requested daily query")({
      val params = QueryParams(List(createPartnerDimensionDefintion(Set(1)), createDayDimensionDefintion()), List(), new DateTime(1), new DateTime(2))
      val expectedParams = QueryParams(List(createPartnerDimensionDefintion(Set(1)), createHourDimensionDefintion()), List(), new DateTime(1), new DateTime(2))
      configureStub(expectedParams, List())
      intercept[IllegalArgumentException] {
        dailyQueryCreatorWithStub(params)
      }
    })

    it("Should convert day dimension to hour dimension when locating an appropriate query")({
      val params = QueryParams(List(createPartnerDimensionDefintion(Set(1)), createDayDimensionDefintion()), List(), new DateTime(1), new DateTime(2))
      val expectedParams = QueryParams(List(createPartnerDimensionDefintion(Set(1)), createHourDimensionDefintion()), List(), new DateTime(1), new DateTime(2))
      configureMock(expectedParams, List((stub[IQuery], List()))) // The response is meaningless for this test - just use some IQuery
      dailyQueryCreatorWithMock(params)
      // Assertion done by the Mock
    })
  }

  protected def createCompletedFuture[T](value : T) = Promise[T]().success(value).future

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

  protected def configureStub(params : QueryParams, result : List[(IQuery, List[Metric])]) = {
    (queryLocatorStub.locate(_, _, _)).
      when(params, * , *).
      returns(result)
  }

  protected def configureMock(params : QueryParams, result : List[(IQuery, List[Metric])]) = {
    (queryLocatorMock.locate(_, _, _)).
      expects(params, * , *).
      returning(result)
  }

}


