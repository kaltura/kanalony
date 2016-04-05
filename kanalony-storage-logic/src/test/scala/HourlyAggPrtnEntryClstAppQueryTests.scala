package kanalony.storage.test.logic.queries

import com.kaltura.model.entities.Metrics
import kanalony.storage.generated.{HourlyAggPrtnEntryClstAppRow, IHourlyAggPrtnEntryClstAppTableAccessor}
import kanalony.storage.logic.queries.model.{DimensionUnconstrained, DimensionRangeConstraint, DimensionEqualityConstraint, QueryDimensionDefinition}
import kanalony.storage.logic.{QueryResult, Dimensions, QueryParams}
import kanalony.storage.logic.generated.HourlyAggPrtnEntryClstAppQuery
import org.joda.time.{DateTime}
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import org.scalatest.concurrent.{ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}
import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by elad.benedict on 4/5/2016.
 */

class HourlyAggPrtnEntryClstAppQueryTests extends FunSpec with MockFactory with BeforeAndAfterEach with ScalaFutures with Matchers with Inspectors {

  var query : HourlyAggPrtnEntryClstAppQuery = null
  var tableAccessorStub : IHourlyAggPrtnEntryClstAppTableAccessor = null
  def tableAccessorStubQueryMethod = tableAccessorStub.query(_:List[Int], _:List[String], _:List[String], _:List[Int])
  implicit val defaultPatience =
    PatienceConfig(timeout =  Span(600, Seconds), interval = Span(15, Millis))


  override def beforeEach = {
    tableAccessorStub = stub[IHourlyAggPrtnEntryClstAppTableAccessor]
    query = new HourlyAggPrtnEntryClstAppQuery(tableAccessorStub)
  }

  describe("HourlyAggPrtnEntryClstAppQuery tests") {
    it("Should throw when query params don't have values for all partition key columns")({
      val params = QueryParams(List(QueryDimensionDefinition(
        Dimensions.partner,
        new DimensionEqualityConstraint[Int](Set(1, 2)), true)),
        List(Metrics.play),
        new DateTime(1),
        new DateTime(1000))

      whenReady(query.query(params).failed){ e =>
        e shouldBe a [IllegalArgumentException]
      }
    })

    it("Should query table with the correct parameters")({
      configureStub(List(1,2),List("1","2"),List("play"),List(1970),new DateTime(1), new DateTime(1000),
                    List(HourlyAggPrtnEntryClstAppRow(1,"1","play",1970, new DateTime(1),"app",5)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1, 2)),
        createEntryDimensionDefintion(Set("1", "2"))),
        List(Metrics.play),
        new DateTime(1),
        new DateTime(1000))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner","entry","play"),List(List("1","1","5.0"))))) }
    })

    ignore("Should return a correct response when there are no relevant rows in the DB")({
      (tableAccessorStub.query(_:List[Int], _:List[String], _:List[String], _:List[Int], _:DateTime, _:DateTime)).
        when(*, *, *, *, *, *).
        returns(createCompletedFuture(List()))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1, 2)),
        createEntryDimensionDefintion(Set("1", "2"))),
        List(Metrics.play),
        new DateTime(1),
        new DateTime(1000))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner","entry","play"),List()))) }
    })

  }

  def createCompletedFuture[T](value : T) = Promise[T]().success(value).future

  private def createPartnerDimensionDefintion(partners : Set[Int], includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.partner, new DimensionEqualityConstraint[Int](partners), includeInResult)
  }

  private def createEntryDimensionDefintion(entries : Set[String], includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.entry, new DimensionEqualityConstraint[String](entries), includeInResult)
  }

  private def createHourDimensionDefintion(includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.entry, new DimensionUnconstrained, includeInResult)
  }


  def configureStub(partners : List[Int], entries : List[String], metrics : List[String], years : List[Int], start : DateTime, end : DateTime, valueToReturn : List[HourlyAggPrtnEntryClstAppRow]) = {
    (tableAccessorStub.query(_:List[Int], _:List[String], _:List[String], _:List[Int], _:DateTime, _:DateTime)).
      when(partners, entries, metrics, years, start, end).
      returns(createCompletedFuture(List(HourlyAggPrtnEntryClstAppRow(1,"1","play",1970, new DateTime(1),"app",5))))
  }
}


