package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.DailyMaxQuery
import kanalony.storage.test.logic.queries.DailyQueryTestsBase
import org.joda.time.DateTime

/**
  * Created by elad.benedict on 4/7/2016.
  */
class DailyMaxQueryTests extends DailyQueryTestsBase[DailyMaxQuery] {
  it("Should aggregate data by day")({

    val metricQueryStub = stub[IQuery]
    (metricQueryStub.query(_)).
      when(*).
      returns(createCompletedFuture(List(QueryResult(List("partner", "entry", "hour", "peakView"),
        List(List("1","1", new DateTime(2016,1,1,1,1).toString(),"3"),
          List("1","1", new DateTime(2016,1,1,1,2).toString(),"4"),
          List("1","1", new DateTime(2016,1,2,1,2).toString(),"5"))))))

    val params = QueryParams(List(createPartnerDimensionDefintion(Set(1)), createEntryDimensionDefintion(Set("1")), createDayDimensionDefintion()), List(Metrics.peakView), new DateTime(1), new DateTime(2))
    val expectedParams = QueryParams(List(createPartnerDimensionDefintion(Set(1)), createEntryDimensionDefintion(Set("1")), createHourDimensionDefintion()), List(Metrics.peakView), new DateTime(1), new DateTime(2))
    configureStub(expectedParams, List((metricQueryStub, List(Metrics.peakView)))) // The response is meaningless for this test - just use some IQuery
    val dailyQuery = dailyQueryCreatorWithStub(params)
    whenReady(dailyQuery.query(params)){
      res => assert(res == List(QueryResult(List("partner","entry","day","peakView"),List(List("1","1",new DateTime(2016,1,2,1,1).toLocalDate.toString(),"5.0"), List("1","1",new DateTime(2016,1,1,1,1).toLocalDate.toString(),"4.0")))))
    }
  })

  override def createDailyQuery(queryParams: QueryParams, queryLocator: IQueryLocator): DailyMaxQuery = {
    new DailyMaxQuery(queryParams, queryLocator)
  }
}
