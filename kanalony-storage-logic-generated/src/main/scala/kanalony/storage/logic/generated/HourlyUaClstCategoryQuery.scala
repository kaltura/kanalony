package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaClstCategoryQuery extends QueryBase[HourlyUaClstCategoryQueryParams, HourlyUaClstCategoryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaClstCategoryQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyUaClstCategoryQueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaClstCategoryQueryParams): Future[List[HourlyUaClstCategoryRow]] = {
        val rawQueryResult = HourlyUaClstCategoryTableAccessor.query(params.partnerIdList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.category.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaClstCategoryRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.category.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.category, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyUaClstCategoryRow): Int = row.metric
    }

case class HourlyUaClstCategoryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[Int]) extends IYearlyPartitionedQueryParams