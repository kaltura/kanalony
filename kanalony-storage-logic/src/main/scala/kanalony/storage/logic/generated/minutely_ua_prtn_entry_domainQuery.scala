package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_prtn_entry_domainQuery extends QueryBase[minutely_ua_prtn_entry_domainQueryParams, minutely_ua_prtn_entry_domainRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_prtn_entry_domainQueryParams = {
        val (partner_id,entry_id,domain) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.syndicationDomain), params)
        minutely_ua_prtn_entry_domainQueryParams(params.start, params.end, partner_id,entry_id,domain, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_prtn_entry_domainQueryParams): Future[List[minutely_ua_prtn_entry_domainRow]] = {
        val rawQueryResult = minutely_ua_prtn_entry_domainTableAccessor.query(params.partner_id_list,params.entry_id_list,params.domain_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.syndicationDomain.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_prtn_entry_domainRow): List[String] = {
        List(row.partner_id.toString,row.entry_id.toString,row.domain.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: minutely_ua_prtn_entry_domainRow): Int = row.metric
    }

case class minutely_ua_prtn_entry_domainQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], entry_id_list : List[String], domain_list : List[String], metric_list : List[Int]) 