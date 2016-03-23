package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryCv1Cv2Cv3Query extends QueryBase[MinutelyUaPrtnEntryCv1Cv2Cv3QueryParams, MinutelyUaPrtnEntryCv1Cv2Cv3Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryCv1Cv2Cv3QueryParams = {
        val (partner_id,entry_id,custom_var1,custom_var2,custom_var3) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.cf1,Dimensions.cf2,Dimensions.cf3), params)
        MinutelyUaPrtnEntryCv1Cv2Cv3QueryParams(params.start, params.end, partner_id,entry_id,custom_var1,custom_var2,custom_var3, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryCv1Cv2Cv3QueryParams): Future[List[MinutelyUaPrtnEntryCv1Cv2Cv3Row]] = {
        val rawQueryResult = MinutelyUaPrtnEntryCv1Cv2Cv3TableAccessor.query(params.partnerIdList,params.entryIdList,params.customVar1List,params.customVar2List,params.customVar3List,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.cf1.toString,Dimensions.cf2.toString,Dimensions.cf3.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryCv1Cv2Cv3Row): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.customVar1.toString,row.customVar2.toString,row.customVar3.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf3, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 7

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryCv1Cv2Cv3Row): Int = row.metric
    }

case class MinutelyUaPrtnEntryCv1Cv2Cv3QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], customVar3List : List[String], metricList : List[Int]) 