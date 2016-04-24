package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnEntryCv1Cv2ClstCv3Query(accessor : IMinutelyAggPrtnEntryCv1Cv2ClstCv3TableAccessor) extends QueryBase[MinutelyAggPrtnEntryCv1Cv2ClstCv3QueryParams, MinutelyAggPrtnEntryCv1Cv2ClstCv3Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnEntryCv1Cv2ClstCv3QueryParams = {
        val (partner_id,entry_id,custom_var1,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.cf1,Dimensions.cf2), params)
        MinutelyAggPrtnEntryCv1Cv2ClstCv3QueryParams(params.startUtc, params.endUtc, partner_id,entry_id,custom_var1,custom_var2, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnEntryCv1Cv2ClstCv3QueryParams): Future[List[MinutelyAggPrtnEntryCv1Cv2ClstCv3Row]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.customVar1List,params.customVar2List,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.cf1.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.cf3.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnEntryCv1Cv2ClstCv3Row): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.customVar1.toString,row.customVar2.toString,row.metric.toString,row.minute.toString,row.customVar3.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf3, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 7

      override private[logic] def extractMetric(row: MinutelyAggPrtnEntryCv1Cv2ClstCv3Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnEntryCv1Cv2ClstCv3Row, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnEntryCv1Cv2ClstCv3Row = {
        MinutelyAggPrtnEntryCv1Cv2ClstCv3Row(row.partnerId, row.entryId, row.customVar1, row.customVar2, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.customVar3, row.value)
      }

    }

case class MinutelyAggPrtnEntryCv1Cv2ClstCv3QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams