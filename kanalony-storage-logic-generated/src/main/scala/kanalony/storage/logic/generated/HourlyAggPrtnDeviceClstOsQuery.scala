package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.{Device, OperatingSystem}
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class HourlyAggPrtnDeviceClstOsQuery(accessor : IHourlyAggPrtnDeviceClstOsTableAccessor) extends QueryBase[HourlyAggPrtnDeviceClstOsQueryParams, HourlyAggPrtnDeviceClstOsRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnDeviceClstOsQueryParams = {
        val (partner_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.device), params)
        HourlyAggPrtnDeviceClstOsQueryParams(params.startUtc, params.endUtc, partner_id,device, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnDeviceClstOsQueryParams): Future[List[HourlyAggPrtnDeviceClstOsRow]] = {
        accessor.query(params.partnerIdList,params.deviceList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.operatingSystem.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnDeviceClstOsRow): List[String] = {
        List(row.partnerId.toString,Device(row.device).toString,row.metric.toString,row.hour.toString,OperatingSystem(row.operatingSystem).toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnDeviceClstOsRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnDeviceClstOsRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnDeviceClstOsRow = {
        HourlyAggPrtnDeviceClstOsRow(row.partnerId, row.device, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.operatingSystem, row.value)
      }

    }

case class HourlyAggPrtnDeviceClstOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams