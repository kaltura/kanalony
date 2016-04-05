package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnCountryClstCityQuery(accessor : IHourlyAggPrtnCountryClstCityTableAccessor) extends QueryBase[HourlyAggPrtnCountryClstCityQueryParams, HourlyAggPrtnCountryClstCityRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnCountryClstCityQueryParams = {
        val (partner_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.country), params)
        HourlyAggPrtnCountryClstCityQueryParams(params.start, params.end, partner_id,country, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnCountryClstCityQueryParams): Future[List[HourlyAggPrtnCountryClstCityRow]] = {
        accessor.query(params.partnerIdList,params.countryList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.city.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnCountryClstCityRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.metric.toString,row.hour.toString,row.city.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnCountryClstCityRow): String = row.metric
    }

case class HourlyAggPrtnCountryClstCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams