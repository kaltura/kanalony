package kanalony.storage.logic.queries

import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.DateTime

import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/11/2016.
 */

class HourlyUserActivityPrtnEntryClstCountryQuery extends QueryBase[HourlyUserActivityPrtnEntryClstCountryParams, hourly_ua_prtn_entry_clst_countryRow] with UserActivityQuery {
  private[logic] override def extractParams(params: QueryParams): HourlyUserActivityPrtnEntryClstCountryParams = {
    val (partnerIds, entryIds) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner, Dimensions.entry), params)
    HourlyUserActivityPrtnEntryClstCountryParams(params.start, params.end, partnerIds, entryIds, List(params.metric.id))
  }

  private[logic] override def executeQuery(params: HourlyUserActivityPrtnEntryClstCountryParams): Future[List[hourly_ua_prtn_entry_clst_countryRow]] = {
    val rawQueryResult = dbApi.H_UA_PartnerEntry_Country_StorageClient.query(params.partnerId,
                              params.entryId,params.metric,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
  }

  override private[logic] def getResultHeaders(): List[String] =  {
    List(Dimensions.partner.toString, Dimensions.entry.toString, Dimensions.metric.toString, Dimensions.hour.toString, Dimensions.country.toString, metricValueHeaderName)
  }

  override protected def getResultRow(row: hourly_ua_prtn_entry_clst_countryRow): List[String] = {
    List(row.partner_id.toString, row.entry_id, row.metric.toString, row.hour.getHourOfDay.toString, row.country.toString, row.value.toString)
  }

  override val dimensionInformation: List[DimensionDefinition] = {
    List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
      DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
      DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
      DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Unconstrained))
    )
  }

  override val tableName: String = dbApi.H_UA_PartnerEntry_Country_StorageClient.tableName

  override def metricValueLocationIndex(): Int = 5
}

case class HourlyUserActivityPrtnEntryClstCountryParams(startTime : DateTime, endTime : DateTime, partnerId : List[Int], entryId : List[String], metric : List[Int]) extends IYearlyPartitionedQueryParams
