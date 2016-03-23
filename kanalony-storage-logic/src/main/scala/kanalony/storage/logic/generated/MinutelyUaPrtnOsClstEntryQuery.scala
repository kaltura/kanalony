package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnOsClstEntryQuery extends QueryBase[MinutelyUaPrtnOsClstEntryQueryParams, MinutelyUaPrtnOsClstEntryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnOsClstEntryQueryParams = {
        val (partner_id,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.operatingSystem), params)
        MinutelyUaPrtnOsClstEntryQueryParams(params.start, params.end, partner_id,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnOsClstEntryQueryParams): Future[List[MinutelyUaPrtnOsClstEntryRow]] = {
        val rawQueryResult = MinutelyUaPrtnOsClstEntryTableAccessor.query(params.partnerIdList,params.operatingSystemList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnOsClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.operatingSystem.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPrtnOsClstEntryRow): Int = row.metric
    }

case class MinutelyUaPrtnOsClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int]) 