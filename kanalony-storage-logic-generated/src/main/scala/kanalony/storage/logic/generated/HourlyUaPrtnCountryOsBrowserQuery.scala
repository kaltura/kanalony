package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnCountryOsBrowserQuery extends QueryBase[HourlyUaPrtnCountryOsBrowserQueryParams, HourlyUaPrtnCountryOsBrowserRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnCountryOsBrowserQueryParams = {
        val (partner_id,country,operating_system,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int,Int]((Dimensions.partner,Dimensions.country,Dimensions.operatingSystem,Dimensions.browser), params)
        HourlyUaPrtnCountryOsBrowserQueryParams(params.start, params.end, partner_id,country,operating_system,browser, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnCountryOsBrowserQueryParams): Future[List[HourlyUaPrtnCountryOsBrowserRow]] = {
        val rawQueryResult = HourlyUaPrtnCountryOsBrowserTableAccessor.query(params.partnerIdList,params.countryList,params.operatingSystemList,params.browserList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnCountryOsBrowserRow): List[String] = {
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

      override private[logic] def extractMetric(row: HourlyUaPrtnCountryOsBrowserRow): Int = row.metric
    }

case class HourlyUaPrtnCountryOsBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[Int]) extends IYearlyPartitionedQueryParams