package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPtrnOsClstBrowserQuery extends QueryBase[HourlyUaPtrnOsClstBrowserQueryParams, HourlyUaPtrnOsClstBrowserRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPtrnOsClstBrowserQueryParams = {
        val (partner_id,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.operatingSystem), params)
        HourlyUaPtrnOsClstBrowserQueryParams(params.start, params.end, partner_id,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPtrnOsClstBrowserQueryParams): Future[List[HourlyUaPtrnOsClstBrowserRow]] = {
        val rawQueryResult = HourlyUaPtrnOsClstBrowserTableAccessor.query(params.partnerIdList,params.operatingSystemList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.browser.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPtrnOsClstBrowserRow): List[String] = {
        List(row.partnerId.toString,row.operatingSystem.toString,row.metric.toString,row.hour.toString,row.browser.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPtrnOsClstBrowserRow): Int = row.metric
    }

case class HourlyUaPtrnOsClstBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int]) extends IYearlyPartitionedQueryParams