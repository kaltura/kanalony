package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggPrtnCategoryQuery(accessor : ITensecsAggPrtnCategoryTableAccessor) extends QueryBase[TensecsAggPrtnCategoryQueryParams, TensecsAggPrtnCategoryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnCategoryQueryParams = {
        val (partner_id,category) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.category), params)
        TensecsAggPrtnCategoryQueryParams(params.startUtc, params.endUtc, partner_id,category, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnCategoryQueryParams): Future[List[TensecsAggPrtnCategoryRow]] = {
        accessor.query(params.partnerIdList,params.categoryList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.category.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnCategoryRow): List[String] = {
        List(row.partnerId.toString,row.category.toString,row.metric.toString,row.tensecs.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.category, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: TensecsAggPrtnCategoryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggPrtnCategoryRow, timezoneOffsetFromUtc : Int) : TensecsAggPrtnCategoryRow = {
        TensecsAggPrtnCategoryRow(row.partnerId, row.category, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class TensecsAggPrtnCategoryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], categoryList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams