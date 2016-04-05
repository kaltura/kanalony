package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnBrowserClstEntryQuery(accessor : IHourlyAggPrtnBrowserClstEntryTableAccessor) extends QueryBase[HourlyAggPrtnBrowserClstEntryQueryParams, HourlyAggPrtnBrowserClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnBrowserClstEntryQueryParams = {
        val (partner_id,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.browser), params)
        HourlyAggPrtnBrowserClstEntryQueryParams(params.start, params.end, partner_id,browser, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnBrowserClstEntryQueryParams): Future[List[HourlyAggPrtnBrowserClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.browserList,params.months,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnBrowserClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.browser.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnBrowserClstEntryRow): String = row.metric
    }

case class HourlyAggPrtnBrowserClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], browserList : List[Int], metricList : List[String]) extends IMonthlyPartitionedQueryParams