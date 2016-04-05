package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsAggClstEntryQuery(accessor : ITensecsAggClstEntryTableAccessor) extends QueryBase[TensecsAggClstEntryQueryParams, TensecsAggClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggClstEntryQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        TensecsAggClstEntryQueryParams(params.start, params.end, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggClstEntryQueryParams): Future[List[TensecsAggClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.tensecs.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: TensecsAggClstEntryRow): String = row.metric
    }

case class TensecsAggClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams