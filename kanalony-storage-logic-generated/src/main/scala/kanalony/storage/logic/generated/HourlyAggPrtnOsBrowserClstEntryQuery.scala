package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnOsBrowserClstEntryQuery(accessor : IHourlyAggPrtnOsBrowserClstEntryTableAccessor) extends QueryBase[HourlyAggPrtnOsBrowserClstEntryQueryParams, HourlyAggPrtnOsBrowserClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnOsBrowserClstEntryQueryParams = {
        val (partner_id,operating_system,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.operatingSystem,Dimensions.browser), params)
        HourlyAggPrtnOsBrowserClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,operating_system,browser, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnOsBrowserClstEntryQueryParams): Future[List[HourlyAggPrtnOsBrowserClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.operatingSystemList,params.browserList,params.months,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnOsBrowserClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.operatingSystem.toString,row.browser.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyAggPrtnOsBrowserClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnOsBrowserClstEntryRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnOsBrowserClstEntryRow = {
        HourlyAggPrtnOsBrowserClstEntryRow(row.partnerId, row.operatingSystem, row.browser, row.month, row.metric, row.hour.plusHours(timezoneOffsetFromUtc), row.entryId, row.value)
      }

    }

case class HourlyAggPrtnOsBrowserClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String]) extends IMonthlyPartitionedQueryParams