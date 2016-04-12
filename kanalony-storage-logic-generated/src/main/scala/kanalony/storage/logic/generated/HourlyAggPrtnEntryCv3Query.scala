package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnEntryCv3Query(accessor : IHourlyAggPrtnEntryCv3TableAccessor) extends QueryBase[HourlyAggPrtnEntryCv3QueryParams, HourlyAggPrtnEntryCv3Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryCv3QueryParams = {
        val (partner_id,entry_id,custom_var3) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.cf3), params)
        HourlyAggPrtnEntryCv3QueryParams(params.startUtc, params.endUtc, partner_id,entry_id,custom_var3, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryCv3QueryParams): Future[List[HourlyAggPrtnEntryCv3Row]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.customVar3List,params.years,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.cf3.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryCv3Row): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.customVar3.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf3, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryCv3Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnEntryCv3Row, timezoneOffsetFromUtc : Int) : HourlyAggPrtnEntryCv3Row = {
        HourlyAggPrtnEntryCv3Row(row.partnerId, row.entryId, row.customVar3, row.year, row.metric, row.hour.plusHours(timezoneOffsetFromUtc), row.value)
      }

    }

case class HourlyAggPrtnEntryCv3QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], customVar3List : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams