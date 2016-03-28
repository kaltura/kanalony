package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnCv3Query extends QueryBase[MinutelyUaPrtnCv3QueryParams, MinutelyUaPrtnCv3Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnCv3QueryParams = {
        val (partner_id,custom_var3) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf3), params)
        MinutelyUaPrtnCv3QueryParams(params.start, params.end, partner_id,custom_var3, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnCv3QueryParams): Future[List[MinutelyUaPrtnCv3Row]] = {
        val rawQueryResult = MinutelyUaPrtnCv3TableAccessor.query(params.partnerIdList,params.customVar3List,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf3.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnCv3Row): List[String] = {
        List(row.partnerId.toString,row.customVar3.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf3, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyUaPrtnCv3Row): Int = row.metric
    }

case class MinutelyUaPrtnCv3QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar3List : List[String], metricList : List[Int]) 