package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryDeviceQuery extends QueryBase[MinutelyUaPrtnEntryDeviceQueryParams, MinutelyUaPrtnEntryDeviceRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryDeviceQueryParams = {
        val (partner_id,entry_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.entry,Dimensions.device), params)
        MinutelyUaPrtnEntryDeviceQueryParams(params.start, params.end, partner_id,entry_id,device, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryDeviceQueryParams): Future[List[MinutelyUaPrtnEntryDeviceRow]] = {
        val rawQueryResult = MinutelyUaPrtnEntryDeviceTableAccessor.query(params.partnerIdList,params.entryIdList,params.deviceList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryDeviceRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.device.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryDeviceRow): Int = row.metric
    }

case class MinutelyUaPrtnEntryDeviceQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], metricList : List[Int]) 