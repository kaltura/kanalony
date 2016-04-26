package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggClstCountryQuery(accessor : ITensecsAggClstCountryTableAccessor) extends QueryBase[TensecsAggClstCountryQueryParams, TensecsAggClstCountryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggClstCountryQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        TensecsAggClstCountryQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggClstCountryQueryParams): Future[List[TensecsAggClstCountryRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.country.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggClstCountryRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.tensecs.toString,row.country.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: TensecsAggClstCountryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggClstCountryRow, timezoneOffsetFromUtc : Int) : TensecsAggClstCountryRow = {
        TensecsAggClstCountryRow(row.partnerId, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.country, row.value)
      }

    }

case class TensecsAggClstCountryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams