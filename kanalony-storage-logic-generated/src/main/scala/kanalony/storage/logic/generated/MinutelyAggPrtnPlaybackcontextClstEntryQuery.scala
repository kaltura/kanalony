package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnPlaybackcontextClstEntryQuery(accessor : IMinutelyAggPrtnPlaybackcontextClstEntryTableAccessor) extends QueryBase[MinutelyAggPrtnPlaybackcontextClstEntryQueryParams, MinutelyAggPrtnPlaybackcontextClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnPlaybackcontextClstEntryQueryParams = {
        val (partner_id,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.playbackContext), params)
        MinutelyAggPrtnPlaybackcontextClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,playback_context, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnPlaybackcontextClstEntryQueryParams): Future[List[MinutelyAggPrtnPlaybackcontextClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.playbackContextList,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnPlaybackcontextClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.playbackContext.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnPlaybackcontextClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnPlaybackcontextClstEntryRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnPlaybackcontextClstEntryRow = {
        MinutelyAggPrtnPlaybackcontextClstEntryRow(row.partnerId, row.playbackContext, row.day, row.metric, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.entryId, row.value)
      }

    }

case class MinutelyAggPrtnPlaybackcontextClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], playbackContextList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams