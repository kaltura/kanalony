package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryOsQuery extends QueryBase[MinutelyUaPrtnEntryOsQueryParams, MinutelyUaPrtnEntryOsRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryOsQueryParams = {
        val (partner_id,entry_id,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.entry,Dimensions.operatingSystem), params)
        MinutelyUaPrtnEntryOsQueryParams(params.start, params.end, partner_id,entry_id,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryOsQueryParams): Future[List[MinutelyUaPrtnEntryOsRow]] = {
        val rawQueryResult = MinutelyUaPrtnEntryOsTableAccessor.query(params.partnerIdList,params.entryIdList,params.operatingSystemList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryOsRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.operatingSystem.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryOsRow): Int = row.metric
    }

case class MinutelyUaPrtnEntryOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], metricList : List[Int]) 