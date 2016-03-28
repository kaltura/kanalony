package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnCountryOsClstBrowserQuery extends QueryBase[MinutelyUaPrtnCountryOsClstBrowserQueryParams, MinutelyUaPrtnCountryOsClstBrowserRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnCountryOsClstBrowserQueryParams = {
        val (partner_id,country,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.country,Dimensions.operatingSystem), params)
        MinutelyUaPrtnCountryOsClstBrowserQueryParams(params.start, params.end, partner_id,country,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnCountryOsClstBrowserQueryParams): Future[List[MinutelyUaPrtnCountryOsClstBrowserRow]] = {
        val rawQueryResult = MinutelyUaPrtnCountryOsClstBrowserTableAccessor.query(params.partnerIdList,params.countryList,params.operatingSystemList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.browser.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnCountryOsClstBrowserRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.operatingSystem.toString,row.metric.toString,row.minute.toString,row.browser.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyUaPrtnCountryOsClstBrowserRow): Int = row.metric
    }

case class MinutelyUaPrtnCountryOsClstBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[Int]) 