package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggClstCountryCityQuery(accessor : IHourlyAggClstCountryCityTableAccessor) extends QueryBase[HourlyAggClstCountryCityQueryParams, HourlyAggClstCountryCityRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggClstCountryCityQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyAggClstCountryCityQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggClstCountryCityQueryParams): Future[List[HourlyAggClstCountryCityRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.years,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.country.toString,Dimensions.city.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggClstCountryCityRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.country.toString,row.city.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggClstCountryCityRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggClstCountryCityRow, timezoneOffsetFromUtc : Int) : HourlyAggClstCountryCityRow = {
        HourlyAggClstCountryCityRow(row.partnerId, row.metric, row.year, row.hour.plusHours(timezoneOffsetFromUtc), row.country, row.city, row.value)
      }

    }

case class HourlyAggClstCountryCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams