package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnDomainClstEntryQuery extends QueryBase[HourlyUaPrtnDomainClstEntryQueryParams, HourlyUaPrtnDomainClstEntryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnDomainClstEntryQueryParams = {
        val (partner_id,domain) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.syndicationDomain), params)
        HourlyUaPrtnDomainClstEntryQueryParams(params.start, params.end, partner_id,domain, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnDomainClstEntryQueryParams): Future[List[HourlyUaPrtnDomainClstEntryRow]] = {
        val rawQueryResult = HourlyUaPrtnDomainClstEntryTableAccessor.query(params.partnerIdList,params.domainList,params.years,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.syndicationDomain.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnDomainClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.domain.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnDomainClstEntryRow): Int = row.metric
    }

case class HourlyUaPrtnDomainClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], domainList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams