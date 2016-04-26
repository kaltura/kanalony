package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggClstDomainQuery(accessor : IHourlyAggClstDomainTableAccessor) extends QueryBase[HourlyAggClstDomainQueryParams, HourlyAggClstDomainRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggClstDomainQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyAggClstDomainQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggClstDomainQueryParams): Future[List[HourlyAggClstDomainRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.syndicationDomain.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggClstDomainRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.domain.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggClstDomainRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggClstDomainRow, timezoneOffsetFromUtc : Int) : HourlyAggClstDomainRow = {
        HourlyAggClstDomainRow(row.partnerId, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.domain, row.value)
      }

    }

case class HourlyAggClstDomainQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams