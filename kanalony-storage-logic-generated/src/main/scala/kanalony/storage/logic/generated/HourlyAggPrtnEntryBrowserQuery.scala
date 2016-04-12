package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnEntryBrowserQuery(accessor : IHourlyAggPrtnEntryBrowserTableAccessor) extends QueryBase[HourlyAggPrtnEntryBrowserQueryParams, HourlyAggPrtnEntryBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryBrowserQueryParams = {
        val (partner_id,entry_id,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.entry,Dimensions.browser), params)
        HourlyAggPrtnEntryBrowserQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,browser, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryBrowserQueryParams): Future[List[HourlyAggPrtnEntryBrowserRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.browserList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryBrowserRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.browser.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryBrowserRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnEntryBrowserRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnEntryBrowserRow = {
        HourlyAggPrtnEntryBrowserRow(row.partnerId, row.entryId, row.browser, row.metric, row.year, row.hour.plusHours(timezoneOffsetFromUtc), row.value)
      }

    }

case class HourlyAggPrtnEntryBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], browserList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams