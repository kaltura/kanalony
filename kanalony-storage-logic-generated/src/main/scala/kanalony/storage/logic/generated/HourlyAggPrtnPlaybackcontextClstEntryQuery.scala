package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnPlaybackcontextClstEntryQuery(accessor : IHourlyAggPrtnPlaybackcontextClstEntryTableAccessor) extends QueryBase[HourlyAggPrtnPlaybackcontextClstEntryQueryParams, HourlyAggPrtnPlaybackcontextClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnPlaybackcontextClstEntryQueryParams = {
        val (partner_id,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.playbackContext), params)
        HourlyAggPrtnPlaybackcontextClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,playback_context, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnPlaybackcontextClstEntryQueryParams): Future[List[HourlyAggPrtnPlaybackcontextClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.playbackContextList,params.months,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnPlaybackcontextClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.playbackContext.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnPlaybackcontextClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnPlaybackcontextClstEntryRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnPlaybackcontextClstEntryRow = {
        HourlyAggPrtnPlaybackcontextClstEntryRow(row.partnerId, row.playbackContext, row.month, row.metric, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.entryId, row.value)
      }

    }

case class HourlyAggPrtnPlaybackcontextClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], playbackContextList : List[String], metricList : List[String]) extends IMonthlyPartitionedQueryParams