package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnReferrerQuery(accessor : IMinutelyAggPrtnReferrerTableAccessor) extends QueryBase[MinutelyAggPrtnReferrerQueryParams, MinutelyAggPrtnReferrerRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnReferrerQueryParams = {
        val (partner_id,referrer) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.referrer), params)
        MinutelyAggPrtnReferrerQueryParams(params.start, params.end, partner_id,referrer, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnReferrerQueryParams): Future[List[MinutelyAggPrtnReferrerRow]] = {
        accessor.query(params.partnerIdList,params.referrerList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.referrer.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnReferrerRow): List[String] = {
        List(row.partnerId.toString,row.referrer.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyAggPrtnReferrerRow): String = row.metric
    }

case class MinutelyAggPrtnReferrerQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], referrerList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams