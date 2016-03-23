package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnReferrerClstEntryQuery extends QueryBase[HourlyUaPrtnReferrerClstEntryQueryParams, HourlyUaPrtnReferrerClstEntryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnReferrerClstEntryQueryParams = {
        val (partner_id,referrer) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.referrer), params)
        HourlyUaPrtnReferrerClstEntryQueryParams(params.start, params.end, partner_id,referrer, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnReferrerClstEntryQueryParams): Future[List[HourlyUaPrtnReferrerClstEntryRow]] = {
        val rawQueryResult = HourlyUaPrtnReferrerClstEntryTableAccessor.query(params.partnerIdList,params.referrerList,params.years,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.referrer.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnReferrerClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.referrer.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnReferrerClstEntryRow): Int = row.metric
    }

case class HourlyUaPrtnReferrerClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], referrerList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams