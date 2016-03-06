package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.api.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_prtn_entry_playbackcontextQuery extends QueryBase[hourly_ua_prtn_entry_playbackcontextQueryParams, hourly_ua_prtn_entry_playbackcontextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_prtn_entry_playbackcontextQueryParams = {
        val (partner_id,entry_id,playbackContext) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.playbackContext), params)
        hourly_ua_prtn_entry_playbackcontextQueryParams(params.start, params.end, partner_id,entry_id,playbackContext, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_prtn_entry_playbackcontextQueryParams): Future[List[hourly_ua_prtn_entry_playbackcontextRow]] = {
        val rawQueryResult = hourly_ua_prtn_entry_playbackcontextTableAccessor.query(params.partner_id_list,params.entry_id_list,params.playbackContext_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_prtn_entry_playbackcontextRow): List[String] = {
        List(row.partner_id.toString,row.entry_id.toString,row.playbackContext.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: hourly_ua_prtn_entry_playbackcontextRow): Int = row.metric
    }

case class hourly_ua_prtn_entry_playbackcontextQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], entry_id_list : List[String], playbackContext_list : List[String], metric_list : List[Int]) extends IYearlyPartitionedQueryParams