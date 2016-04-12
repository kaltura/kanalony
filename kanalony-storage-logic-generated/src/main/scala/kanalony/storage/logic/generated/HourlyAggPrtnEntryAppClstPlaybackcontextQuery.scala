package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnEntryAppClstPlaybackcontextQuery(accessor : IHourlyAggPrtnEntryAppClstPlaybackcontextTableAccessor) extends QueryBase[HourlyAggPrtnEntryAppClstPlaybackcontextQueryParams, HourlyAggPrtnEntryAppClstPlaybackcontextRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryAppClstPlaybackcontextQueryParams = {
        val (partner_id,entry_id,application) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.application), params)
        HourlyAggPrtnEntryAppClstPlaybackcontextQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,application, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryAppClstPlaybackcontextQueryParams): Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.applicationList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.application.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.playbackContext.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryAppClstPlaybackcontextRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.application.toString,row.metric.toString,row.hour.toString,row.playbackContext.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryAppClstPlaybackcontextRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnEntryAppClstPlaybackcontextRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnEntryAppClstPlaybackcontextRow = {
        HourlyAggPrtnEntryAppClstPlaybackcontextRow(row.partnerId, row.entryId, row.application, row.metric, row.year, row.hour.plusHours(timezoneOffsetFromUtc), row.playbackContext, row.value)
      }

    }

case class HourlyAggPrtnEntryAppClstPlaybackcontextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams