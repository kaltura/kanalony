package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggClstPlaybackcontextQuery(accessor : IHourlyAggClstPlaybackcontextTableAccessor) extends QueryBase[HourlyAggClstPlaybackcontextQueryParams, HourlyAggClstPlaybackcontextRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggClstPlaybackcontextQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyAggClstPlaybackcontextQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggClstPlaybackcontextQueryParams): Future[List[HourlyAggClstPlaybackcontextRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.playbackContext.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggClstPlaybackcontextRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.playbackContext.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggClstPlaybackcontextRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggClstPlaybackcontextRow, timezoneOffsetFromUtc : Int) : HourlyAggClstPlaybackcontextRow = {
        HourlyAggClstPlaybackcontextRow(row.partnerId, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.playbackContext, row.value)
      }

    }

case class HourlyAggClstPlaybackcontextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams