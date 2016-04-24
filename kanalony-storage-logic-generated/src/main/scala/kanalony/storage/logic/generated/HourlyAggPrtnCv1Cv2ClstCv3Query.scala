package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class HourlyAggPrtnCv1Cv2ClstCv3Query(accessor : IHourlyAggPrtnCv1Cv2ClstCv3TableAccessor) extends QueryBase[HourlyAggPrtnCv1Cv2ClstCv3QueryParams, HourlyAggPrtnCv1Cv2ClstCv3Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnCv1Cv2ClstCv3QueryParams = {
        val (partner_id,custom_var1,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.cf1,Dimensions.cf2), params)
        HourlyAggPrtnCv1Cv2ClstCv3QueryParams(params.startUtc, params.endUtc, partner_id,custom_var1,custom_var2, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnCv1Cv2ClstCv3QueryParams): Future[List[HourlyAggPrtnCv1Cv2ClstCv3Row]] = {
        accessor.query(params.partnerIdList,params.customVar1List,params.customVar2List,params.years,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf1.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.cf3.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnCv1Cv2ClstCv3Row): List[String] = {
        List(row.partnerId.toString,row.customVar1.toString,row.customVar2.toString,row.metric.toString,row.hour.toString,row.customVar3.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf3, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: HourlyAggPrtnCv1Cv2ClstCv3Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnCv1Cv2ClstCv3Row, timezoneOffsetFromUtc : Int) : HourlyAggPrtnCv1Cv2ClstCv3Row = {
        HourlyAggPrtnCv1Cv2ClstCv3Row(row.partnerId, row.customVar1, row.customVar2, row.year, row.metric, row.hour.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.customVar3, row.value)
      }

    }

case class HourlyAggPrtnCv1Cv2ClstCv3QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams