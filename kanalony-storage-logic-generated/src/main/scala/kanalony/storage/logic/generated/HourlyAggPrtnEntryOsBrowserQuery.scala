package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnEntryOsBrowserQuery(accessor : IHourlyAggPrtnEntryOsBrowserTableAccessor) extends QueryBase[HourlyAggPrtnEntryOsBrowserQueryParams, HourlyAggPrtnEntryOsBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryOsBrowserQueryParams = {
        val (partner_id,entry_id,operating_system,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int,Int]((Dimensions.partner,Dimensions.entry,Dimensions.operatingSystem,Dimensions.browser), params)
        HourlyAggPrtnEntryOsBrowserQueryParams(params.start, params.end, partner_id,entry_id,operating_system,browser, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryOsBrowserQueryParams): Future[List[HourlyAggPrtnEntryOsBrowserRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.operatingSystemList,params.browserList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryOsBrowserRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.operatingSystem.toString,row.browser.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryOsBrowserRow): String = row.metric
    }

case class HourlyAggPrtnEntryOsBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams