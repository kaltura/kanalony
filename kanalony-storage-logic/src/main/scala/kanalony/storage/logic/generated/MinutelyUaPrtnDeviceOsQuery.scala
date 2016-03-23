package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnDeviceOsQuery extends QueryBase[MinutelyUaPrtnDeviceOsQueryParams, MinutelyUaPrtnDeviceOsRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnDeviceOsQueryParams = {
        val (partner_id,device,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.device,Dimensions.operatingSystem), params)
        MinutelyUaPrtnDeviceOsQueryParams(params.start, params.end, partner_id,device,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnDeviceOsQueryParams): Future[List[MinutelyUaPrtnDeviceOsRow]] = {
        val rawQueryResult = MinutelyUaPrtnDeviceOsTableAccessor.query(params.partnerIdList,params.deviceList,params.operatingSystemList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnDeviceOsRow): List[String] = {
        List(row.partnerId.toString,row.device.toString,row.operatingSystem.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPrtnDeviceOsRow): Int = row.metric
    }

case class MinutelyUaPrtnDeviceOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[Int]) 