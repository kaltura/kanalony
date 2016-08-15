package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.Device
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class MinutelyAggPrtnDeviceClstEntryQuery(accessor : IMinutelyAggPrtnDeviceClstEntryTableAccessor) extends QueryBase[MinutelyAggPrtnDeviceClstEntryQueryParams, MinutelyAggPrtnDeviceClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnDeviceClstEntryQueryParams = {
        val (partner_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.device), params)
        MinutelyAggPrtnDeviceClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,device, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnDeviceClstEntryQueryParams): Future[List[MinutelyAggPrtnDeviceClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.deviceList,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnDeviceClstEntryRow): List[String] = {
        List(row.partnerId.toString,Device(row.device).toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnDeviceClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnDeviceClstEntryRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnDeviceClstEntryRow = {
        MinutelyAggPrtnDeviceClstEntryRow(row.partnerId, row.device, row.day, row.metric, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.entryId, row.value)
      }

    }

case class MinutelyAggPrtnDeviceClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams