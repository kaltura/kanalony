package unit

import com.kaltura.model.entities.Metrics
import kanalony.storage.generated.{TensecsAggPrtnOsRow, ITensecsAggPrtnOsTableAccessor }
import kanalony.storage.logic.generated.{TensecsAggPrtnOsQuery}
import kanalony.storage.logic.queries.model.{DimensionEqualityConstraint, DimensionUnconstrained, QueryDimensionDefinition}
import kanalony.storage.logic.{Dimensions, QueryParams, QueryResult}
import org.joda.time.{DateTimeZone, LocalDateTime, DateTime}
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.Promise

/**
 * Created by elad.benedict on 4/5/2016.
 */

class TensecsAggPrtnOsQueryTests extends FunSpec with MockFactory with BeforeAndAfterEach with ScalaFutures with Matchers with Inspectors {

  var query : TensecsAggPrtnOsQuery = null
  var tableAccessorStub : ITensecsAggPrtnOsTableAccessor = null

  implicit val defaultPatience =
    PatienceConfig(timeout =  Span(600, Seconds), interval = Span(15, Millis))


  override def beforeEach = {
    tableAccessorStub = stub[ITensecsAggPrtnOsTableAccessor]
    query = new TensecsAggPrtnOsQuery(tableAccessorStub)
  }

