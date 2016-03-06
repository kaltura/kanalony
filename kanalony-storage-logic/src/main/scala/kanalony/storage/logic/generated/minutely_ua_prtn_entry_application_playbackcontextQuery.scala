package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.api.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_prtn_entry_application_playbackcontextQuery extends QueryBase[minutely_ua_prtn_entry_application_playbackcontextQueryParams, minutely_ua_prtn_entry_application_playbackcontextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_prtn_entry_application_playbackcontextQueryParams = {
        val (partner_id,entry_id,application,playbackContext) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.application,Dimensions.playbackContext), params)
        minutely_ua_prtn_entry_application_playbackcontextQueryParams(params.start, params.end, partner_id,entry_id,application,playbackContext, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_prtn_entry_application_playbackcontextQueryParams): Future[List[minutely_ua_prtn_entry_application_playbackcontextRow]] = {
        val rawQueryResult = minutely_ua_prtn_entry_application_playbackcontextTableAccessor.query(params.partner_id_list,params.entry_id_list,params.application_list,params.playbackContext_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.application.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_prtn_entry_application_playbackcontextRow): List[String] = {
        List(row.partner_id.toString,row.entry_id.toString,row.application.toString,row.playbackContext.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: minutely_ua_prtn_entry_application_playbackcontextRow): Int = row.metric
    }

case class minutely_ua_prtn_entry_application_playbackcontextQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], entry_id_list : List[String], application_list : List[String], playbackContext_list : List[String], metric_list : List[Int]) 