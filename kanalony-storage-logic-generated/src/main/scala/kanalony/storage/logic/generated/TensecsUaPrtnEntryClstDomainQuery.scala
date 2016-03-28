package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsUaPrtnEntryClstDomainQuery extends QueryBase[TensecsUaPrtnEntryClstDomainQueryParams, TensecsUaPrtnEntryClstDomainRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsUaPrtnEntryClstDomainQueryParams = {
        val (partner_id,entry_id) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.entry), params)
        TensecsUaPrtnEntryClstDomainQueryParams(params.start, params.end, partner_id,entry_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: TensecsUaPrtnEntryClstDomainQueryParams): Future[List[TensecsUaPrtnEntryClstDomainRow]] = {
        val rawQueryResult = TensecsUaPrtnEntryClstDomainTableAccessor.query(params.partnerIdList,params.entryIdList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.syndicationDomain.toString,"value")
      }

      override protected def getResultRow(row: TensecsUaPrtnEntryClstDomainRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.metric.toString,row.tensecs.toString,row.domain.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: TensecsUaPrtnEntryClstDomainRow): Int = row.metric
    }

case class TensecsUaPrtnEntryClstDomainQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int]) 