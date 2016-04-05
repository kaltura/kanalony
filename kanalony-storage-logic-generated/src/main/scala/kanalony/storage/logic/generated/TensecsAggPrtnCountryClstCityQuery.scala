package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsAggPrtnCountryClstCityQuery(accessor : ITensecsAggPrtnCountryClstCityTableAccessor) extends QueryBase[TensecsAggPrtnCountryClstCityQueryParams, TensecsAggPrtnCountryClstCityRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnCountryClstCityQueryParams = {
        val (partner_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.country), params)
        TensecsAggPrtnCountryClstCityQueryParams(params.start, params.end, partner_id,country, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnCountryClstCityQueryParams): Future[List[TensecsAggPrtnCountryClstCityRow]] = {
        accessor.query(params.partnerIdList,params.countryList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.city.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnCountryClstCityRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.metric.toString,row.tensecs.toString,row.city.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: TensecsAggPrtnCountryClstCityRow): String = row.metric
    }

case class TensecsAggPrtnCountryClstCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams