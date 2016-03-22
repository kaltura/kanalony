package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_prtn_entry_cv1_clst_cv2Query extends QueryBase[minutely_ua_prtn_entry_cv1_clst_cv2QueryParams, minutely_ua_prtn_entry_cv1_clst_cv2Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_prtn_entry_cv1_clst_cv2QueryParams = {
        val (partner_id,entry_id,custom_var1) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.cf1), params)
        minutely_ua_prtn_entry_cv1_clst_cv2QueryParams(params.start, params.end, partner_id,entry_id,custom_var1, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_prtn_entry_cv1_clst_cv2QueryParams): Future[List[minutely_ua_prtn_entry_cv1_clst_cv2Row]] = {
        val rawQueryResult = minutely_ua_prtn_entry_cv1_clst_cv2TableAccessor.query(params.partner_id_list,params.entry_id_list,params.custom_var1_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.cf1.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.cf2.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_prtn_entry_cv1_clst_cv2Row): List[String] = {
        List(row.partner_id.toString,row.entry_id.toString,row.custom_var1.toString,row.metric.toString,row.minute.toString,row.custom_var2.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: minutely_ua_prtn_entry_cv1_clst_cv2Row): Int = row.metric
    }

case class minutely_ua_prtn_entry_cv1_clst_cv2QueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], entry_id_list : List[String], custom_var1_list : List[String], metric_list : List[Int]) 