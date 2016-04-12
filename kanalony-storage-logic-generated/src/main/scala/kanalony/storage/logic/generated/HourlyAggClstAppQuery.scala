package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggClstAppQuery(accessor : IHourlyAggClstAppTableAccessor) extends QueryBase[HourlyAggClstAppQueryParams, HourlyAggClstAppRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggClstAppQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyAggClstAppQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggClstAppQueryParams): Future[List[HourlyAggClstAppRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.application.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggClstAppRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.application.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggClstAppRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggClstAppRow, timezoneOffsetFromUtc : Int) : HourlyAggClstAppRow = {
        HourlyAggClstAppRow(row.partnerId, row.metric, row.year, row.hour.plusHours(timezoneOffsetFromUtc), row.application, row.value)
      }

    }

case class HourlyAggClstAppQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams