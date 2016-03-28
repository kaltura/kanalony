package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryPlaybackContextQuery extends QueryBase[MinutelyUaPrtnEntryPlaybackContextQueryParams, MinutelyUaPrtnEntryPlaybackContextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryPlaybackContextQueryParams = {
        val (partner_id,entry_id,playback_context) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.playbackContext), params)
        MinutelyUaPrtnEntryPlaybackContextQueryParams(params.start, params.end, partner_id,entry_id,playback_context, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryPlaybackContextQueryParams): Future[List[MinutelyUaPrtnEntryPlaybackContextRow]] = {
        val rawQueryResult = MinutelyUaPrtnEntryPlaybackContextTableAccessor.query(params.partnerIdList,params.entryIdList,params.playbackContextList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.playbackContext.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryPlaybackContextRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.playbackContext.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryPlaybackContextRow): Int = row.metric
    }

case class MinutelyUaPrtnEntryPlaybackContextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], playbackContextList : List[String], metricList : List[Int]) 