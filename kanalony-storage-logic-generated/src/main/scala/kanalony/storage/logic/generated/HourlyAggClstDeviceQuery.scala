package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggClstDeviceQuery(accessor : IHourlyAggClstDeviceTableAccessor) extends QueryBase[HourlyAggClstDeviceQueryParams, HourlyAggClstDeviceRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggClstDeviceQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyAggClstDeviceQueryParams(params.start, params.end, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggClstDeviceQueryParams): Future[List[HourlyAggClstDeviceRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.device.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggClstDeviceRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.device.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggClstDeviceRow): String = row.metric
    }

case class HourlyAggClstDeviceQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams