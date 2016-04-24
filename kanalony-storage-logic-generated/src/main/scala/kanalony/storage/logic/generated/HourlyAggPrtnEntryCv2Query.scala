package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnEntryCv2Query(accessor : IHourlyAggPrtnEntryCv2TableAccessor) extends QueryBase[HourlyAggPrtnEntryCv2QueryParams, HourlyAggPrtnEntryCv2Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryCv2QueryParams = {
        val (partner_id,entry_id,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.cf2), params)
        HourlyAggPrtnEntryCv2QueryParams(params.startUtc, params.endUtc, partner_id,entry_id,custom_var2, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryCv2QueryParams): Future[List[HourlyAggPrtnEntryCv2Row]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.customVar2List,params.years,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryCv2Row): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.customVar2.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryCv2Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnEntryCv2Row, timezoneOffsetFromUtc : Int) : HourlyAggPrtnEntryCv2Row = {
        HourlyAggPrtnEntryCv2Row(row.partnerId, row.entryId, row.customVar2, row.year, row.metric, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class HourlyAggPrtnEntryCv2QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], customVar2List : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams