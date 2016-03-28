package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnReferrerClstEntryQuery extends QueryBase[MinutelyUaPrtnReferrerClstEntryQueryParams, MinutelyUaPrtnReferrerClstEntryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnReferrerClstEntryQueryParams = {
        val (partner_id,referrer) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.referrer), params)
        MinutelyUaPrtnReferrerClstEntryQueryParams(params.start, params.end, partner_id,referrer, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnReferrerClstEntryQueryParams): Future[List[MinutelyUaPrtnReferrerClstEntryRow]] = {
        val rawQueryResult = MinutelyUaPrtnReferrerClstEntryTableAccessor.query(params.partnerIdList,params.referrerList,params.days,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.referrer.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnReferrerClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.referrer.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPrtnReferrerClstEntryRow): Int = row.metric
    }

case class MinutelyUaPrtnReferrerClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], referrerList : List[String], metricList : List[Int]) extends IDailyPartitionedQueryParams