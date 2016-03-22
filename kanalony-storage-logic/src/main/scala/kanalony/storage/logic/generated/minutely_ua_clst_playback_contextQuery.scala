package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_clst_playback_contextQuery extends QueryBase[minutely_ua_clst_playback_contextQueryParams, minutely_ua_clst_playback_contextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_clst_playback_contextQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        minutely_ua_clst_playback_contextQueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_clst_playback_contextQueryParams): Future[List[minutely_ua_clst_playback_contextRow]] = {
        val rawQueryResult = minutely_ua_clst_playback_contextTableAccessor.query(params.partner_id_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.playbackContext.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_clst_playback_contextRow): List[String] = {
        List(row.partner_id.toString,row.metric.toString,row.minute.toString,row.playback_context.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: minutely_ua_clst_playback_contextRow): Int = row.metric
    }

case class minutely_ua_clst_playback_contextQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], metric_list : List[Int]) 