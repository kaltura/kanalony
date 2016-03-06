package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.api.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_prtn_entry_application_clst_playbackcontextQuery extends QueryBase[minutely_ua_prtn_entry_application_clst_playbackcontextQueryParams, minutely_ua_prtn_entry_application_clst_playbackcontextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_prtn_entry_application_clst_playbackcontextQueryParams = {
        val (partner_id,entry_id,application) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.application), params)
        minutely_ua_prtn_entry_application_clst_playbackcontextQueryParams(params.start, params.end, partner_id,entry_id,application, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_prtn_entry_application_clst_playbackcontextQueryParams): Future[List[minutely_ua_prtn_entry_application_clst_playbackcontextRow]] = {
        val rawQueryResult = minutely_ua_prtn_entry_application_clst_playbackcontextTableAccessor.query(params.partner_id_list,params.entry_id_list,params.application_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.application.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.playbackContext.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_prtn_entry_application_clst_playbackcontextRow): List[String] = {
        List(row.partner_id.toString,row.entry_id.toString,row.application.toString,row.metric.toString,row.minute.toString,row.playbackContext.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: minutely_ua_prtn_entry_application_clst_playbackcontextRow): Int = row.metric
    }

case class minutely_ua_prtn_entry_application_clst_playbackcontextQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], entry_id_list : List[String], application_list : List[String], metric_list : List[Int]) extends IYearlyPartitionedQueryParams