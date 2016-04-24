package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnCv1Cv2ClstEntryQuery(accessor : IHourlyAggPrtnCv1Cv2ClstEntryTableAccessor) extends QueryBase[HourlyAggPrtnCv1Cv2ClstEntryQueryParams, HourlyAggPrtnCv1Cv2ClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnCv1Cv2ClstEntryQueryParams = {
        val (partner_id,custom_var1,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.cf1,Dimensions.cf2), params)
        HourlyAggPrtnCv1Cv2ClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,custom_var1,custom_var2, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnCv1Cv2ClstEntryQueryParams): Future[List[HourlyAggPrtnCv1Cv2ClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.customVar1List,params.customVar2List,params.months,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf1.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnCv1Cv2ClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.customVar1.toString,row.customVar2.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyAggPrtnCv1Cv2ClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnCv1Cv2ClstEntryRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnCv1Cv2ClstEntryRow = {
        HourlyAggPrtnCv1Cv2ClstEntryRow(row.partnerId, row.customVar1, row.customVar2, row.month, row.metric, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.entryId, row.value)
      }

    }

case class HourlyAggPrtnCv1Cv2ClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], metricList : List[String]) extends IMonthlyPartitionedQueryParams