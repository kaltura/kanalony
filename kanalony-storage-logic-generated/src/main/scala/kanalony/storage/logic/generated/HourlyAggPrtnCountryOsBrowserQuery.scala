package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnCountryOsBrowserQuery(accessor : IHourlyAggPrtnCountryOsBrowserTableAccessor) extends QueryBase[HourlyAggPrtnCountryOsBrowserQueryParams, HourlyAggPrtnCountryOsBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnCountryOsBrowserQueryParams = {
        val (partner_id,country,operating_system,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int,Int]((Dimensions.partner,Dimensions.country,Dimensions.operatingSystem,Dimensions.browser), params)
        HourlyAggPrtnCountryOsBrowserQueryParams(params.startUtc, params.endUtc, partner_id,country,operating_system,browser, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnCountryOsBrowserQueryParams): Future[List[HourlyAggPrtnCountryOsBrowserRow]] = {
        accessor.query(params.partnerIdList,params.countryList,params.operatingSystemList,params.browserList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnCountryOsBrowserRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.operatingSystem.toString,row.browser.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyAggPrtnCountryOsBrowserRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnCountryOsBrowserRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnCountryOsBrowserRow = {
        HourlyAggPrtnCountryOsBrowserRow(row.partnerId, row.country, row.operatingSystem, row.browser, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnCountryOsBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams