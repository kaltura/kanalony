package kanalony.storage.logic.queries

import kanalony.storage.generated.hourly_user_activity_prtn_entry_clst_countryRow
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.DateTime

import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/11/2016.
 */

class HourlyUserActivityPrtnEntryClstCountryQuery extends QueryBase[HourlyUserActivityPrtnEntryClstCountryParams, hourly_user_activity_prtn_entry_clst_countryRow] with UserActivityQuery {
  protected override def extractParams(params: QueryParams): HourlyUserActivityPrtnEntryClstCountryParams = {
    val (partnerIds, entryIds) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner, Dimensions.entry), params)
    HourlyUserActivityPrtnEntryClstCountryParams(params.start, params.end, partnerIds, entryIds, List(params.metric.id))
  }

  private[logic] override def executeQuery(params: HourlyUserActivityPrtnEntryClstCountryParams): Future[List[hourly_user_activity_prtn_entry_clst_countryRow]] = {
    val year = List(params.startTime.getYear)
    val rawQueryResult = dbApi.H_UA_PartnerEntry_Country_StorageClient.query(params.partnerId,
                              params.entryId,params.metric,year,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
  }

  override protected def getResultHeaders(): List[String] =  {
    List(Dimensions.partner.toString, Dimensions.entry.toString, "metric", Dimensions.hour.toString, Dimensions.country.toString, "count")
  }

  override protected def getResultRow(row: hourly_user_activity_prtn_entry_clst_countryRow): List[String] = {
    List(row.partner_id.toString, row.entry_id, row.event_type.toString, row.hour.getHourOfDay.toString, row.country.toString, row.count.toString)
  }

  override val dimensionInformation: List[DimensionDefinition] = {
    List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
      DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
      DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
      DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Unconstrained))
    )
  }

  override val tableName: String = dbApi.H_UA_PartnerEntry_Country_StorageClient.tableName
}

case class HourlyUserActivityPrtnEntryClstCountryParams(startTime : DateTime, endTime : DateTime, partnerId : List[Int], entryId : List[String], metric : List[Int])
