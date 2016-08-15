package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.OperatingSystem
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class HourlyAggPrtnOsQuery(accessor : IHourlyAggPrtnOsTableAccessor) extends QueryBase[HourlyAggPrtnOsQueryParams, HourlyAggPrtnOsRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnOsQueryParams = {
        val (partner_id,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.operatingSystem), params)
        HourlyAggPrtnOsQueryParams(params.startUtc, params.endUtc, partner_id,operating_system, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnOsQueryParams): Future[List[HourlyAggPrtnOsRow]] = {
        accessor.query(params.partnerIdList,params.operatingSystemList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnOsRow): List[String] = {
        List(row.partnerId.toString,OperatingSystem(row.operatingSystem).toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggPrtnOsRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnOsRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnOsRow = {
        HourlyAggPrtnOsRow(row.partnerId, row.operatingSystem, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams