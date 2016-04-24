package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnAppPlaybackcontextQuery(accessor : IHourlyAggPrtnAppPlaybackcontextTableAccessor) extends QueryBase[HourlyAggPrtnAppPlaybackcontextQueryParams, HourlyAggPrtnAppPlaybackcontextRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnAppPlaybackcontextQueryParams = {
        val (partner_id,application,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.application,Dimensions.playbackContext), params)
        HourlyAggPrtnAppPlaybackcontextQueryParams(params.startUtc, params.endUtc, partner_id,application,playback_context, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnAppPlaybackcontextQueryParams): Future[List[HourlyAggPrtnAppPlaybackcontextRow]] = {
        accessor.query(params.partnerIdList,params.applicationList,params.playbackContextList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.application.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnAppPlaybackcontextRow): List[String] = {
        List(row.partnerId.toString,row.application.toString,row.playbackContext.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnAppPlaybackcontextRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnAppPlaybackcontextRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnAppPlaybackcontextRow = {
        HourlyAggPrtnAppPlaybackcontextRow(row.partnerId, row.application, row.playbackContext, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnAppPlaybackcontextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], applicationList : List[String], playbackContextList : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams