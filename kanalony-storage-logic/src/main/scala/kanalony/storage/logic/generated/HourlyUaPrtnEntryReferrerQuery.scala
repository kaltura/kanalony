package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryReferrerQuery extends QueryBase[HourlyUaPrtnEntryReferrerQueryParams, HourlyUaPrtnEntryReferrerRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryReferrerQueryParams = {
        val (partner_id,entry_id,referrer) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.referrer), params)
        HourlyUaPrtnEntryReferrerQueryParams(params.start, params.end, partner_id,entry_id,referrer, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryReferrerQueryParams): Future[List[HourlyUaPrtnEntryReferrerRow]] = {
        val rawQueryResult = HourlyUaPrtnEntryReferrerTableAccessor.query(params.partnerIdList,params.entryIdList,params.referrerList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.referrer.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryReferrerRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.referrer.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryReferrerRow): Int = row.metric
    }

case class HourlyUaPrtnEntryReferrerQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], referrerList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams