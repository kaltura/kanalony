package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggPrtnCountryClstOsBrowserQuery(accessor : ITensecsAggPrtnCountryClstOsBrowserTableAccessor) extends QueryBase[TensecsAggPrtnCountryClstOsBrowserQueryParams, TensecsAggPrtnCountryClstOsBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnCountryClstOsBrowserQueryParams = {
        val (partner_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.country), params)
        TensecsAggPrtnCountryClstOsBrowserQueryParams(params.startUtc, params.endUtc, partner_id,country, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnCountryClstOsBrowserQueryParams): Future[List[TensecsAggPrtnCountryClstOsBrowserRow]] = {
        accessor.query(params.partnerIdList,params.countryList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnCountryClstOsBrowserRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.metric.toString,row.tensecs.toString,row.operatingSystem.toString,row.browser.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: TensecsAggPrtnCountryClstOsBrowserRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggPrtnCountryClstOsBrowserRow, timezoneOffsetFromUtc : Int) : TensecsAggPrtnCountryClstOsBrowserRow = {
        TensecsAggPrtnCountryClstOsBrowserRow(row.partnerId, row.country, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.operatingSystem, row.browser, row.value)
      }

    }

case class TensecsAggPrtnCountryClstOsBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams