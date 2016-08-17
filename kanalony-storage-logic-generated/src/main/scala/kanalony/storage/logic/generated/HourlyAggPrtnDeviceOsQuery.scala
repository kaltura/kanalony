package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.{OperatingSystem, Device}
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class HourlyAggPrtnDeviceOsQuery(accessor : IHourlyAggPrtnDeviceOsTableAccessor) extends QueryBase[HourlyAggPrtnDeviceOsQueryParams, HourlyAggPrtnDeviceOsRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnDeviceOsQueryParams = {
        val (partner_id,device,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.device,Dimensions.operatingSystem), params)
        HourlyAggPrtnDeviceOsQueryParams(params.startUtc, params.endUtc, partner_id,device,operating_system, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnDeviceOsQueryParams): Future[List[HourlyAggPrtnDeviceOsRow]] = {
        accessor.query(params.partnerIdList,params.deviceList,params.operatingSystemList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnDeviceOsRow): List[String] = {
        List(row.partnerId.toString,Device(row.device).toString,OperatingSystem(row.operatingSystem).toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnDeviceOsRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnDeviceOsRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnDeviceOsRow = {
        HourlyAggPrtnDeviceOsRow(row.partnerId, row.device, row.operatingSystem, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnDeviceOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams