package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggPrtnEntryClstAppQuery(accessor : ITensecsAggPrtnEntryClstAppTableAccessor) extends QueryBase[TensecsAggPrtnEntryClstAppQueryParams, TensecsAggPrtnEntryClstAppRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnEntryClstAppQueryParams = {
        val (partner_id,entry_id) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.entry), params)
        TensecsAggPrtnEntryClstAppQueryParams(params.startUtc, params.endUtc, partner_id,entry_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnEntryClstAppQueryParams): Future[List[TensecsAggPrtnEntryClstAppRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.application.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnEntryClstAppRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.metric.toString,row.tensecs.toString,row.application.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: TensecsAggPrtnEntryClstAppRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggPrtnEntryClstAppRow, timezoneOffsetFromUtc : Int) : TensecsAggPrtnEntryClstAppRow = {
        TensecsAggPrtnEntryClstAppRow(row.partnerId, row.entryId, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.application, row.value)
      }

    }

case class TensecsAggPrtnEntryClstAppQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams