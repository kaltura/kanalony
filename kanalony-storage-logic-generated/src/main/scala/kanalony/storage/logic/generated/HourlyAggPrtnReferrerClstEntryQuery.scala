package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnReferrerClstEntryQuery(accessor : IHourlyAggPrtnReferrerClstEntryTableAccessor) extends QueryBase[HourlyAggPrtnReferrerClstEntryQueryParams, HourlyAggPrtnReferrerClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnReferrerClstEntryQueryParams = {
        val (partner_id,referrer) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.referrer), params)
        HourlyAggPrtnReferrerClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,referrer, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnReferrerClstEntryQueryParams): Future[List[HourlyAggPrtnReferrerClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.referrerList,params.months,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.referrer.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnReferrerClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.referrer.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnReferrerClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnReferrerClstEntryRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnReferrerClstEntryRow = {
        HourlyAggPrtnReferrerClstEntryRow(row.partnerId, row.referrer, row.month, row.metric, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.entryId, row.value)
      }

    }

case class HourlyAggPrtnReferrerClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], referrerList : List[String], metricList : List[String]) extends IMonthlyPartitionedQueryParams