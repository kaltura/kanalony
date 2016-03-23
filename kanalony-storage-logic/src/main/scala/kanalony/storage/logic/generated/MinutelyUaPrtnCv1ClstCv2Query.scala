package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnCv1ClstCv2Query extends QueryBase[MinutelyUaPrtnCv1ClstCv2QueryParams, MinutelyUaPrtnCv1ClstCv2Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnCv1ClstCv2QueryParams = {
        val (partner_id,custom_var1) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf1), params)
        MinutelyUaPrtnCv1ClstCv2QueryParams(params.start, params.end, partner_id,custom_var1, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnCv1ClstCv2QueryParams): Future[List[MinutelyUaPrtnCv1ClstCv2Row]] = {
        val rawQueryResult = MinutelyUaPrtnCv1ClstCv2TableAccessor.query(params.partnerIdList,params.customVar1List,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf1.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.cf2.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnCv1ClstCv2Row): List[String] = {
        List(row.partnerId.toString,row.customVar1.toString,row.metric.toString,row.minute.toString,row.customVar2.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPrtnCv1ClstCv2Row): Int = row.metric
    }

case class MinutelyUaPrtnCv1ClstCv2QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar1List : List[String], metricList : List[Int]) 