package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnDeviceOsQuery extends QueryBase[HourlyUaPrtnDeviceOsQueryParams, HourlyUaPrtnDeviceOsRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnDeviceOsQueryParams = {
        val (partner_id,device,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.device,Dimensions.operatingSystem), params)
        HourlyUaPrtnDeviceOsQueryParams(params.start, params.end, partner_id,device,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnDeviceOsQueryParams): Future[List[HourlyUaPrtnDeviceOsRow]] = {
        val rawQueryResult = HourlyUaPrtnDeviceOsTableAccessor.query(params.partnerIdList,params.deviceList,params.operatingSystemList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnDeviceOsRow): List[String] = {
        List(row.partnerId.toString,row.device.toString,row.operatingSystem.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnDeviceOsRow): Int = row.metric
    }

case class HourlyUaPrtnDeviceOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[Int]) extends IYearlyPartitionedQueryParams