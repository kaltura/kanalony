package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_prtn_browserQuery extends QueryBase[hourly_ua_prtn_browserQueryParams, hourly_ua_prtn_browserRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_prtn_browserQueryParams = {
        val (partner_id,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.browser), params)
        hourly_ua_prtn_browserQueryParams(params.start, params.end, partner_id,browser, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_prtn_browserQueryParams): Future[List[hourly_ua_prtn_browserRow]] = {
        val rawQueryResult = hourly_ua_prtn_browserTableAccessor.query(params.partner_id_list,params.browser_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_prtn_browserRow): List[String] = {
        List(row.partner_id.toString,row.browser.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: hourly_ua_prtn_browserRow): Int = row.metric
    }

case class hourly_ua_prtn_browserQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], browser_list : List[Int], metric_list : List[Int]) extends IYearlyPartitionedQueryParams