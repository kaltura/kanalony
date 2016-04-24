package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnCv1Cv2Query(accessor : IMinutelyAggPrtnCv1Cv2TableAccessor) extends QueryBase[MinutelyAggPrtnCv1Cv2QueryParams, MinutelyAggPrtnCv1Cv2Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnCv1Cv2QueryParams = {
        val (partner_id,custom_var1,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.cf1,Dimensions.cf2), params)
        MinutelyAggPrtnCv1Cv2QueryParams(params.startUtc, params.endUtc, partner_id,custom_var1,custom_var2, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnCv1Cv2QueryParams): Future[List[MinutelyAggPrtnCv1Cv2Row]] = {
        accessor.query(params.partnerIdList,params.customVar1List,params.customVar2List,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf1.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnCv1Cv2Row): List[String] = {
        List(row.partnerId.toString,row.customVar1.toString,row.customVar2.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnCv1Cv2Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnCv1Cv2Row, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnCv1Cv2Row = {
        MinutelyAggPrtnCv1Cv2Row(row.partnerId, row.customVar1, row.customVar2, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class MinutelyAggPrtnCv1Cv2QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams