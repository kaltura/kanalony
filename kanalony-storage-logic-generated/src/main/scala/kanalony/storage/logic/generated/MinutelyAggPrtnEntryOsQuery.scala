package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnEntryOsQuery(accessor : IMinutelyAggPrtnEntryOsTableAccessor) extends QueryBase[MinutelyAggPrtnEntryOsQueryParams, MinutelyAggPrtnEntryOsRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnEntryOsQueryParams = {
        val (partner_id,entry_id,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.entry,Dimensions.operatingSystem), params)
        MinutelyAggPrtnEntryOsQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,operating_system, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnEntryOsQueryParams): Future[List[MinutelyAggPrtnEntryOsRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.operatingSystemList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnEntryOsRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.operatingSystem.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnEntryOsRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnEntryOsRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnEntryOsRow = {
        MinutelyAggPrtnEntryOsRow(row.partnerId, row.entryId, row.operatingSystem, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class MinutelyAggPrtnEntryOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams