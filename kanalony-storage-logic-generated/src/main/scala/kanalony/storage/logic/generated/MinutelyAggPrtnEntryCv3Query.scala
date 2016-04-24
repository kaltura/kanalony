package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnEntryCv3Query(accessor : IMinutelyAggPrtnEntryCv3TableAccessor) extends QueryBase[MinutelyAggPrtnEntryCv3QueryParams, MinutelyAggPrtnEntryCv3Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnEntryCv3QueryParams = {
        val (partner_id,entry_id,custom_var3) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.cf3), params)
        MinutelyAggPrtnEntryCv3QueryParams(params.startUtc, params.endUtc, partner_id,entry_id,custom_var3, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnEntryCv3QueryParams): Future[List[MinutelyAggPrtnEntryCv3Row]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.customVar3List,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.cf3.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnEntryCv3Row): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.customVar3.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf3, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnEntryCv3Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnEntryCv3Row, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnEntryCv3Row = {
        MinutelyAggPrtnEntryCv3Row(row.partnerId, row.entryId, row.customVar3, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class MinutelyAggPrtnEntryCv3QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], customVar3List : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams