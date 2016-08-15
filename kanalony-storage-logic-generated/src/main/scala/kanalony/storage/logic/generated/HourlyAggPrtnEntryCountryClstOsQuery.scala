package kanalony.storage.logic.generated

import com.kaltura.core.userAgent.enums.OperatingSystem
import kanalony.storage.generated._
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.Future

    class HourlyAggPrtnEntryCountryClstOsQuery(accessor : IHourlyAggPrtnEntryCountryClstOsTableAccessor) extends QueryBase[HourlyAggPrtnEntryCountryClstOsQueryParams, HourlyAggPrtnEntryCountryClstOsRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryCountryClstOsQueryParams = {
        val (partner_id,entry_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.country), params)
        HourlyAggPrtnEntryCountryClstOsQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,country, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryCountryClstOsQueryParams): Future[List[HourlyAggPrtnEntryCountryClstOsRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.countryList,params.years,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.operatingSystem.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryCountryClstOsRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.country.toString,row.metric.toString,row.hour.toString,OperatingSystem(row.operatingSystem).toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryCountryClstOsRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnEntryCountryClstOsRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnEntryCountryClstOsRow = {
        HourlyAggPrtnEntryCountryClstOsRow(row.partnerId, row.entryId, row.country, row.year, row.metric, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.operatingSystem, row.value)
      }

    }

case class HourlyAggPrtnEntryCountryClstOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams