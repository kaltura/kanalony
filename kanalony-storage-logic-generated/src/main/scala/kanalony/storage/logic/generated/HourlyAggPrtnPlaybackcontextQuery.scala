package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnPlaybackcontextQuery(accessor : IHourlyAggPrtnPlaybackcontextTableAccessor) extends QueryBase[HourlyAggPrtnPlaybackcontextQueryParams, HourlyAggPrtnPlaybackcontextRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnPlaybackcontextQueryParams = {
        val (partner_id,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.playbackContext), params)
        HourlyAggPrtnPlaybackcontextQueryParams(params.startUtc, params.endUtc, partner_id,playback_context, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnPlaybackcontextQueryParams): Future[List[HourlyAggPrtnPlaybackcontextRow]] = {
        accessor.query(params.partnerIdList,params.playbackContextList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnPlaybackcontextRow): List[String] = {
        List(row.partnerId.toString,row.playbackContext.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggPrtnPlaybackcontextRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnPlaybackcontextRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnPlaybackcontextRow = {
        HourlyAggPrtnPlaybackcontextRow(row.partnerId, row.playbackContext, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnPlaybackcontextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], playbackContextList : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams