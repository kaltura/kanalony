package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnOsQuery extends QueryBase[HourlyUaPrtnOsQueryParams, HourlyUaPrtnOsRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnOsQueryParams = {
        val (partner_id,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.operatingSystem), params)
        HourlyUaPrtnOsQueryParams(params.start, params.end, partner_id,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnOsQueryParams): Future[List[HourlyUaPrtnOsRow]] = {
        val rawQueryResult = HourlyUaPrtnOsTableAccessor.query(params.partnerIdList,params.operatingSystemList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnOsRow): List[String] = {
        List(row.partnerId.toString,row.operatingSystem.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyUaPrtnOsRow): Int = row.metric
    }

case class HourlyUaPrtnOsQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int]) extends IYearlyPartitionedQueryParams