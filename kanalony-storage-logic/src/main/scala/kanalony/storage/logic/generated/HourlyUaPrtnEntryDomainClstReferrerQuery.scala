package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryDomainClstReferrerQuery extends QueryBase[HourlyUaPrtnEntryDomainClstReferrerQueryParams, HourlyUaPrtnEntryDomainClstReferrerRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryDomainClstReferrerQueryParams = {
        val (partner_id,entry_id,domain) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.syndicationDomain), params)
        HourlyUaPrtnEntryDomainClstReferrerQueryParams(params.start, params.end, partner_id,entry_id,domain, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryDomainClstReferrerQueryParams): Future[List[HourlyUaPrtnEntryDomainClstReferrerRow]] = {
        val rawQueryResult = HourlyUaPrtnEntryDomainClstReferrerTableAccessor.query(params.partnerIdList,params.entryIdList,params.domainList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.syndicationDomain.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.referrer.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryDomainClstReferrerRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.domain.toString,row.metric.toString,row.hour.toString,row.referrer.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryDomainClstReferrerRow): Int = row.metric
    }

case class HourlyUaPrtnEntryDomainClstReferrerQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], domainList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams