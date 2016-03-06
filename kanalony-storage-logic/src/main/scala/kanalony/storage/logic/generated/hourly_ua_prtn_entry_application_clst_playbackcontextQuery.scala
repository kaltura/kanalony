package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.api.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_prtn_entry_application_clst_playbackcontextQuery extends QueryBase[hourly_ua_prtn_entry_application_clst_playbackcontextQueryParams, hourly_ua_prtn_entry_application_clst_playbackcontextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_prtn_entry_application_clst_playbackcontextQueryParams = {
        val (partner_id,entry_id,application) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.application), params)
        hourly_ua_prtn_entry_application_clst_playbackcontextQueryParams(params.start, params.end, partner_id,entry_id,application, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_prtn_entry_application_clst_playbackcontextQueryParams): Future[List[hourly_ua_prtn_entry_application_clst_playbackcontextRow]] = {
        val rawQueryResult = hourly_ua_prtn_entry_application_clst_playbackcontextTableAccessor.query(params.partner_id_list,params.entry_id_list,params.application_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.application.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.playbackContext.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_prtn_entry_application_clst_playbackcontextRow): List[String] = {
        List(row.partner_id.toString,row.entry_id.toString,row.application.toString,row.metric.toString,row.hour.toString,row.playbackContext.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: hourly_ua_prtn_entry_application_clst_playbackcontextRow): Int = row.metric
    }

case class hourly_ua_prtn_entry_application_clst_playbackcontextQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], entry_id_list : List[String], application_list : List[String], metric_list : List[Int]) extends IYearlyPartitionedQueryParams