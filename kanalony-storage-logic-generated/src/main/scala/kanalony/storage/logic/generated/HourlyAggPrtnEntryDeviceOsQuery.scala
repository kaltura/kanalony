package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnEntryDeviceOsQuery(accessor : IHourlyAggPrtnEntryDeviceOsTableAccessor) extends QueryBase[HourlyAggPrtnEntryDeviceOsQueryParams, HourlyAggPrtnEntryDeviceOsRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryDeviceOsQueryParams = {
        val (partner_id,entry_id,device,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int,Int]((Dimensions.partner,Dimensions.entry,Dimensions.device,Dimensions.operatingSystem), params)
        HourlyAggPrtnEntryDeviceOsQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,device,operating_system, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryDeviceOsQueryParams): Future[List[HourlyAggPrtnEntryDeviceOsRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.deviceList,params.operatingSystemList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.device.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryDeviceOsRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.device.toString,row.operatingSystem.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryDeviceOsRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnEntryDeviceOsRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnEntryDeviceOsRow = {
        HourlyAggPrtnEntryDeviceOsRow(row.partnerId, row.entryId, row.device, row.operatingSystem, row.metric, row.year, row.hour.plusHours(timezoneOffsetFromUtc), row.value)
      }

    }

case class HourlyAggPrtnEntryDeviceOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams