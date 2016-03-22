package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_clst_cv1Query extends QueryBase[minutely_ua_clst_cv1QueryParams, minutely_ua_clst_cv1Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_clst_cv1QueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        minutely_ua_clst_cv1QueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_clst_cv1QueryParams): Future[List[minutely_ua_clst_cv1Row]] = {
        val rawQueryResult = minutely_ua_clst_cv1TableAccessor.query(params.partner_id_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.cf1.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_clst_cv1Row): List[String] = {
        List(row.partner_id.toString,row.metric.toString,row.minute.toString,row.custom_var1.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: minutely_ua_clst_cv1Row): Int = row.metric
    }

case class minutely_ua_clst_cv1QueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], metric_list : List[Int]) 