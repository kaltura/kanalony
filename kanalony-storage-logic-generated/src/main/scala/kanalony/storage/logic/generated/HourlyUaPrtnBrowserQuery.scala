package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnBrowserQuery extends QueryBase[HourlyUaPrtnBrowserQueryParams, HourlyUaPrtnBrowserRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnBrowserQueryParams = {
        val (partner_id,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.browser), params)
        HourlyUaPrtnBrowserQueryParams(params.start, params.end, partner_id,browser, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnBrowserQueryParams): Future[List[HourlyUaPrtnBrowserRow]] = {
        val rawQueryResult = HourlyUaPrtnBrowserTableAccessor.query(params.partnerIdList,params.browserList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnBrowserRow): List[String] = {
        List(row.partnerId.toString,row.browser.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyUaPrtnBrowserRow): Int = row.metric
    }

case class HourlyUaPrtnBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], browserList : List[Int], metricList : List[Int]) extends IYearlyPartitionedQueryParams