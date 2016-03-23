package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryAppPlaybackContextQuery extends QueryBase[HourlyUaPrtnEntryAppPlaybackContextQueryParams, HourlyUaPrtnEntryAppPlaybackContextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryAppPlaybackContextQueryParams = {
        val (partner_id,entry_id,application,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.application,Dimensions.playbackContext), params)
        HourlyUaPrtnEntryAppPlaybackContextQueryParams(params.start, params.end, partner_id,entry_id,application,playback_context, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryAppPlaybackContextQueryParams): Future[List[HourlyUaPrtnEntryAppPlaybackContextRow]] = {
        val rawQueryResult = HourlyUaPrtnEntryAppPlaybackContextTableAccessor.query(params.partnerIdList,params.entryIdList,params.applicationList,params.playbackContextList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.application.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryAppPlaybackContextRow): List[String] = {
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

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryAppPlaybackContextRow): Int = row.metric
    }

case class HourlyUaPrtnEntryAppPlaybackContextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], playbackContextList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams