package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.{Device, OperatingSystem}
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class HourlyAggPrtnEntryDeviceClstOsQuery(accessor : IHourlyAggPrtnEntryDeviceClstOsTableAccessor) extends QueryBase[HourlyAggPrtnEntryDeviceClstOsQueryParams, HourlyAggPrtnEntryDeviceClstOsRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryDeviceClstOsQueryParams = {
        val (partner_id,entry_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.entry,Dimensions.device), params)
        HourlyAggPrtnEntryDeviceClstOsQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,device, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryDeviceClstOsQueryParams): Future[List[HourlyAggPrtnEntryDeviceClstOsRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.deviceList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.operatingSystem.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryDeviceClstOsRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,Device(row.device).toString,row.metric.toString,row.hour.toString,OperatingSystem(row.operatingSystem).toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryDeviceClstOsRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnEntryDeviceClstOsRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnEntryDeviceClstOsRow = {
        HourlyAggPrtnEntryDeviceClstOsRow(row.partnerId, row.entryId, row.device, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.operatingSystem, row.value)
      }

    }

case class HourlyAggPrtnEntryDeviceClstOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams