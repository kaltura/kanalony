package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnEntryReferrerQuery(accessor : IMinutelyAggPrtnEntryReferrerTableAccessor) extends QueryBase[MinutelyAggPrtnEntryReferrerQueryParams, MinutelyAggPrtnEntryReferrerRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnEntryReferrerQueryParams = {
        val (partner_id,entry_id,referrer) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.referrer), params)
        MinutelyAggPrtnEntryReferrerQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,referrer, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnEntryReferrerQueryParams): Future[List[MinutelyAggPrtnEntryReferrerRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.referrerList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.referrer.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnEntryReferrerRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.referrer.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.referrer, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnEntryReferrerRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnEntryReferrerRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnEntryReferrerRow = {
        MinutelyAggPrtnEntryReferrerRow(row.partnerId, row.entryId, row.referrer, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class MinutelyAggPrtnEntryReferrerQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], referrerList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams