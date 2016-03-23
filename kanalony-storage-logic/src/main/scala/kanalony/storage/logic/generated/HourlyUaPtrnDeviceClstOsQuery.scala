package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPtrnDeviceClstOsQuery extends QueryBase[HourlyUaPtrnDeviceClstOsQueryParams, HourlyUaPtrnDeviceClstOsRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPtrnDeviceClstOsQueryParams = {
        val (partner_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.device), params)
        HourlyUaPtrnDeviceClstOsQueryParams(params.start, params.end, partner_id,device, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPtrnDeviceClstOsQueryParams): Future[List[HourlyUaPtrnDeviceClstOsRow]] = {
        val rawQueryResult = HourlyUaPtrnDeviceClstOsTableAccessor.query(params.partnerIdList,params.deviceList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.operatingSystem.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPtrnDeviceClstOsRow): List[String] = {
        List(row.partnerId.toString,row.device.toString,row.metric.toString,row.hour.toString,row.operatingSystem.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPtrnDeviceClstOsRow): Int = row.metric
    }

case class HourlyUaPtrnDeviceClstOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], metricList : List[Int]) extends IYearlyPartitionedQueryParams