package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaClstPlaybackContextQuery extends QueryBase[MinutelyUaClstPlaybackContextQueryParams, MinutelyUaClstPlaybackContextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaClstPlaybackContextQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        MinutelyUaClstPlaybackContextQueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaClstPlaybackContextQueryParams): Future[List[MinutelyUaClstPlaybackContextRow]] = {
        val rawQueryResult = MinutelyUaClstPlaybackContextTableAccessor.query(params.partnerIdList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.playbackContext.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaClstPlaybackContextRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.minute.toString,row.playbackContext.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyUaClstPlaybackContextRow): Int = row.metric
    }

case class MinutelyUaClstPlaybackContextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[Int]) 