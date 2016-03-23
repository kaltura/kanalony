package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnDeviceClstEntryQuery extends QueryBase[MinutelyUaPrtnDeviceClstEntryQueryParams, MinutelyUaPrtnDeviceClstEntryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnDeviceClstEntryQueryParams = {
        val (partner_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.device), params)
        MinutelyUaPrtnDeviceClstEntryQueryParams(params.start, params.end, partner_id,device, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnDeviceClstEntryQueryParams): Future[List[MinutelyUaPrtnDeviceClstEntryRow]] = {
        val rawQueryResult = MinutelyUaPrtnDeviceClstEntryTableAccessor.query(params.partnerIdList,params.deviceList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnDeviceClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.device.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPrtnDeviceClstEntryRow): Int = row.metric
    }

case class MinutelyUaPrtnDeviceClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], deviceList : List[Int], metricList : List[Int]) 