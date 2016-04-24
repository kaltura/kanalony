package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggClstDomainQuery(accessor : ITensecsAggClstDomainTableAccessor) extends QueryBase[TensecsAggClstDomainQueryParams, TensecsAggClstDomainRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggClstDomainQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        TensecsAggClstDomainQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggClstDomainQueryParams): Future[List[TensecsAggClstDomainRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.syndicationDomain.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggClstDomainRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.tensecs.toString,row.domain.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.syndicationDomain, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: TensecsAggClstDomainRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggClstDomainRow, timezoneOffsetFromUtc : Int) : TensecsAggClstDomainRow = {
        TensecsAggClstDomainRow(row.partnerId, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.domain, row.value)
      }

    }

case class TensecsAggClstDomainQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams