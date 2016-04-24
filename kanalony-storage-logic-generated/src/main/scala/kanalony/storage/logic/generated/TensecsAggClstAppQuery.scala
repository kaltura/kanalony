package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggClstAppQuery(accessor : ITensecsAggClstAppTableAccessor) extends QueryBase[TensecsAggClstAppQueryParams, TensecsAggClstAppRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggClstAppQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        TensecsAggClstAppQueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggClstAppQueryParams): Future[List[TensecsAggClstAppRow]] = {
        accessor.query(params.partnerIdList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.application.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggClstAppRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.tensecs.toString,row.application.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: TensecsAggClstAppRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggClstAppRow, timezoneOffsetFromUtc : Int) : TensecsAggClstAppRow = {
        TensecsAggClstAppRow(row.partnerId, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.application, row.value)
      }

    }

case class TensecsAggClstAppQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams