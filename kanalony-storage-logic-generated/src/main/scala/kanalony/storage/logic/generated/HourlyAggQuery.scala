package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggQuery(accessor : IHourlyAggTableAccessor) extends QueryBase[HourlyAggQueryParams, HourlyAggRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyAggQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggQueryParams): Future[List[HourlyAggRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 3

      override private[logic] def extractMetric(row: HourlyAggRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggRow, timezoneOffsetFromUtc : Int) : HourlyAggRow = {
        HourlyAggRow(row.partnerId, row.metric, row.year, row.hour.plusHours(timezoneOffsetFromUtc), row.value)
      }

    }

case class HourlyAggQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams