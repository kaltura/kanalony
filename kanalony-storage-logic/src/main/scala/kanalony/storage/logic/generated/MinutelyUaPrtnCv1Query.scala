package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnCv1Query extends QueryBase[MinutelyUaPrtnCv1QueryParams, MinutelyUaPrtnCv1Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnCv1QueryParams = {
        val (partner_id,custom_var1) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf1), params)
        MinutelyUaPrtnCv1QueryParams(params.start, params.end, partner_id,custom_var1, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnCv1QueryParams): Future[List[MinutelyUaPrtnCv1Row]] = {
        val rawQueryResult = MinutelyUaPrtnCv1TableAccessor.query(params.partnerIdList,params.customVar1List,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf1.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnCv1Row): List[String] = {
        List(row.partnerId.toString,row.customVar1.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyUaPrtnCv1Row): Int = row.metric
    }

case class MinutelyUaPrtnCv1QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar1List : List[String], metricList : List[Int]) 