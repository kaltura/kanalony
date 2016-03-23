package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnCv1Cv2ClstCv3Query extends QueryBase[HourlyUaPrtnCv1Cv2ClstCv3QueryParams, HourlyUaPrtnCv1Cv2ClstCv3Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnCv1Cv2ClstCv3QueryParams = {
        val (partner_id,custom_var1,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.cf1,Dimensions.cf2), params)
        HourlyUaPrtnCv1Cv2ClstCv3QueryParams(params.start, params.end, partner_id,custom_var1,custom_var2, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnCv1Cv2ClstCv3QueryParams): Future[List[HourlyUaPrtnCv1Cv2ClstCv3Row]] = {
        val rawQueryResult = HourlyUaPrtnCv1Cv2ClstCv3TableAccessor.query(params.partnerIdList,params.customVar1List,params.customVar2List,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf1.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.cf3.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnCv1Cv2ClstCv3Row): List[String] = {
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

      override private[logic] def extractMetric(row: HourlyUaPrtnCv1Cv2ClstCv3Row): Int = row.metric
    }

case class HourlyUaPrtnCv1Cv2ClstCv3QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams