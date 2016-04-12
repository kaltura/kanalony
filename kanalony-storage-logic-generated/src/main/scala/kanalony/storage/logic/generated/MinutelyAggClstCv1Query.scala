package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggClstCv1Query(accessor : IMinutelyAggClstCv1TableAccessor) extends QueryBase[MinutelyAggClstCv1QueryParams, MinutelyAggClstCv1Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggClstCv1QueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        MinutelyAggClstCv1QueryParams(params.startUtc, params.endUtc, partner_id, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggClstCv1QueryParams): Future[List[MinutelyAggClstCv1Row]] = {
        accessor.query(params.partnerIdList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.cf1.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggClstCv1Row): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.minute.toString,row.customVar1.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyAggClstCv1Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggClstCv1Row, timezoneOffsetFromUtc : Int) : MinutelyAggClstCv1Row = {
        MinutelyAggClstCv1Row(row.partnerId, row.metric, row.day, row.minute.plusHours(timezoneOffsetFromUtc), row.customVar1, row.value)
      }

    }

case class MinutelyAggClstCv1QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[String]) extends IDailyPartitionedQueryParams