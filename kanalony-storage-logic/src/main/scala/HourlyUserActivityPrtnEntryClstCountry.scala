package kanalony.storage.logic

import kanalony.storage.generated.hourly_user_activity_prtn_entry_clst_countryRow
import scala.concurrent.ExecutionContext.Implicits.global
import org.joda.time.DateTime
import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/11/2016.
 */

class HourlyUserActivityPrtnEntryClstCountry extends QueryBase[HourlyUserActivityPrtnEntryClstCountryParams] with HourlyUserActivityQuery {
  protected override def typifyParams(params: Map[String, Any]): HourlyUserActivityPrtnEntryClstCountryParams = {
    val typedParams = QueryParamsValidator.validate[DateTime,DateTime,Int,String,Int](("start","end","partnerId","entryId","metric"), params)
    HourlyUserActivityPrtnEntryClstCountryParams(typedParams._1, typedParams._2, typedParams._3, typedParams._4, typedParams._5)
  }

  protected override def executeQuery(params: HourlyUserActivityPrtnEntryClstCountryParams): Future[IQueryResult] = {
    val year = params.startTime.getYear
    val rawQueryResult = dbApi.H_UA_PartnerEntry_Country_StorageClient.query(params.partnerId,
                                                                     params.entryId,params.metric,year,
                                                                     params.startTime,params.endTime).fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    // TODO - implement transformation to IQueryResult
    rawQueryResult map {rows => QueryResult(List(rows(0).entry_id), List(List(rows(0).partner_id.toString)))}
  }
}

case class HourlyUserActivityPrtnEntryClstCountryParams(startTime : DateTime, endTime : DateTime, partnerId : Int, entryId : String, metric : Int)
