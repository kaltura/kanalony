package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_prtn_application_playbackcontextQuery extends QueryBase[hourly_ua_prtn_application_playbackcontextQueryParams, hourly_ua_prtn_application_playbackcontextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_prtn_application_playbackcontextQueryParams = {
        val (partner_id,application,playbackContext) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.application,Dimensions.playbackContext), params)
        hourly_ua_prtn_application_playbackcontextQueryParams(params.start, params.end, partner_id,application,playbackContext, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_prtn_application_playbackcontextQueryParams): Future[List[hourly_ua_prtn_application_playbackcontextRow]] = {
        val rawQueryResult = hourly_ua_prtn_application_playbackcontextTableAccessor.query(params.partner_id_list,params.application_list,params.playbackContext_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.application.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_prtn_application_playbackcontextRow): List[String] = {
        List(row.partner_id.toString,row.application.toString,row.playbackContext.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: hourly_ua_prtn_application_playbackcontextRow): Int = row.metric
    }

case class hourly_ua_prtn_application_playbackcontextQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], application_list : List[String], playbackContext_list : List[String], metric_list : List[Int]) extends IYearlyPartitionedQueryParams