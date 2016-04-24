package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnCountryClstOsQuery(accessor : IHourlyAggPrtnCountryClstOsTableAccessor) extends QueryBase[HourlyAggPrtnCountryClstOsQueryParams, HourlyAggPrtnCountryClstOsRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnCountryClstOsQueryParams = {
        val (partner_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.country), params)
        HourlyAggPrtnCountryClstOsQueryParams(params.startUtc, params.endUtc, partner_id,country, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnCountryClstOsQueryParams): Future[List[HourlyAggPrtnCountryClstOsRow]] = {
        accessor.query(params.partnerIdList,params.countryList,params.years,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.operatingSystem.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnCountryClstOsRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.metric.toString,row.hour.toString,row.operatingSystem.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnCountryClstOsRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnCountryClstOsRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnCountryClstOsRow = {
        HourlyAggPrtnCountryClstOsRow(row.partnerId, row.country, row.year, row.metric, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.operatingSystem, row.value)
      }

    }

case class HourlyAggPrtnCountryClstOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams