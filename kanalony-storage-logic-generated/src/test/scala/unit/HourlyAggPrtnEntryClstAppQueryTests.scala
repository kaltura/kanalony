package kanalony.storage.test.logic.queries

import com.kaltura.model.entities.Metrics
import kanalony.storage.generated.{HourlyAggPrtnEntryClstAppRow, IHourlyAggPrtnEntryClstAppTableAccessor}
import kanalony.storage.logic.queries.model.{DimensionUnconstrained, DimensionRangeConstraint, DimensionEqualityConstraint, QueryDimensionDefinition}
import kanalony.storage.logic.{QueryResult, Dimensions, QueryParams}
import kanalony.storage.logic.generated.HourlyAggPrtnEntryClstAppQuery
import org.joda.time.{LocalDateTime, DateTime}
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
        new LocalDateTime(1),
        new LocalDateTime(1000))

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
        new LocalDateTime(1),
        new LocalDateTime(1000))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner","entry","play"),List(List("1","1","5.0"))))) }
    })

    it("Should aggregate correctly when there are no group by columns (i.e. cross-partner)")({
      configureStub(List(1,2),List("1","2"),List("play"),List(1970),new DateTime(1), new DateTime(1000),
        List(HourlyAggPrtnEntryClstAppRow(1,"1","play",1970, new DateTime(1),"app",5),
            HourlyAggPrtnEntryClstAppRow(2,"1","play",1970, new DateTime(1),"app",1)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1, 2), false),
        createEntryDimensionDefintion(Set("1", "2"), false)),
        List(Metrics.play),
        new LocalDateTime(1),
        new LocalDateTime(1000))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("play"),List(List("6.0"))))) }
    })

    it("Should support well known metric")({
        assert(query.isMetricSupported(Metrics.playerImpression))
    })

    it("Should support user defined metric")({
      assert(query.isMetricSupported(Metrics.get("SomeUserDefinedMetric")))
    })

    it("Should aggregate correctly over several metrics")({
      configureStub(List(1),List("1"),List("play","playerImpression"),List(1970),new DateTime(1), new DateTime(1000),
        List(HourlyAggPrtnEntryClstAppRow(1,"1","play",1970, new DateTime(1).plusMinutes(5),"app",1),
          HourlyAggPrtnEntryClstAppRow(1,"1","play",1970, new DateTime(1).plusMinutes(5),"app",2),
          HourlyAggPrtnEntryClstAppRow(1,"1","playerImpression",1970, new DateTime(1),"app",3),
          HourlyAggPrtnEntryClstAppRow(1,"1","playerImpression",1970, new DateTime(1).plusHours(1),"app",4)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1)),
        createEntryDimensionDefintion(Set("1"))),
        List(Metrics.play, Metrics.playerImpression),
        new LocalDateTime(1),
        new LocalDateTime(1000))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner", "entry", "play"),List(List("1", "1", "3.0"))), QueryResult(List("partner", "entry", "playerImpression"),List(List("1", "1", "7.0"))))) }
    })

    it("Should aggregate correctly over max-logic metric")({
      configureStub(List(1),List("1"),List("peakView"),List(1970),new DateTime(1), new DateTime(1000),
        List(HourlyAggPrtnEntryClstAppRow(1,"1","peakView",1970, new DateTime(1).plusMinutes(1),"app",1),
          HourlyAggPrtnEntryClstAppRow(1,"1","peakView",1970, new DateTime(1).plusMinutes(2),"app",200),
          HourlyAggPrtnEntryClstAppRow(1,"1","peakView",1970, new DateTime(1).plusMinutes(3),"app",3),
          HourlyAggPrtnEntryClstAppRow(1,"1","peakView",1970, new DateTime(1).plusHours(4),"app",4)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1)),
        createEntryDimensionDefintion(Set("1"))),
        List(Metrics.peakView),
        new LocalDateTime(1),
        new LocalDateTime(1000))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner", "entry", "peakView"),List(List("1", "1", "200.0"))))) }
    })


    it("Should query table with the correct parameters when time spans more than a single year")({

      val start = new DateTime(2015,12,31,1,1)
      val end = new DateTime(2016,1,1,1,1)

      configureStub(List(1,2),List("1","2"),List("play"),List(2015,2016),start, end,
        List(HourlyAggPrtnEntryClstAppRow(1,"1","play",2015, start.toDateTime().plusHours(3) ,"app",5),
             HourlyAggPrtnEntryClstAppRow(1,"2","play",2016, end.toDateTime.minusHours(1),"app",3)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1, 2)),
        createEntryDimensionDefintion(Set("1", "2"))),
        List(Metrics.play),
        start.toLocalDateTime,
        end.toLocalDateTime)

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner","entry","play"),List(List("1","2","3.0"),List("1","1","5.0"))))) }
    })

    it("Should aggregate over columns in partition key and clustering key")({

      val start = new DateTime(2015,12,31,1,1)
      val end = new DateTime(2016,1,1,1,1)

      configureStub(List(1),List("1","2"),List("play"),List(2015,2016),start, end,
        List(HourlyAggPrtnEntryClstAppRow(1,"1","play",2015, start.plusHours(3) ,"app",5),
          HourlyAggPrtnEntryClstAppRow(1,"2","play",2016, end.minusHours(1),"app",3)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1)),
        createEntryDimensionDefintion(Set("1", "2"), false)),
        List(Metrics.play),
        start.toLocalDateTime,
        end.toLocalDateTime)

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner","play"),List(List("1","8.0"))))) }
    })

    it("Should aggregate hourly data")({

      val start = new DateTime(2015,12,31,1,1)
      val end = new DateTime(2016,1,1,1,1)

      configureStub(List(1),List("1","2"),List("play"),List(2015,2016),start, end,
        List(HourlyAggPrtnEntryClstAppRow(1,"1","play",2015, start.plusHours(3) ,"app",5),
          HourlyAggPrtnEntryClstAppRow(1,"1","play",2015, start.plusHours(3).plusMinutes(5) ,"app",5),
          HourlyAggPrtnEntryClstAppRow(1,"2","play",2016, end.minusHours(1),"app",3)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1)),
        createEntryDimensionDefintion(Set("1", "2"), false),
        createHourDimensionDefintion(true)),
        List(Metrics.play),
        start.toLocalDateTime,
        end.toLocalDateTime)

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner", "hour", "play"),List(List("1", "2016-01-01T00:01:00.000+02:00", "3.0"), List("1", "2015-12-31T04:06:00.000+02:00", "5.0"), List("1", "2015-12-31T04:01:00.000+02:00", "5.0"))))) }
    })

    it("Should return a correct response when there are no relevant rows in the DB")({
      (tableAccessorStub.query(_:List[Int], _:List[String], _:List[String], _:List[Int], _:DateTime, _:DateTime)).
        when(*, *, *, *, *, *).
        returns(createCompletedFuture(List()))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1, 2)),
        createEntryDimensionDefintion(Set("1", "2"))),
        List(Metrics.play),
        new LocalDateTime(1),
        new LocalDateTime(1000))

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner","entry","play"),List()))) }
    })

    it("Should display data per application")({
      val start = new DateTime(2015,12,31,1,1)
      val end = new DateTime(2016,1,1,1,1)

      configureStub(List(1),List("1","2"),List("play"),List(2015,2016),start, end,
        List(HourlyAggPrtnEntryClstAppRow(1,"1","play",2015, start,"app1",5),
          HourlyAggPrtnEntryClstAppRow(1,"1","play",2015, start,"app2",5),
          HourlyAggPrtnEntryClstAppRow(1,"1","play",2016, end,"app3",3)))

      val params = QueryParams(List(
        createPartnerDimensionDefintion(Set(1)),
        createEntryDimensionDefintion(Set("1", "2"), false),
        createAppDimensionDefintion(true)),
        List(Metrics.play),
        start.toLocalDateTime,
        end.toLocalDateTime)

      whenReady(query.query(params)){ res => assert(res == List(QueryResult(List("partner", "application", "play"),List(List("1", "app2", "5.0"), List("1", "app1", "5.0"), List("1", "app3", "3.0"))))) }
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
    QueryDimensionDefinition(Dimensions.hour, new DimensionUnconstrained, includeInResult)
  }

  private def createAppDimensionDefintion(includeInResult : Boolean = true) = {
    QueryDimensionDefinition(Dimensions.application, new DimensionUnconstrained, includeInResult)
  }

  def configureStub(partners : List[Int], entries : List[String], metrics : List[String], years : List[Int], start : DateTime, end : DateTime, valueToReturn : List[HourlyAggPrtnEntryClstAppRow]) = {
    (tableAccessorStub.query(_:List[Int], _:List[String], _:List[String], _:List[Int], _:DateTime, _:DateTime)).
      when(partners, entries, metrics, years, start, end).
      returns(createCompletedFuture(valueToReturn))
  }

}


