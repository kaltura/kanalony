package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnBrowserClstEntryQuery(accessor : IMinutelyAggPrtnBrowserClstEntryTableAccessor) extends QueryBase[MinutelyAggPrtnBrowserClstEntryQueryParams, MinutelyAggPrtnBrowserClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnBrowserClstEntryQueryParams = {
        val (partner_id,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.browser), params)
        MinutelyAggPrtnBrowserClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,browser, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnBrowserClstEntryQueryParams): Future[List[MinutelyAggPrtnBrowserClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.browserList,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnBrowserClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.browser.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnBrowserClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnBrowserClstEntryRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnBrowserClstEntryRow = {
        MinutelyAggPrtnBrowserClstEntryRow(row.partnerId, row.browser, row.day, row.metric, row.minute.plusHours(timezoneOffsetFromUtc), row.entryId, row.value)
      }

    }

case class MinutelyAggPrtnBrowserClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], browserList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams