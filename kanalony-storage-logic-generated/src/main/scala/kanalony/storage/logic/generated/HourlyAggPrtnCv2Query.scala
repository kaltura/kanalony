package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnCv2Query(accessor : IHourlyAggPrtnCv2TableAccessor) extends QueryBase[HourlyAggPrtnCv2QueryParams, HourlyAggPrtnCv2Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnCv2QueryParams = {
        val (partner_id,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf2), params)
        HourlyAggPrtnCv2QueryParams(params.startUtc, params.endUtc, partner_id,custom_var2, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnCv2QueryParams): Future[List[HourlyAggPrtnCv2Row]] = {
        accessor.query(params.partnerIdList,params.customVar2List,params.years,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnCv2Row): List[String] = {
        List(row.partnerId.toString,row.customVar2.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggPrtnCv2Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnCv2Row, timezoneOffsetFromUtc : Int) : HourlyAggPrtnCv2Row = {
        HourlyAggPrtnCv2Row(row.partnerId, row.customVar2, row.year, row.metric, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnCv2QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar2List : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams