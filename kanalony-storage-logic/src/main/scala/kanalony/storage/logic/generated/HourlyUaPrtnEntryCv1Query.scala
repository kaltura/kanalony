package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryCv1Query extends QueryBase[HourlyUaPrtnEntryCv1QueryParams, HourlyUaPrtnEntryCv1Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryCv1QueryParams = {
        val (partner_id,entry_id,custom_var1) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.cf1), params)
        HourlyUaPrtnEntryCv1QueryParams(params.start, params.end, partner_id,entry_id,custom_var1, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryCv1QueryParams): Future[List[HourlyUaPrtnEntryCv1Row]] = {
        val rawQueryResult = HourlyUaPrtnEntryCv1TableAccessor.query(params.partnerIdList,params.entryIdList,params.customVar1List,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.cf1.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryCv1Row): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.customVar1.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryCv1Row): Int = row.metric
    }

case class HourlyUaPrtnEntryCv1QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams