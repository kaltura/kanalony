package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsAggQuery(accessor : ITensecsAggTableAccessor) extends QueryBase[TensecsAggQueryParams, TensecsAggRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        TensecsAggQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggQueryParams): Future[List[TensecsAggRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.tensecs.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 3

      override private[logic] def extractMetric(row: TensecsAggRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggRow, timezoneOffsetFromUtc : Int) : TensecsAggRow = {
        TensecsAggRow(row.partnerId, row.metric, row.day, row.tensecs.plusHours(timezoneOffsetFromUtc), row.value)
      }

    }

case class TensecsAggQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams