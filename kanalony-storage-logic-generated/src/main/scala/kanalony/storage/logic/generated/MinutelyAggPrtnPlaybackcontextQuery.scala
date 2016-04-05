package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnPlaybackcontextQuery(accessor : IMinutelyAggPrtnPlaybackcontextTableAccessor) extends QueryBase[MinutelyAggPrtnPlaybackcontextQueryParams, MinutelyAggPrtnPlaybackcontextRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnPlaybackcontextQueryParams = {
        val (partner_id,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.playbackContext), params)
        MinutelyAggPrtnPlaybackcontextQueryParams(params.start, params.end, partner_id,playback_context, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnPlaybackcontextQueryParams): Future[List[MinutelyAggPrtnPlaybackcontextRow]] = {
        accessor.query(params.partnerIdList,params.playbackContextList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnPlaybackcontextRow): List[String] = {
        List(row.partnerId.toString,row.playbackContext.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyAggPrtnPlaybackcontextRow): String = row.metric
    }

case class MinutelyAggPrtnPlaybackcontextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], playbackContextList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams