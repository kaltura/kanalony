package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryCountryClstCityQuery extends QueryBase[HourlyUaPrtnEntryCountryClstCityQueryParams, HourlyUaPrtnEntryCountryClstCityRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryCountryClstCityQueryParams = {
        val (partner_id,entry_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.country), params)
        HourlyUaPrtnEntryCountryClstCityQueryParams(params.start, params.end, partner_id,entry_id,country, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryCountryClstCityQueryParams): Future[List[HourlyUaPrtnEntryCountryClstCityRow]] = {
        val rawQueryResult = HourlyUaPrtnEntryCountryClstCityTableAccessor.query(params.partnerIdList,params.entryIdList,params.countryList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.city.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryCountryClstCityRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.country.toString,row.metric.toString,row.hour.toString,row.city.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryCountryClstCityRow): Int = row.metric
    }

case class HourlyUaPrtnEntryCountryClstCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams