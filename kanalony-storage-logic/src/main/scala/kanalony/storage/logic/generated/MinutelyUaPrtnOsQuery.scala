package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnOsQuery extends QueryBase[MinutelyUaPrtnOsQueryParams, MinutelyUaPrtnOsRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnOsQueryParams = {
        val (partner_id,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.operatingSystem), params)
        MinutelyUaPrtnOsQueryParams(params.start, params.end, partner_id,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnOsQueryParams): Future[List[MinutelyUaPrtnOsRow]] = {
        val rawQueryResult = MinutelyUaPrtnOsTableAccessor.query(params.partnerIdList,params.operatingSystemList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnOsRow): List[String] = {
        List(row.partnerId.toString,row.operatingSystem.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyUaPrtnOsRow): Int = row.metric
    }

case class MinutelyUaPrtnOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int]) 