package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnCv2Query extends QueryBase[HourlyUaPrtnCv2QueryParams, HourlyUaPrtnCv2Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnCv2QueryParams = {
        val (partner_id,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf2), params)
        HourlyUaPrtnCv2QueryParams(params.start, params.end, partner_id,custom_var2, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnCv2QueryParams): Future[List[HourlyUaPrtnCv2Row]] = {
        val rawQueryResult = HourlyUaPrtnCv2TableAccessor.query(params.partnerIdList,params.customVar2List,params.years,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnCv2Row): List[String] = {
        List(row.partnerId.toString,row.customVar2.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: HourlyUaPrtnCv2Row): Int = row.metric
    }

case class HourlyUaPrtnCv2QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar2List : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams