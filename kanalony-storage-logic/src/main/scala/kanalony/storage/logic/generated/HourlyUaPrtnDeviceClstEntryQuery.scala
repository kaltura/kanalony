package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnDeviceClstEntryQuery extends QueryBase[HourlyUaPrtnDeviceClstEntryQueryParams, HourlyUaPrtnDeviceClstEntryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnDeviceClstEntryQueryParams = {
        val (partner_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.device), params)
        HourlyUaPrtnDeviceClstEntryQueryParams(params.start, params.end, partner_id,device, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnDeviceClstEntryQueryParams): Future[List[HourlyUaPrtnDeviceClstEntryRow]] = {
        val rawQueryResult = HourlyUaPrtnDeviceClstEntryTableAccessor.query(params.partnerIdList,params.deviceList,params.years,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnDeviceClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.device.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnDeviceClstEntryRow): Int = row.metric
    }

case class HourlyUaPrtnDeviceClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], metricList : List[Int]) extends IYearlyPartitionedQueryParams