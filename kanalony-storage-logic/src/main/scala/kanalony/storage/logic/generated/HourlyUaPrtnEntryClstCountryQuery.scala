package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryClstCountryQuery extends QueryBase[HourlyUaPrtnEntryClstCountryQueryParams, HourlyUaPrtnEntryClstCountryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryClstCountryQueryParams = {
        val (partner_id,entry_id) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.entry), params)
        HourlyUaPrtnEntryClstCountryQueryParams(params.start, params.end, partner_id,entry_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryClstCountryQueryParams): Future[List[HourlyUaPrtnEntryClstCountryRow]] = {
        val rawQueryResult = HourlyUaPrtnEntryClstCountryTableAccessor.query(params.partnerIdList,params.entryIdList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.country.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryClstCountryRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.metric.toString,row.hour.toString,row.country.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryClstCountryRow): Int = row.metric
    }

case class HourlyUaPrtnEntryClstCountryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams