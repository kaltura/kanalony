package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnEntryAppPlaybackcontextQuery(accessor : IMinutelyAggPrtnEntryAppPlaybackcontextTableAccessor) extends QueryBase[MinutelyAggPrtnEntryAppPlaybackcontextQueryParams, MinutelyAggPrtnEntryAppPlaybackcontextRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnEntryAppPlaybackcontextQueryParams = {
        val (partner_id,entry_id,application,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.application,Dimensions.playbackContext), params)
        MinutelyAggPrtnEntryAppPlaybackcontextQueryParams(params.start, params.end, partner_id,entry_id,application,playback_context, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnEntryAppPlaybackcontextQueryParams): Future[List[MinutelyAggPrtnEntryAppPlaybackcontextRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.applicationList,params.playbackContextList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.application.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnEntryAppPlaybackcontextRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.application.toString,row.playbackContext.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyAggPrtnEntryAppPlaybackcontextRow): String = row.metric
    }

case class MinutelyAggPrtnEntryAppPlaybackcontextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], playbackContextList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams