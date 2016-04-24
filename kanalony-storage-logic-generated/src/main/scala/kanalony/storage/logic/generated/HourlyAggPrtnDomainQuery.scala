package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnDomainQuery(accessor : IHourlyAggPrtnDomainTableAccessor) extends QueryBase[HourlyAggPrtnDomainQueryParams, HourlyAggPrtnDomainRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnDomainQueryParams = {
        val (partner_id,domain) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.syndicationDomain), params)
        HourlyAggPrtnDomainQueryParams(params.startUtc, params.endUtc, partner_id,domain, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnDomainQueryParams): Future[List[HourlyAggPrtnDomainRow]] = {
        accessor.query(params.partnerIdList,params.domainList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.syndicationDomain.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnDomainRow): List[String] = {
        List(row.partnerId.toString,row.domain.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggPrtnDomainRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnDomainRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnDomainRow = {
        HourlyAggPrtnDomainRow(row.partnerId, row.domain, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnDomainQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], domainList : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams