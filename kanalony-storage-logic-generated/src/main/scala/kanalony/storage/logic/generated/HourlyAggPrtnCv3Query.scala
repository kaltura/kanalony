package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyAggPrtnCv3Query(accessor : IHourlyAggPrtnCv3TableAccessor) extends QueryBase[HourlyAggPrtnCv3QueryParams, HourlyAggPrtnCv3Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyAggPrtnCv3QueryParams = {
        val (partner_id,custom_var3) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf3), params)
        HourlyAggPrtnCv3QueryParams(params.startUtc, params.endUtc, partner_id,custom_var3, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: HourlyAggPrtnCv3QueryParams): Future[List[HourlyAggPrtnCv3Row]] = {
        accessor.query(params.partnerIdList,params.customVar3List,params.years,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf3.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyAggPrtnCv3Row): List[String] = {
        List(row.partnerId.toString,row.customVar3.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf3, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyAggPrtnCv3Row): String = row.metric

      override private[logic] def updateTimezoneOffset(row : HourlyAggPrtnCv3Row, timezoneOffsetFromUtc : Int) : HourlyAggPrtnCv3Row = {
        HourlyAggPrtnCv3Row(row.partnerId, row.customVar3, row.year, row.metric, row.hour.plusHours(timezoneOffsetFromUtc), row.value)
      }

    }

case class HourlyAggPrtnCv3QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar3List : List[String], metricList : List[String]) extends IYearlyPartitionedQueryParams