package kanalony.storage.logic.queries

import kanalony.storage.generated.{hourly_ua_prtn_entryRow}
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time._
import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/16/2016.
 */

class HourlyUserActivityPrtnEntryQuery extends QueryBase[HourlyUserActivityPrtnEntryParams, hourly_ua_prtn_entryRow] with UserActivityQuery {
  override private[logic] def extractParams(queryParams: QueryParams): HourlyUserActivityPrtnEntryParams = {
    val (partnerIds, entryIds) = QueryParamsValidator.extractEqualityConstraintParams((Dimensions.partner, Dimensions.entry), queryParams)
    HourlyUserActivityPrtnEntryParams(queryParams.start, queryParams.end, partnerIds, entryIds, queryParams.metrics.map(_.id))
  }

  override private[logic] def executeQuery(params: HourlyUserActivityPrtnEntryParams): Future[List[hourly_ua_prtn_entryRow]] = {
    val rawQueryResult = dbApi.H_UA_PartnerEntry_StorageClient.query(params.partnerIds,
      params.entryIds,params.metrics,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
  }

  override private[logic] def getResultHeaders(): List[String] =  {
    List(Dimensions.partner.toString, Dimensions.entry.toString, Dimensions.metric.toString, Dimensions.hour.toString, metricValueHeaderName)
  }

  override protected def getResultRow(row: hourly_ua_prtn_entryRow) : List[String] = {
    List(row.partner_id.toString, row.entry_id, row.metric.toString, row.hour.getHourOfDay.toString, row.value.toString)
  }

  override val dimensionInformation: List[DimensionDefinition] = {
    List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
      DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
      DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range))
    )
  }

  override def metricValueLocationIndex(): Int = 4

  override private[logic] def extractMetric(row: hourly_ua_prtn_entryRow): Int = row.metric
}

case class HourlyUserActivityPrtnEntryParams(startTime : DateTime, endTime : DateTime, partnerIds : List[Int], entryIds : List[String], metrics : List[Int]) extends IYearlyPartitionedQueryParams

