package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggClstBrowserQuery(accessor : IHourlyAggClstBrowserTableAccessor) extends QueryBase[HourlyAggClstBrowserQueryParams, HourlyAggClstBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggClstBrowserQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyAggClstBrowserQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggClstBrowserQueryParams): Future[List[HourlyAggClstBrowserRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.browser.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggClstBrowserRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.browser.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggClstBrowserRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggClstBrowserRow, timezoneOffsetFromUtc : Int) : HourlyAggClstBrowserRow = {
        HourlyAggClstBrowserRow(row.partnerId, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.browser, row.value)
      }

    }

case class HourlyAggClstBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams