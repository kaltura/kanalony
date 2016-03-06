package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_ptrn_domain_clst_referrerQuery extends QueryBase[minutely_ua_ptrn_domain_clst_referrerQueryParams, minutely_ua_ptrn_domain_clst_referrerRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_ptrn_domain_clst_referrerQueryParams = {
        val (partner_id,domain) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.syndicationDomain), params)
        minutely_ua_ptrn_domain_clst_referrerQueryParams(params.start, params.end, partner_id,domain, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_ptrn_domain_clst_referrerQueryParams): Future[List[minutely_ua_ptrn_domain_clst_referrerRow]] = {
        val rawQueryResult = minutely_ua_ptrn_domain_clst_referrerTableAccessor.query(params.partner_id_list,params.domain_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.syndicationDomain.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.referrer.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_ptrn_domain_clst_referrerRow): List[String] = {
        List(row.partner_id.toString,row.domain.toString,row.metric.toString,row.minute.toString,row.referrer.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: minutely_ua_ptrn_domain_clst_referrerRow): Int = row.metric
    }

case class minutely_ua_ptrn_domain_clst_referrerQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], domain_list : List[String], metric_list : List[Int]) 