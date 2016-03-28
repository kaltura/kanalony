package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaClstCountryQuery extends QueryBase[HourlyUaClstCountryQueryParams, HourlyUaClstCountryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaClstCountryQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyUaClstCountryQueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaClstCountryQueryParams): Future[List[HourlyUaClstCountryRow]] = {
        val rawQueryResult = HourlyUaClstCountryTableAccessor.query(params.partnerIdList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.country.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaClstCountryRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.country.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyUaClstCountryRow): Int = row.metric
    }

case class HourlyUaClstCountryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[Int]) extends IYearlyPartitionedQueryParams