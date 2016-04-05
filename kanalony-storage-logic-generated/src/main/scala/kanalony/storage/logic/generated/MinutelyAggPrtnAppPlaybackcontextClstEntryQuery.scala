package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnAppPlaybackcontextClstEntryQuery(accessor : IMinutelyAggPrtnAppPlaybackcontextClstEntryTableAccessor) extends QueryBase[MinutelyAggPrtnAppPlaybackcontextClstEntryQueryParams, MinutelyAggPrtnAppPlaybackcontextClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnAppPlaybackcontextClstEntryQueryParams = {
        val (partner_id,application,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.application,Dimensions.playbackContext), params)
        MinutelyAggPrtnAppPlaybackcontextClstEntryQueryParams(params.start, params.end, partner_id,application,playback_context, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnAppPlaybackcontextClstEntryQueryParams): Future[List[MinutelyAggPrtnAppPlaybackcontextClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.applicationList,params.playbackContextList,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.application.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnAppPlaybackcontextClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.application.toString,row.playbackContext.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyAggPrtnAppPlaybackcontextClstEntryRow): String = row.metric
    }

case class MinutelyAggPrtnAppPlaybackcontextClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], applicationList : List[String], playbackContextList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams