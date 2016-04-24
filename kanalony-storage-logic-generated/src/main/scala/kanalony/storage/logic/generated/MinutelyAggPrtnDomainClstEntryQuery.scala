package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnDomainClstEntryQuery(accessor : IMinutelyAggPrtnDomainClstEntryTableAccessor) extends QueryBase[MinutelyAggPrtnDomainClstEntryQueryParams, MinutelyAggPrtnDomainClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnDomainClstEntryQueryParams = {
        val (partner_id,domain) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.syndicationDomain), params)
        MinutelyAggPrtnDomainClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,domain, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnDomainClstEntryQueryParams): Future[List[MinutelyAggPrtnDomainClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.domainList,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.syndicationDomain.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnDomainClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.domain.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnDomainClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnDomainClstEntryRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnDomainClstEntryRow = {
        MinutelyAggPrtnDomainClstEntryRow(row.partnerId, row.domain, row.day, row.metric, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.entryId, row.value)
      }

    }

case class MinutelyAggPrtnDomainClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], domainList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams