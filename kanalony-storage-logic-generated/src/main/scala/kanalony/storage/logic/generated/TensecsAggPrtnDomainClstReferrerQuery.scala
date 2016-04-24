package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggPrtnDomainClstReferrerQuery(accessor : ITensecsAggPrtnDomainClstReferrerTableAccessor) extends QueryBase[TensecsAggPrtnDomainClstReferrerQueryParams, TensecsAggPrtnDomainClstReferrerRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnDomainClstReferrerQueryParams = {
        val (partner_id,domain) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.syndicationDomain), params)
        TensecsAggPrtnDomainClstReferrerQueryParams(params.startUtc, params.endUtc, partner_id,domain, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnDomainClstReferrerQueryParams): Future[List[TensecsAggPrtnDomainClstReferrerRow]] = {
        accessor.query(params.partnerIdList,params.domainList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.syndicationDomain.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.referrer.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnDomainClstReferrerRow): List[String] = {
        List(row.partnerId.toString,row.domain.toString,row.metric.toString,row.tensecs.toString,row.referrer.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: TensecsAggPrtnDomainClstReferrerRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggPrtnDomainClstReferrerRow, timezoneOffsetFromUtc : Int) : TensecsAggPrtnDomainClstReferrerRow = {
        TensecsAggPrtnDomainClstReferrerRow(row.partnerId, row.domain, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.referrer, row.value)
      }

    }

case class TensecsAggPrtnDomainClstReferrerQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], domainList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams