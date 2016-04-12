package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsAggPrtnCountryOsClstBrowserQuery(accessor : ITensecsAggPrtnCountryOsClstBrowserTableAccessor) extends QueryBase[TensecsAggPrtnCountryOsClstBrowserQueryParams, TensecsAggPrtnCountryOsClstBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnCountryOsClstBrowserQueryParams = {
        val (partner_id,country,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.country,Dimensions.operatingSystem), params)
        TensecsAggPrtnCountryOsClstBrowserQueryParams(params.startUtc, params.endUtc, partner_id,country,operating_system, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnCountryOsClstBrowserQueryParams): Future[List[TensecsAggPrtnCountryOsClstBrowserRow]] = {
        accessor.query(params.partnerIdList,params.countryList,params.operatingSystemList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.browser.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnCountryOsClstBrowserRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.operatingSystem.toString,row.metric.toString,row.tensecs.toString,row.browser.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: TensecsAggPrtnCountryOsClstBrowserRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggPrtnCountryOsClstBrowserRow, timezoneOffsetFromUtc : Int) : TensecsAggPrtnCountryOsClstBrowserRow = {
        TensecsAggPrtnCountryOsClstBrowserRow(row.partnerId, row.country, row.operatingSystem, row.metric, row.day, row.tensecs.plusHours(timezoneOffsetFromUtc), row.browser, row.value)
      }

    }

case class TensecsAggPrtnCountryOsClstBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams