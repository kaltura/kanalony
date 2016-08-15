package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.{Browser, OperatingSystem}
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class MinutelyAggPrtnOsBrowserQuery(accessor : IMinutelyAggPrtnOsBrowserTableAccessor) extends QueryBase[MinutelyAggPrtnOsBrowserQueryParams, MinutelyAggPrtnOsBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnOsBrowserQueryParams = {
        val (partner_id,operating_system,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.operatingSystem,Dimensions.browser), params)
        MinutelyAggPrtnOsBrowserQueryParams(params.startUtc, params.endUtc, partner_id,operating_system,browser, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnOsBrowserQueryParams): Future[List[MinutelyAggPrtnOsBrowserRow]] = {
        accessor.query(params.partnerIdList,params.operatingSystemList,params.browserList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnOsBrowserRow): List[String] = {
        List(row.partnerId.toString,OperatingSystem(row.operatingSystem).toString,Browser(row.browser).toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnOsBrowserRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnOsBrowserRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnOsBrowserRow = {
        MinutelyAggPrtnOsBrowserRow(row.partnerId, row.operatingSystem, row.browser, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class MinutelyAggPrtnOsBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams