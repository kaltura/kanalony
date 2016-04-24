package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggPrtnAppQuery(accessor : ITensecsAggPrtnAppTableAccessor) extends QueryBase[TensecsAggPrtnAppQueryParams, TensecsAggPrtnAppRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnAppQueryParams = {
        val (partner_id,application) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.application), params)
        TensecsAggPrtnAppQueryParams(params.startUtc, params.endUtc, partner_id,application, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnAppQueryParams): Future[List[TensecsAggPrtnAppRow]] = {
        accessor.query(params.partnerIdList,params.applicationList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.application.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnAppRow): List[String] = {
        List(row.partnerId.toString,row.application.toString,row.metric.toString,row.tensecs.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.application, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: TensecsAggPrtnAppRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggPrtnAppRow, timezoneOffsetFromUtc : Int) : TensecsAggPrtnAppRow = {
        TensecsAggPrtnAppRow(row.partnerId, row.application, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class TensecsAggPrtnAppQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], applicationList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams