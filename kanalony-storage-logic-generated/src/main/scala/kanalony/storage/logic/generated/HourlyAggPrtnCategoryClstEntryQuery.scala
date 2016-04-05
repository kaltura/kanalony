package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnCategoryClstEntryQuery(accessor : IHourlyAggPrtnCategoryClstEntryTableAccessor) extends QueryBase[HourlyAggPrtnCategoryClstEntryQueryParams, HourlyAggPrtnCategoryClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnCategoryClstEntryQueryParams = {
        val (partner_id,category) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.category), params)
        HourlyAggPrtnCategoryClstEntryQueryParams(params.start, params.end, partner_id,category, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnCategoryClstEntryQueryParams): Future[List[HourlyAggPrtnCategoryClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.categoryList,params.months,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.category.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnCategoryClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.category.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.category, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnCategoryClstEntryRow): String = row.metric
    }

case class HourlyAggPrtnCategoryClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], categoryList : List[String], metricList : List[String]) extends IMonthlyPartitionedQueryParams