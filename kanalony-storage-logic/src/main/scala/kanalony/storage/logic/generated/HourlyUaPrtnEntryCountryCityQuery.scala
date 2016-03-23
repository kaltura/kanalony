package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryCountryCityQuery extends QueryBase[HourlyUaPrtnEntryCountryCityQueryParams, HourlyUaPrtnEntryCountryCityRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryCountryCityQueryParams = {
        val (partner_id,entry_id,country,city) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.country,Dimensions.city), params)
        HourlyUaPrtnEntryCountryCityQueryParams(params.start, params.end, partner_id,entry_id,country,city, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryCountryCityQueryParams): Future[List[HourlyUaPrtnEntryCountryCityRow]] = {
        val rawQueryResult = HourlyUaPrtnEntryCountryCityTableAccessor.query(params.partnerIdList,params.entryIdList,params.countryList,params.cityList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.country.toString,Dimensions.city.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryCountryCityRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.country.toString,row.city.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryCountryCityRow): Int = row.metric
    }

case class HourlyUaPrtnEntryCountryCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], cityList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams