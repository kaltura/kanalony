package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnEntryDomainQuery(accessor : IHourlyAggPrtnEntryDomainTableAccessor) extends QueryBase[HourlyAggPrtnEntryDomainQueryParams, HourlyAggPrtnEntryDomainRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryDomainQueryParams = {
        val (partner_id,entry_id,domain) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.syndicationDomain), params)
        HourlyAggPrtnEntryDomainQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,domain, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryDomainQueryParams): Future[List[HourlyAggPrtnEntryDomainRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.domainList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.syndicationDomain.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryDomainRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.domain.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryDomainRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnEntryDomainRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnEntryDomainRow = {
        HourlyAggPrtnEntryDomainRow(row.partnerId, row.entryId, row.domain, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnEntryDomainQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], domainList : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams