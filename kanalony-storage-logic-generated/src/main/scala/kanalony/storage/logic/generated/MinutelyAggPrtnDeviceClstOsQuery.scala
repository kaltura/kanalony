package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.{Device, OperatingSystem}
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class MinutelyAggPrtnDeviceClstOsQuery(accessor : IMinutelyAggPrtnDeviceClstOsTableAccessor) extends QueryBase[MinutelyAggPrtnDeviceClstOsQueryParams, MinutelyAggPrtnDeviceClstOsRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnDeviceClstOsQueryParams = {
        val (partner_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.device), params)
        MinutelyAggPrtnDeviceClstOsQueryParams(params.startUtc, params.endUtc, partner_id,device, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnDeviceClstOsQueryParams): Future[List[MinutelyAggPrtnDeviceClstOsRow]] = {
        accessor.query(params.partnerIdList,params.deviceList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.operatingSystem.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnDeviceClstOsRow): List[String] = {
        List(row.partnerId.toString,Device(row.device).toString,row.metric.toString,row.minute.toString,OperatingSystem(row.operatingSystem).toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnDeviceClstOsRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnDeviceClstOsRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnDeviceClstOsRow = {
        MinutelyAggPrtnDeviceClstOsRow(row.partnerId, row.device, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.operatingSystem, row.value)
      }

    }

case class MinutelyAggPrtnDeviceClstOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams