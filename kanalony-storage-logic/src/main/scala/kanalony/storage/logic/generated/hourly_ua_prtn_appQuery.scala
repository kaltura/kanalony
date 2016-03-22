package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_prtn_appQuery extends QueryBase[hourly_ua_prtn_appQueryParams, hourly_ua_prtn_appRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_prtn_appQueryParams = {
        val (partner_id,application) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.application), params)
        hourly_ua_prtn_appQueryParams(params.start, params.end, partner_id,application, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_prtn_appQueryParams): Future[List[hourly_ua_prtn_appRow]] = {
        val rawQueryResult = hourly_ua_prtn_appTableAccessor.query(params.partner_id_list,params.application_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.application.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_prtn_appRow): List[String] = {
        List(row.partner_id.toString,row.application.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: hourly_ua_prtn_appRow): Int = row.metric
    }

case class hourly_ua_prtn_appQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], application_list : List[String], metric_list : List[Int]) extends IYearlyPartitionedQueryParams