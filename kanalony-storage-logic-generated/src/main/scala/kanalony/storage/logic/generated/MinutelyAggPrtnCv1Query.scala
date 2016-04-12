package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnCv1Query(accessor : IMinutelyAggPrtnCv1TableAccessor) extends QueryBase[MinutelyAggPrtnCv1QueryParams, MinutelyAggPrtnCv1Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnCv1QueryParams = {
        val (partner_id,custom_var1) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf1), params)
        MinutelyAggPrtnCv1QueryParams(params.startUtc, params.endUtc, partner_id,custom_var1, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnCv1QueryParams): Future[List[MinutelyAggPrtnCv1Row]] = {
        accessor.query(params.partnerIdList,params.customVar1List,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf1.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnCv1Row): List[String] = {
        List(row.partnerId.toString,row.customVar1.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyAggPrtnCv1Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnCv1Row, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnCv1Row = {
        MinutelyAggPrtnCv1Row(row.partnerId, row.customVar1, row.metric, row.day, row.minute.plusHours(timezoneOffsetFromUtc), row.value)
      }

    }

case class MinutelyAggPrtnCv1QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar1List : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams