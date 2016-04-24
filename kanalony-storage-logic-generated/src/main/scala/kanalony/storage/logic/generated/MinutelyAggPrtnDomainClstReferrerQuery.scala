package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnDomainClstReferrerQuery(accessor : IMinutelyAggPrtnDomainClstReferrerTableAccessor) extends QueryBase[MinutelyAggPrtnDomainClstReferrerQueryParams, MinutelyAggPrtnDomainClstReferrerRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnDomainClstReferrerQueryParams = {
        val (partner_id,domain) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.syndicationDomain), params)
        MinutelyAggPrtnDomainClstReferrerQueryParams(params.startUtc, params.endUtc, partner_id,domain, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnDomainClstReferrerQueryParams): Future[List[MinutelyAggPrtnDomainClstReferrerRow]] = {
        accessor.query(params.partnerIdList,params.domainList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.syndicationDomain.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.referrer.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnDomainClstReferrerRow): List[String] = {
        List(row.partnerId.toString,row.domain.toString,row.metric.toString,row.minute.toString,row.referrer.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnDomainClstReferrerRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnDomainClstReferrerRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnDomainClstReferrerRow = {
        MinutelyAggPrtnDomainClstReferrerRow(row.partnerId, row.domain, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.referrer, row.value)
      }

    }

case class MinutelyAggPrtnDomainClstReferrerQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], domainList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams