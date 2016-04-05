package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnEntryCv1Cv2Cv3Query(accessor : IHourlyAggPrtnEntryCv1Cv2Cv3TableAccessor) extends QueryBase[HourlyAggPrtnEntryCv1Cv2Cv3QueryParams, HourlyAggPrtnEntryCv1Cv2Cv3Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnEntryCv1Cv2Cv3QueryParams = {
        val (partner_id,custom_var1,entry_id,custom_var2,custom_var3) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String,String]((Dimensions.partner,Dimensions.cf1,Dimensions.entry,Dimensions.cf2,Dimensions.cf3), params)
        HourlyAggPrtnEntryCv1Cv2Cv3QueryParams(params.start, params.end, partner_id,custom_var1,entry_id,custom_var2,custom_var3, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnEntryCv1Cv2Cv3QueryParams): Future[List[HourlyAggPrtnEntryCv1Cv2Cv3Row]] = {
        accessor.query(params.partnerIdList,params.customVar1List,params.entryIdList,params.customVar2List,params.customVar3List,params.years,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf1.toString,Dimensions.entry.toString,Dimensions.cf2.toString,Dimensions.cf3.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnEntryCv1Cv2Cv3Row): List[String] = {
        List(row.partnerId.toString,row.customVar1.toString,row.entryId.toString,row.customVar2.toString,row.customVar3.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf3, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 7

      override private[logic] def extractMetric(row: HourlyAggPrtnEntryCv1Cv2Cv3Row): String = row.metric
    }

case class HourlyAggPrtnEntryCv1Cv2Cv3QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar1List : List[String], entryIdList : List[String], customVar2List : List[String], customVar3List : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams