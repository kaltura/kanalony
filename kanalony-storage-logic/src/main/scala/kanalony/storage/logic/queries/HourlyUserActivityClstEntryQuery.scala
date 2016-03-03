package kanalony.storage.logic.queries

import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.DateTime

import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/16/2016.
 */

class HourlyUserActivityClstEntryQuery extends QueryBase[HourlyUserActivityClstEntryParams, hourly_ua_clst_entryRow] with UserActivityQuery {
  override private[logic] def extractParams(queryParams: QueryParams): HourlyUserActivityClstEntryParams = {
    val partnerIds = QueryParamsValidator.extractEqualityConstraintParams(Dimensions.partner, queryParams)
    HourlyUserActivityClstEntryParams(queryParams.start, queryParams.end, partnerIds, queryParams.metrics.map(_.id))
  }

  override private[logic] def executeQuery(params: HourlyUserActivityClstEntryParams): Future[List[hourly_ua_clst_entryRow]] = {
    val rawQueryResult = dbApi.H_UA_Partner_Entry_StorageClient.query(params.partnerIds,
      params.metrics,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
  }

  override private[logic] def getResultHeaders(): List[String] =  {
    List(Dimensions.partner.toString, Dimensions.entry.toString, Dimensions.metric.toString, Dimensions.hour.toString, metricValueHeaderName)
  }

  override protected def getResultRow(row: hourly_ua_clst_entryRow) : List[String] = {
    List(row.partner_id.toString, row.entry_id, row.metric.toString, row.hour.getHourOfDay.toString, row.value.toString)
  }

  override val dimensionInformation: List[DimensionDefinition] = {
    List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
      DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)),
      DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range))
    )
  }

  override def metricValueLocationIndex(): Int = 4

  override private[logic] def extractMetric(row: hourly_ua_clst_entryRow): Int = row.metric
}

case class HourlyUserActivityClstEntryParams(startTime : DateTime, endTime : DateTime, partnerIds : List[Int], metrics : List[Int]) extends IYearlyPartitionedQueryParams



