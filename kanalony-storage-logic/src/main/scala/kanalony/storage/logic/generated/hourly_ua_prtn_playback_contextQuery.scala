package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_prtn_playback_contextQuery extends QueryBase[hourly_ua_prtn_playback_contextQueryParams, hourly_ua_prtn_playback_contextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_prtn_playback_contextQueryParams = {
        val (partner_id,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.playbackContext), params)
        hourly_ua_prtn_playback_contextQueryParams(params.start, params.end, partner_id,playback_context, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_prtn_playback_contextQueryParams): Future[List[hourly_ua_prtn_playback_contextRow]] = {
        val rawQueryResult = hourly_ua_prtn_playback_contextTableAccessor.query(params.partner_id_list,params.playback_context_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_prtn_playback_contextRow): List[String] = {
        List(row.partner_id.toString,row.playback_context.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: hourly_ua_prtn_playback_contextRow): Int = row.metric
    }

case class hourly_ua_prtn_playback_contextQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], playback_context_list : List[String], metric_list : List[Int]) extends IYearlyPartitionedQueryParams