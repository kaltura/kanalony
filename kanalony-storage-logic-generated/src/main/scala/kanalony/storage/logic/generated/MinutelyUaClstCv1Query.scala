package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaClstCv1Query extends QueryBase[MinutelyUaClstCv1QueryParams, MinutelyUaClstCv1Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaClstCv1QueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        MinutelyUaClstCv1QueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaClstCv1QueryParams): Future[List[MinutelyUaClstCv1Row]] = {
        val rawQueryResult = MinutelyUaClstCv1TableAccessor.query(params.partnerIdList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.cf1.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaClstCv1Row): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.minute.toString,row.customVar1.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyUaClstCv1Row): Int = row.metric
    }

case class MinutelyUaClstCv1QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[Int]) 