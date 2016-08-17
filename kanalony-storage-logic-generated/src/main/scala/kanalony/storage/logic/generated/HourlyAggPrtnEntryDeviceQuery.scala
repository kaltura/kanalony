package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.Device
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class HourlyAggPrtnEntryDeviceQuery(accessor : IHourlyAggPrtnEntryDeviceTableAccessor) extends QueryBase[HourlyAggPrtnEntryDeviceQueryParams, HourlyAggPrtnEntryDeviceRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryDeviceQueryParams = {
        val (partner_id,entry_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.entry,Dimensions.device), params)
        HourlyAggPrtnEntryDeviceQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,device, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryDeviceQueryParams): Future[List[HourlyAggPrtnEntryDeviceRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.deviceList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryDeviceRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,Device(row.device).toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryDeviceRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnEntryDeviceRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnEntryDeviceRow = {
        HourlyAggPrtnEntryDeviceRow(row.partnerId, row.entryId, row.device, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnEntryDeviceQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams
