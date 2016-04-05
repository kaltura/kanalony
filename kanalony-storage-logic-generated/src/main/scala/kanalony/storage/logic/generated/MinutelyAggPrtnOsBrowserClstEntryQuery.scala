package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnOsBrowserClstEntryQuery(accessor : IMinutelyAggPrtnOsBrowserClstEntryTableAccessor) extends QueryBase[MinutelyAggPrtnOsBrowserClstEntryQueryParams, MinutelyAggPrtnOsBrowserClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnOsBrowserClstEntryQueryParams = {
        val (partner_id,operating_system,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.operatingSystem,Dimensions.browser), params)
        MinutelyAggPrtnOsBrowserClstEntryQueryParams(params.start, params.end, partner_id,operating_system,browser, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnOsBrowserClstEntryQueryParams): Future[List[MinutelyAggPrtnOsBrowserClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.operatingSystemList,params.browserList,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnOsBrowserClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.operatingSystem.toString,row.browser.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyAggPrtnOsBrowserClstEntryRow): String = row.metric
    }

case class MinutelyAggPrtnOsBrowserClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams