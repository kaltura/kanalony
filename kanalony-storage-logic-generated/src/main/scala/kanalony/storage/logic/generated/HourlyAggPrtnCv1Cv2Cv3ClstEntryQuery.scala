package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnCv1Cv2Cv3ClstEntryQuery(accessor : IHourlyAggPrtnCv1Cv2Cv3ClstEntryTableAccessor) extends QueryBase[HourlyAggPrtnCv1Cv2Cv3ClstEntryQueryParams, HourlyAggPrtnCv1Cv2Cv3ClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnCv1Cv2Cv3ClstEntryQueryParams = {
        val (partner_id,custom_var1,custom_var2,custom_var3) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String]((Dimensions.partner,Dimensions.cf1,Dimensions.cf2,Dimensions.cf3), params)
        HourlyAggPrtnCv1Cv2Cv3ClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,custom_var1,custom_var2,custom_var3, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnCv1Cv2Cv3ClstEntryQueryParams): Future[List[HourlyAggPrtnCv1Cv2Cv3ClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.customVar1List,params.customVar2List,params.customVar3List,params.months,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf1.toString,Dimensions.cf2.toString,Dimensions.cf3.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnCv1Cv2Cv3ClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.customVar1.toString,row.customVar2.toString,row.customVar3.toString,row.metric.toString,row.hour.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf3, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 7

      override private[logic] def extractMetric(row: HourlyAggPrtnCv1Cv2Cv3ClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnCv1Cv2Cv3ClstEntryRow, timezoneOffsetFromUtc : Int) : HourlyAggPrtnCv1Cv2Cv3ClstEntryRow = {
        HourlyAggPrtnCv1Cv2Cv3ClstEntryRow(row.partnerId, row.customVar1, row.customVar2, row.customVar3, row.month, row.metric, row.hour.plusHours(timezoneOffsetFromUtc), row.entryId, row.value)
      }

    }

case class HourlyAggPrtnCv1Cv2Cv3ClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], customVar3List : List[String], metricList : List[String]) extends IMonthlyPartitionedQueryParams