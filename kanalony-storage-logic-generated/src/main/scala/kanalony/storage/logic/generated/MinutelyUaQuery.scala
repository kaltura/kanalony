package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaQuery extends QueryBase[MinutelyUaQueryParams, MinutelyUaRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        MinutelyUaQueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaQueryParams): Future[List[MinutelyUaRow]] = {
        val rawQueryResult = MinutelyUaTableAccessor.query(params.partnerIdList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 3

      override private[logic] def extractMetric(row: MinutelyUaRow): Int = row.metric
    }

case class MinutelyUaQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[Int]) 