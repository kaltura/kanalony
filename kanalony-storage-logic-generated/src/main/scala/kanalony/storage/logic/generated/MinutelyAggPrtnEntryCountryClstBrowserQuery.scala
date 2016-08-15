package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.Browser
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class MinutelyAggPrtnEntryCountryClstBrowserQuery(accessor : IMinutelyAggPrtnEntryCountryClstBrowserTableAccessor) extends QueryBase[MinutelyAggPrtnEntryCountryClstBrowserQueryParams, MinutelyAggPrtnEntryCountryClstBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnEntryCountryClstBrowserQueryParams = {
        val (partner_id,entry_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.country), params)
        MinutelyAggPrtnEntryCountryClstBrowserQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,country, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnEntryCountryClstBrowserQueryParams): Future[List[MinutelyAggPrtnEntryCountryClstBrowserRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.countryList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.browser.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnEntryCountryClstBrowserRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.country.toString,row.metric.toString,row.minute.toString,Browser(row.browser).toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyAggPrtnEntryCountryClstBrowserRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnEntryCountryClstBrowserRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnEntryCountryClstBrowserRow = {
        MinutelyAggPrtnEntryCountryClstBrowserRow(row.partnerId, row.entryId, row.country, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.browser, row.value)
      }

    }

case class MinutelyAggPrtnEntryCountryClstBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams