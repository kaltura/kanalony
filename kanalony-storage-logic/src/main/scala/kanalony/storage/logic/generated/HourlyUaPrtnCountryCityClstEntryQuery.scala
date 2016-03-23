package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnCountryCityClstEntryQuery extends QueryBase[HourlyUaPrtnCountryCityClstEntryQueryParams, HourlyUaPrtnCountryCityClstEntryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnCountryCityClstEntryQueryParams = {
        val (partner_id,country,city) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.country,Dimensions.city), params)
        HourlyUaPrtnCountryCityClstEntryQueryParams(params.start, params.end, partner_id,country,city, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnCountryCityClstEntryQueryParams): Future[List[HourlyUaPrtnCountryCityClstEntryRow]] = {
        val rawQueryResult = HourlyUaPrtnCountryCityClstEntryTableAccessor.query(params.partnerIdList,params.countryList,params.cityList,params.years,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.city.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnCountryCityClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.city.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyUaPrtnCountryCityClstEntryRow): Int = row.metric
    }

case class HourlyUaPrtnCountryCityClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams