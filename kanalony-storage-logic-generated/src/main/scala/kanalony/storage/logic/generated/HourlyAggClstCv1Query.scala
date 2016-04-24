package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggClstCv1Query(accessor : IHourlyAggClstCv1TableAccessor) extends QueryBase[HourlyAggClstCv1QueryParams, HourlyAggClstCv1Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggClstCv1QueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        HourlyAggClstCv1QueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggClstCv1QueryParams): Future[List[HourlyAggClstCv1Row]] = {
        accessor.query(params.partnerIdList,params.years,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.cf1.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggClstCv1Row): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.hour.toString,row.customVar1.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggClstCv1Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggClstCv1Row, timezoneOffsetFromUtc : Int) : HourlyAggClstCv1Row = {
        HourlyAggClstCv1Row(row.partnerId, row.year, row.metric, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.customVar1, row.value)
      }

    }

case class HourlyAggClstCv1QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IYearlyPartitionedQueryParams