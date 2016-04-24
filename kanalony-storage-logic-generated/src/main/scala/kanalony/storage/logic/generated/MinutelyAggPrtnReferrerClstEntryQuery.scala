package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnReferrerClstEntryQuery(accessor : IMinutelyAggPrtnReferrerClstEntryTableAccessor) extends QueryBase[MinutelyAggPrtnReferrerClstEntryQueryParams, MinutelyAggPrtnReferrerClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnReferrerClstEntryQueryParams = {
        val (partner_id,referrer) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.referrer), params)
        MinutelyAggPrtnReferrerClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,referrer, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnReferrerClstEntryQueryParams): Future[List[MinutelyAggPrtnReferrerClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.referrerList,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.referrer.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnReferrerClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.referrer.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnReferrerClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnReferrerClstEntryRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnReferrerClstEntryRow = {
        MinutelyAggPrtnReferrerClstEntryRow(row.partnerId, row.referrer, row.day, row.metric, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.entryId, row.value)
      }

    }

case class MinutelyAggPrtnReferrerClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], referrerList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams