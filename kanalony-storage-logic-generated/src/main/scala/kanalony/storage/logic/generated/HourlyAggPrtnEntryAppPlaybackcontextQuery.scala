package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnEntryAppPlaybackcontextQuery(accessor : IHourlyAggPrtnEntryAppPlaybackcontextTableAccessor) extends QueryBase[HourlyAggPrtnEntryAppPlaybackcontextQueryParams, HourlyAggPrtnEntryAppPlaybackcontextRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryAppPlaybackcontextQueryParams = {
        val (partner_id,entry_id,application,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.application,Dimensions.playbackContext), params)
        HourlyAggPrtnEntryAppPlaybackcontextQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,application,playback_context, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryAppPlaybackcontextQueryParams): Future[List[HourlyAggPrtnEntryAppPlaybackcontextRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.applicationList,params.playbackContextList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.application.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryAppPlaybackcontextRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.application.toString,row.playbackContext.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryAppPlaybackcontextRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnEntryAppPlaybackcontextRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnEntryAppPlaybackcontextRow = {
        HourlyAggPrtnEntryAppPlaybackcontextRow(row.partnerId, row.entryId, row.application, row.playbackContext, row.metric, row.year, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnEntryAppPlaybackcontextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], playbackContextList : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams