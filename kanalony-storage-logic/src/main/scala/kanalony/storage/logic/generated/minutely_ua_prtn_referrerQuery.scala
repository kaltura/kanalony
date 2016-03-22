package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_prtn_referrerQuery extends QueryBase[minutely_ua_prtn_referrerQueryParams, minutely_ua_prtn_referrerRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_prtn_referrerQueryParams = {
        val (partner_id,referrer) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.referrer), params)
        minutely_ua_prtn_referrerQueryParams(params.start, params.end, partner_id,referrer, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_prtn_referrerQueryParams): Future[List[minutely_ua_prtn_referrerRow]] = {
        val rawQueryResult = minutely_ua_prtn_referrerTableAccessor.query(params.partner_id_list,params.referrer_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.referrer.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_prtn_referrerRow): List[String] = {
        List(row.partner_id.toString,row.referrer.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: minutely_ua_prtn_referrerRow): Int = row.metric
    }

case class minutely_ua_prtn_referrerQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], referrer_list : List[String], metric_list : List[Int]) 