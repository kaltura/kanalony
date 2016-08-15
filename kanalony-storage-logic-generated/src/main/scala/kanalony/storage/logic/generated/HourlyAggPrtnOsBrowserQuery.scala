package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.{Browser, OperatingSystem}
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class HourlyAggPrtnOsBrowserQuery(accessor : IHourlyAggPrtnOsBrowserTableAccessor) extends QueryBase[HourlyAggPrtnOsBrowserQueryParams, HourlyAggPrtnOsBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnOsBrowserQueryParams = {
        val (partner_id,operating_system,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.operatingSystem,Dimensions.browser), params)
        HourlyAggPrtnOsBrowserQueryParams(params.startUtc, params.endUtc, partner_id,operating_system,browser, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnOsBrowserQueryParams): Future[List[HourlyAggPrtnOsBrowserRow]] = {
        accessor.query(params.partnerIdList,params.operatingSystemList,params.browserList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnOsBrowserRow): List[String] = {
        List(row.partnerId.toString,OperatingSystem(row.operatingSystem).toString,Browser(row.browser).toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnOsBrowserRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnOsBrowserRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnOsBrowserRow = {
        HourlyAggPrtnOsBrowserRow(row.partnerId, row.operatingSystem, row.browser, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnOsBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams