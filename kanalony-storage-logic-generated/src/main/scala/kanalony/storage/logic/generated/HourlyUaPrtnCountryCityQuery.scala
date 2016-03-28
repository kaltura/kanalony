package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnCountryCityQuery extends QueryBase[HourlyUaPrtnCountryCityQueryParams, HourlyUaPrtnCountryCityRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnCountryCityQueryParams = {
        val (partner_id,country,city) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.country,Dimensions.city), params)
        HourlyUaPrtnCountryCityQueryParams(params.start, params.end, partner_id,country,city, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnCountryCityQueryParams): Future[List[HourlyUaPrtnCountryCityRow]] = {
        val rawQueryResult = HourlyUaPrtnCountryCityTableAccessor.query(params.partnerIdList,params.countryList,params.cityList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.city.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnCountryCityRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.city.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnCountryCityRow): Int = row.metric
    }

case class HourlyUaPrtnCountryCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams