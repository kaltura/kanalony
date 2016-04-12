package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnDeviceOsClstEntryQuery(accessor : IMinutelyAggPrtnDeviceOsClstEntryTableAccessor) extends QueryBase[MinutelyAggPrtnDeviceOsClstEntryQueryParams, MinutelyAggPrtnDeviceOsClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnDeviceOsClstEntryQueryParams = {
        val (partner_id,device,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.device,Dimensions.operatingSystem), params)
        MinutelyAggPrtnDeviceOsClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,device,operating_system, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnDeviceOsClstEntryQueryParams): Future[List[MinutelyAggPrtnDeviceOsClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.deviceList,params.operatingSystemList,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnDeviceOsClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.device.toString,row.operatingSystem.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyAggPrtnDeviceOsClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnDeviceOsClstEntryRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnDeviceOsClstEntryRow = {
        MinutelyAggPrtnDeviceOsClstEntryRow(row.partnerId, row.device, row.operatingSystem, row.day, row.metric, row.minute.plusHours(timezoneOffsetFromUtc), row.entryId, row.value)
      }

    }

case class MinutelyAggPrtnDeviceOsClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams