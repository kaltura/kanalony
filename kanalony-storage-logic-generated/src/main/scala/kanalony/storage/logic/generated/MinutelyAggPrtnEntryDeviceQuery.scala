package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.Device
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class MinutelyAggPrtnEntryDeviceQuery(accessor : IMinutelyAggPrtnEntryDeviceTableAccessor) extends QueryBase[MinutelyAggPrtnEntryDeviceQueryParams, MinutelyAggPrtnEntryDeviceRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnEntryDeviceQueryParams = {
        val (partner_id,entry_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.entry,Dimensions.device), params)
        MinutelyAggPrtnEntryDeviceQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,device, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnEntryDeviceQueryParams): Future[List[MinutelyAggPrtnEntryDeviceRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.deviceList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnEntryDeviceRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,Device(row.device).toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnEntryDeviceRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnEntryDeviceRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnEntryDeviceRow = {
        MinutelyAggPrtnEntryDeviceRow(row.partnerId, row.entryId, row.device, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class MinutelyAggPrtnEntryDeviceQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams