package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.{Device, OperatingSystem}
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class MinutelyAggPrtnDeviceOsQuery(accessor : IMinutelyAggPrtnDeviceOsTableAccessor) extends QueryBase[MinutelyAggPrtnDeviceOsQueryParams, MinutelyAggPrtnDeviceOsRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnDeviceOsQueryParams = {
        val (partner_id,device,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.device,Dimensions.operatingSystem), params)
        MinutelyAggPrtnDeviceOsQueryParams(params.startUtc, params.endUtc, partner_id,device,operating_system, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnDeviceOsQueryParams): Future[List[MinutelyAggPrtnDeviceOsRow]] = {
        accessor.query(params.partnerIdList,params.deviceList,params.operatingSystemList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnDeviceOsRow): List[String] = {
        List(row.partnerId.toString,Device(row.device).toString,OperatingSystem(row.operatingSystem).toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnDeviceOsRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnDeviceOsRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnDeviceOsRow = {
        MinutelyAggPrtnDeviceOsRow(row.partnerId, row.device, row.operatingSystem, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class MinutelyAggPrtnDeviceOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams