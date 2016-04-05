package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnCv2Query(accessor : IMinutelyAggPrtnCv2TableAccessor) extends QueryBase[MinutelyAggPrtnCv2QueryParams, MinutelyAggPrtnCv2Row] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnCv2QueryParams = {
        val (partner_id,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf2), params)
        MinutelyAggPrtnCv2QueryParams(params.start, params.end, partner_id,custom_var2, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnCv2QueryParams): Future[List[MinutelyAggPrtnCv2Row]] = {
        accessor.query(params.partnerIdList,params.customVar2List,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnCv2Row): List[String] = {
        List(row.partnerId.toString,row.customVar2.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyAggPrtnCv2Row): String = row.metric
    }

case class MinutelyAggPrtnCv2QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar2List : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams