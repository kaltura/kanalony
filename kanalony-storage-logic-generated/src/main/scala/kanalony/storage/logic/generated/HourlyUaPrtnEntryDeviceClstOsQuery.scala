package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryDeviceClstOsQuery extends QueryBase[HourlyUaPrtnEntryDeviceClstOsQueryParams, HourlyUaPrtnEntryDeviceClstOsRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryDeviceClstOsQueryParams = {
        val (partner_id,entry_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.entry,Dimensions.device), params)
        HourlyUaPrtnEntryDeviceClstOsQueryParams(params.start, params.end, partner_id,entry_id,device, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryDeviceClstOsQueryParams): Future[List[HourlyUaPrtnEntryDeviceClstOsRow]] = {
        val rawQueryResult = HourlyUaPrtnEntryDeviceClstOsTableAccessor.query(params.partnerIdList,params.entryIdList,params.deviceList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.operatingSystem.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryDeviceClstOsRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.device.toString,row.metric.toString,row.hour.toString,row.operatingSystem.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryDeviceClstOsRow): Int = row.metric
    }

case class HourlyUaPrtnEntryDeviceClstOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], metricList : List[Int]) extends IYearlyPartitionedQueryParams