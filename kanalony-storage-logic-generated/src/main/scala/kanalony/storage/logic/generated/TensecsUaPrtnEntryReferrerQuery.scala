package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsUaPrtnEntryReferrerQuery extends QueryBase[TensecsUaPrtnEntryReferrerQueryParams, TensecsUaPrtnEntryReferrerRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsUaPrtnEntryReferrerQueryParams = {
        val (partner_id,entry_id,referrer) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.referrer), params)
        TensecsUaPrtnEntryReferrerQueryParams(params.start, params.end, partner_id,entry_id,referrer, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: TensecsUaPrtnEntryReferrerQueryParams): Future[List[TensecsUaPrtnEntryReferrerRow]] = {
        val rawQueryResult = TensecsUaPrtnEntryReferrerTableAccessor.query(params.partnerIdList,params.entryIdList,params.referrerList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.referrer.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,"value")
      }

      override protected def getResultRow(row: TensecsUaPrtnEntryReferrerRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.referrer.toString,row.metric.toString,row.tensecs.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: TensecsUaPrtnEntryReferrerRow): Int = row.metric
    }

case class TensecsUaPrtnEntryReferrerQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], referrerList : List[String], metricList : List[Int]) 