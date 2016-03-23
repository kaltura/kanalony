package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryAppClstPlaybackContextQuery extends QueryBase[MinutelyUaPrtnEntryAppClstPlaybackContextQueryParams, MinutelyUaPrtnEntryAppClstPlaybackContextRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryAppClstPlaybackContextQueryParams = {
        val (partner_id,entry_id,application) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.application), params)
        MinutelyUaPrtnEntryAppClstPlaybackContextQueryParams(params.start, params.end, partner_id,entry_id,application, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryAppClstPlaybackContextQueryParams): Future[List[MinutelyUaPrtnEntryAppClstPlaybackContextRow]] = {
        val rawQueryResult = MinutelyUaPrtnEntryAppClstPlaybackContextTableAccessor.query(params.partnerIdList,params.entryIdList,params.applicationList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.application.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.playbackContext.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryAppClstPlaybackContextRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.application.toString,row.metric.toString,row.minute.toString,row.playbackContext.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.playbackContext, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryAppClstPlaybackContextRow): Int = row.metric
    }

case class MinutelyUaPrtnEntryAppClstPlaybackContextQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[Int]) 