package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnAppPlaybackcontextQuery(accessor : IMinutelyAggPrtnAppPlaybackcontextTableAccessor) extends QueryBase[MinutelyAggPrtnAppPlaybackcontextQueryParams, MinutelyAggPrtnAppPlaybackcontextRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnAppPlaybackcontextQueryParams = {
        val (partner_id,application,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.application,Dimensions.playbackContext), params)
        MinutelyAggPrtnAppPlaybackcontextQueryParams(params.startUtc, params.endUtc, partner_id,application,playback_context, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnAppPlaybackcontextQueryParams): Future[List[MinutelyAggPrtnAppPlaybackcontextRow]] = {
        accessor.query(params.partnerIdList,params.applicationList,params.playbackContextList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.application.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnAppPlaybackcontextRow): List[String] = {
        List(row.partnerId.toString,row.application.toString,row.playbackContext.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnAppPlaybackcontextRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnAppPlaybackcontextRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnAppPlaybackcontextRow = {
        MinutelyAggPrtnAppPlaybackcontextRow(row.partnerId, row.application, row.playbackContext, row.metric, row.day, row.minute.plusHours(timezoneOffsetFromUtc), row.value)
      }

    }

case class MinutelyAggPrtnAppPlaybackcontextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], applicationList : List[String], playbackContextList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams