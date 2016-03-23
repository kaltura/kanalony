package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryAppPlaybackContextQuery extends QueryBase[MinutelyUaPrtnEntryAppPlaybackContextQueryParams, MinutelyUaPrtnEntryAppPlaybackContextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryAppPlaybackContextQueryParams = {
        val (partner_id,entry_id,application,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.application,Dimensions.playbackContext), params)
        MinutelyUaPrtnEntryAppPlaybackContextQueryParams(params.start, params.end, partner_id,entry_id,application,playback_context, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryAppPlaybackContextQueryParams): Future[List[MinutelyUaPrtnEntryAppPlaybackContextRow]] = {
        val rawQueryResult = MinutelyUaPrtnEntryAppPlaybackContextTableAccessor.query(params.partnerIdList,params.entryIdList,params.applicationList,params.playbackContextList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.application.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryAppPlaybackContextRow): List[String] = {
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

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryAppPlaybackContextRow): Int = row.metric
    }

case class MinutelyUaPrtnEntryAppPlaybackContextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], playbackContextList : List[String], metricList : List[Int]) 