  describe("TensecsAggPrtnOsTests tests") {
    it("Should throw when query params don't have values for all partition key columns")({
      val params = QueryParams(List(QueryDimensionDefinition(
        Dimensions.partner,
        new DimensionEqualityConstraint[Int](Set(1, 2)), true)),
        List(Metrics.play),
        new LocalDateTime(1, DateTimeZone.UTC),
        new LocalDateTime(1000, DateTimeZone.UTC))

      whenReady(query.query(params).failed){ e =>
        e shouldBe a [IllegalArgumentException]
      }
    })

    it("Should query table with the correct parameters")({
      configureStub(List(1,2),List(1,2),List("play"),List(19700101),new DateTime(1, DateTimeZone.UTC), new DateTime(1000, DateTimeZone.UTC),
        List(TensecsAggPrtnOsRow(1,1,"play",19700101, new DateTime(1, DateTimeZone.UTC),5)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1, 2)),
        createOsDimensionDefintion(Set(1, 2))),
        List(Metrics.play),
        new LocalDateTime(1, DateTimeZone.UTC),
        new LocalDateTime(1000, DateTimeZone.UTC))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner","operatingSystem","play"),List(List("1","1","5.0"))))) }
    })

    it("Should aggregate correctly when there are no group by columns (i.e. cross-partner)")({
      configureStub(List(1,2),List(1,2),List("play"),List(19700101),new DateTime(1, DateTimeZone.UTC), new DateTime(1000, DateTimeZone.UTC),
        List(TensecsAggPrtnOsRow(1,1,"play",19700101, new DateTime(1, DateTimeZone.UTC),5),
             TensecsAggPrtnOsRow(2,1,"play",19700101, new DateTime(1, DateTimeZone.UTC),1)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1, 2), false),
        createOsDimensionDefintion(Set(1, 2), false)),
        List(Metrics.play),
        new LocalDateTime(1, DateTimeZone.UTC),
        new LocalDateTime(1000, DateTimeZone.UTC))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("play"),List(List("6.0"))))) }
    })

    it("Should support well known metric")({
        assert(query.isMetricSupported(Metrics.playerImpression))
    })

    it("Should support user defined metric")({
      assert(query.isMetricSupported(Metrics.get("SomeUserDefinedMetric")))
    })

    it("Should aggregate correctly over several metrics")({
      configureStub(List(1),List(1),List("play","playerImpression"),List(19700101),new DateTime(1, DateTimeZone.UTC), new DateTime(1000, DateTimeZone.UTC),
        List(TensecsAggPrtnOsRow(1,1,"play",19700101, new DateTime(1, DateTimeZone.UTC).plusMinutes(5),1),
          TensecsAggPrtnOsRow(1,1,"play",19700101, new DateTime(1, DateTimeZone.UTC).plusMinutes(5),2),
          TensecsAggPrtnOsRow(1,1,"playerImpression",19700101, new DateTime(1, DateTimeZone.UTC),3),
          TensecsAggPrtnOsRow(1,1,"playerImpression",19700101, new DateTime(1, DateTimeZone.UTC).plusHours(1),4)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1)),
        createOsDimensionDefintion(Set(1))),
        List(Metrics.play, Metrics.playerImpression),
        new LocalDateTime(1, DateTimeZone.UTC),
        new LocalDateTime(1000, DateTimeZone.UTC))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner", "operatingSystem", "play"),List(List("1", "1", "3.0"))), QueryResult(List("partner", "operatingSystem", "playerImpression"),List(List("1", "1", "7.0"))))) }
    })

    it("Should aggregate correctly over max-logic metric")({
      configureStub(List(1),List(1),List("peakView"),List(19700101),new DateTime(1, DateTimeZone.UTC), new DateTime(1000, DateTimeZone.UTC),
        List(TensecsAggPrtnOsRow(1,1,"peakView",19700101, new DateTime(1, DateTimeZone.UTC).plusMinutes(1),1),
          TensecsAggPrtnOsRow(1,1,"peakView",19700101, new DateTime(1, DateTimeZone.UTC).plusMinutes(2),200),
          TensecsAggPrtnOsRow(1,1,"peakView",19700101, new DateTime(1, DateTimeZone.UTC).plusMinutes(3),3),
          TensecsAggPrtnOsRow(1,1,"peakView",19700101, new DateTime(1, DateTimeZone.UTC).plusHours(4),4)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1)),
        createOsDimensionDefintion(Set(1))),
        List(Metrics.peakView),
        new LocalDateTime(1, DateTimeZone.UTC),
        new LocalDateTime(1000, DateTimeZone.UTC))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner", "operatingSystem", "peakView"),List(List("1", "1", "200.0"))))) }
    })


    it("Should query table with the correct parameters when time spans more than a single day")({

      val start = new DateTime(2015,12,31,1,1, DateTimeZone.UTC)
      val end = new DateTime(2016,1,1,1,1, DateTimeZone.UTC)

      configureStub(List(1,2),List(1,2),List("play"),List(20151231,20160101),start, end,
        List(TensecsAggPrtnOsRow(1,1,"play",20151231, start.plusHours(3), 5),
          TensecsAggPrtnOsRow(1,2,"play",20160101, end.minusHours(1), 3)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1, 2)),
        createOsDimensionDefintion(Set(1, 2))),
        List(Metrics.play),
        start.toLocalDateTime,
        end.toLocalDateTime)

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner","operatingSystem","play"),List(List("1","2","3.0"),List("1","1","5.0"))))) }
    })

    it("Should aggregate over columns in partition key and clustering key")({

      val start = new DateTime(2015,12,31,1,1, DateTimeZone.UTC)
      val end = new DateTime(2016,1,1,1,1, DateTimeZone.UTC)

      configureStub(List(1),List(1,2),List("play"),List(20151231,20160101),start, end,
        List(TensecsAggPrtnOsRow(1,1,"play",20151231, start.plusHours(3),5),
          TensecsAggPrtnOsRow(1,2,"play",20160101, end.minusHours(1),3)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1)),
        createOsDimensionDefintion(Set(1, 2), false)),
        List(Metrics.play),
        start.toLocalDateTime,
        end.toLocalDateTime)

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner","play"),List(List("1","8.0"))))) }
    })

    it("Should return a correct response when there are no relevant rows in the DB")({
      (tableAccessorStub.query(_:List[Int], _:List[Int], _:List[String], _:List[Int], _:DateTime, _:DateTime)).
        when(*, *, *, *, *, *).
        returns(createCompletedFuture(List()))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1, 2)),
        createOsDimensionDefintion(Set(1, 2))),
        List(Metrics.play),
        new LocalDateTime(1),
        new LocalDateTime(1000))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner","operatingSystem","play"),List()))) }
    })

  }

  def createCompletedFuture[T](value : T) = Promise[T]().success(value).future

  private def createPartnerDimensionDefintion(partners : Set[Int], includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.partner, new DimensionEqualityConstraint[Int](partners), includeInResult)
  }

  private def createOsDimensionDefintion(oss : Set[Int], includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.operatingSystem, new DimensionEqualityConstraint[Int](oss), includeInResult)
  }

  private def createHourDimensionDefintion(includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.hour, new DimensionUnconstrained, includeInResult)
  }

  def configureStub(partners : List[Int], oss : List[Int], metrics : List[String], days : List[Int], start : DateTime, end : DateTime, valueToReturn : List[TensecsAggPrtnOsRow]) = {
    (tableAccessorStub.query(_:List[Int], _:List[Int], _:List[String], _:List[Int], _:DateTime, _:DateTime)).
      when(partners, oss, metrics, days, start, end).
      returns(createCompletedFuture(valueToReturn))
  }

}


