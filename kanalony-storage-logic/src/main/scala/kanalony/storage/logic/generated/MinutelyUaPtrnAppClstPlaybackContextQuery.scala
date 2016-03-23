package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPtrnAppClstPlaybackContextQuery extends QueryBase[MinutelyUaPtrnAppClstPlaybackContextQueryParams, MinutelyUaPtrnAppClstPlaybackContextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPtrnAppClstPlaybackContextQueryParams = {
        val (partner_id,application) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.application), params)
        MinutelyUaPtrnAppClstPlaybackContextQueryParams(params.start, params.end, partner_id,application, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPtrnAppClstPlaybackContextQueryParams): Future[List[MinutelyUaPtrnAppClstPlaybackContextRow]] = {
        val rawQueryResult = MinutelyUaPtrnAppClstPlaybackContextTableAccessor.query(params.partnerIdList,params.applicationList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.application.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.playbackContext.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPtrnAppClstPlaybackContextRow): List[String] = {
        List(row.partnerId.toString,row.application.toString,row.metric.toString,row.minute.toString,row.playbackContext.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPtrnAppClstPlaybackContextRow): Int = row.metric
    }

case class MinutelyUaPtrnAppClstPlaybackContextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], applicationList : List[String], metricList : List[Int]) 