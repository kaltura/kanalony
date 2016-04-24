package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggPrtnReferrerQuery(accessor : ITensecsAggPrtnReferrerTableAccessor) extends QueryBase[TensecsAggPrtnReferrerQueryParams, TensecsAggPrtnReferrerRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnReferrerQueryParams = {
        val (partner_id,referrer) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.referrer), params)
        TensecsAggPrtnReferrerQueryParams(params.startUtc, params.endUtc, partner_id,referrer, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnReferrerQueryParams): Future[List[TensecsAggPrtnReferrerRow]] = {
        accessor.query(params.partnerIdList,params.referrerList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.referrer.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnReferrerRow): List[String] = {
        List(row.partnerId.toString,row.referrer.toString,row.metric.toString,row.tensecs.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: TensecsAggPrtnReferrerRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggPrtnReferrerRow, timezoneOffsetFromUtc : Int) : TensecsAggPrtnReferrerRow = {
        TensecsAggPrtnReferrerRow(row.partnerId, row.referrer, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class TensecsAggPrtnReferrerQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], referrerList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams