package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggClstBrowserQuery(accessor : IMinutelyAggClstBrowserTableAccessor) extends QueryBase[MinutelyAggClstBrowserQueryParams, MinutelyAggClstBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggClstBrowserQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        MinutelyAggClstBrowserQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggClstBrowserQueryParams): Future[List[MinutelyAggClstBrowserRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.browser.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggClstBrowserRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.minute.toString,row.browser.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyAggClstBrowserRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggClstBrowserRow, timezoneOffsetFromUtc : Int) : MinutelyAggClstBrowserRow = {
        MinutelyAggClstBrowserRow(row.partnerId, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.browser, row.value)
      }

    }

case class MinutelyAggClstBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams