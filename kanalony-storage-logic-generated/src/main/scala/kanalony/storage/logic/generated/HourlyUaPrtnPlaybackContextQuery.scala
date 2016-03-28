package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnPlaybackContextQuery extends QueryBase[HourlyUaPrtnPlaybackContextQueryParams, HourlyUaPrtnPlaybackContextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnPlaybackContextQueryParams = {
        val (partner_id,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.playbackContext), params)
        HourlyUaPrtnPlaybackContextQueryParams(params.start, params.end, partner_id,playback_context, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnPlaybackContextQueryParams): Future[List[HourlyUaPrtnPlaybackContextRow]] = {
        val rawQueryResult = HourlyUaPrtnPlaybackContextTableAccessor.query(params.partnerIdList,params.playbackContextList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnPlaybackContextRow): List[String] = {
        List(row.partnerId.toString,row.playbackContext.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyUaPrtnPlaybackContextRow): Int = row.metric
    }

case class HourlyUaPrtnPlaybackContextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], playbackContextList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams