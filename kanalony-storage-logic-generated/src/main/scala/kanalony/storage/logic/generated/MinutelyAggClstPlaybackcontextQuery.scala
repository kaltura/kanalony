package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggClstPlaybackcontextQuery(accessor : IMinutelyAggClstPlaybackcontextTableAccessor) extends QueryBase[MinutelyAggClstPlaybackcontextQueryParams, MinutelyAggClstPlaybackcontextRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggClstPlaybackcontextQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        MinutelyAggClstPlaybackcontextQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggClstPlaybackcontextQueryParams): Future[List[MinutelyAggClstPlaybackcontextRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.playbackContext.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggClstPlaybackcontextRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.minute.toString,row.playbackContext.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyAggClstPlaybackcontextRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggClstPlaybackcontextRow, timezoneOffsetFromUtc : Int) : MinutelyAggClstPlaybackcontextRow = {
        MinutelyAggClstPlaybackcontextRow(row.partnerId, row.metric, row.day, row.minute.plusHours(timezoneOffsetFromUtc), row.playbackContext, row.value)
      }

    }

case class MinutelyAggClstPlaybackcontextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams