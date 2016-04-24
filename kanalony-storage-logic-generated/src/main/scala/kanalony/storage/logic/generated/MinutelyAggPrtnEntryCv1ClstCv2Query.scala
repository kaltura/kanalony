package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnEntryCv1ClstCv2Query(accessor : IMinutelyAggPrtnEntryCv1ClstCv2TableAccessor) extends QueryBase[MinutelyAggPrtnEntryCv1ClstCv2QueryParams, MinutelyAggPrtnEntryCv1ClstCv2Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnEntryCv1ClstCv2QueryParams = {
        val (partner_id,entry_id,custom_var1) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.cf1), params)
        MinutelyAggPrtnEntryCv1ClstCv2QueryParams(params.startUtc, params.endUtc, partner_id,entry_id,custom_var1, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnEntryCv1ClstCv2QueryParams): Future[List[MinutelyAggPrtnEntryCv1ClstCv2Row]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.customVar1List,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.cf1.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.cf2.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnEntryCv1ClstCv2Row): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.customVar1.toString,row.metric.toString,row.minute.toString,row.customVar2.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyAggPrtnEntryCv1ClstCv2Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnEntryCv1ClstCv2Row, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnEntryCv1ClstCv2Row = {
        MinutelyAggPrtnEntryCv1ClstCv2Row(row.partnerId, row.entryId, row.customVar1, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.customVar2, row.value)
      }

    }

case class MinutelyAggPrtnEntryCv1ClstCv2QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams