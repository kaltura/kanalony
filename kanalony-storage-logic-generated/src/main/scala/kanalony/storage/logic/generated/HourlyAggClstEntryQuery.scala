package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggClstEntryQuery(accessor : IHourlyAggClstEntryTableAccessor) extends QueryBase[HourlyAggClstEntryQueryParams, HourlyAggClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggClstEntryQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyAggClstEntryQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggClstEntryQueryParams): Future[List[HourlyAggClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.months,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggClstEntryRow, timezoneOffsetFromUtc : Int) : HourlyAggClstEntryRow = {
        HourlyAggClstEntryRow(row.partnerId, row.month, row.metric, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.entryId, row.value)
      }

    }

case class HourlyAggClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IMonthlyPartitionedQueryParams