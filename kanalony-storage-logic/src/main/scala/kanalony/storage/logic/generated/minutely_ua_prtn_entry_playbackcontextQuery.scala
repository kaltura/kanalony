package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_prtn_entry_playbackcontextQuery extends QueryBase[minutely_ua_prtn_entry_playbackcontextQueryParams, minutely_ua_prtn_entry_playbackcontextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_prtn_entry_playbackcontextQueryParams = {
        val (partner_id,entry_id,playbackContext) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.playbackContext), params)
        minutely_ua_prtn_entry_playbackcontextQueryParams(params.start, params.end, partner_id,entry_id,playbackContext, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_prtn_entry_playbackcontextQueryParams): Future[List[minutely_ua_prtn_entry_playbackcontextRow]] = {
        val rawQueryResult = minutely_ua_prtn_entry_playbackcontextTableAccessor.query(params.partner_id_list,params.entry_id_list,params.playbackContext_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_prtn_entry_playbackcontextRow): List[String] = {
        List(row.partner_id.toString,row.entry_id.toString,row.playbackContext.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: minutely_ua_prtn_entry_playbackcontextRow): Int = row.metric
    }

case class minutely_ua_prtn_entry_playbackcontextQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], entry_id_list : List[String], playbackContext_list : List[String], metric_list : List[Int]) 