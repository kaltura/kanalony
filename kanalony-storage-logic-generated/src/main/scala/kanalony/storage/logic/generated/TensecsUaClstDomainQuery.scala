package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsUaClstDomainQuery extends QueryBase[TensecsUaClstDomainQueryParams, TensecsUaClstDomainRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsUaClstDomainQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        TensecsUaClstDomainQueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: TensecsUaClstDomainQueryParams): Future[List[TensecsUaClstDomainRow]] = {
        val rawQueryResult = TensecsUaClstDomainTableAccessor.query(params.partnerIdList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.syndicationDomain.toString,"value")
      }

      override protected def getResultRow(row: TensecsUaClstDomainRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.tensecs.toString,row.domain.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: TensecsUaClstDomainRow): Int = row.metric
    }

case class TensecsUaClstDomainQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[Int]) 