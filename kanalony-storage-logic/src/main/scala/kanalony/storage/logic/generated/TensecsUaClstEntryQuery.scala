package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsUaClstEntryQuery extends QueryBase[TensecsUaClstEntryQueryParams, TensecsUaClstEntryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsUaClstEntryQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        TensecsUaClstEntryQueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: TensecsUaClstEntryQueryParams): Future[List[TensecsUaClstEntryRow]] = {
        val rawQueryResult = TensecsUaClstEntryTableAccessor.query(params.partnerIdList,params.days,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: TensecsUaClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.tensecs.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: TensecsUaClstEntryRow): Int = row.metric
    }

case class TensecsUaClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[Int]) extends IDailyPartitionedQueryParams