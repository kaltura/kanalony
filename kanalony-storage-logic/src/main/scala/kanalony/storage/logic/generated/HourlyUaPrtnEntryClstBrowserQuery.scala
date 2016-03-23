package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryClstBrowserQuery extends QueryBase[HourlyUaPrtnEntryClstBrowserQueryParams, HourlyUaPrtnEntryClstBrowserRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryClstBrowserQueryParams = {
        val (partner_id,entry_id) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.entry), params)
        HourlyUaPrtnEntryClstBrowserQueryParams(params.start, params.end, partner_id,entry_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryClstBrowserQueryParams): Future[List[HourlyUaPrtnEntryClstBrowserRow]] = {
        val rawQueryResult = HourlyUaPrtnEntryClstBrowserTableAccessor.query(params.partnerIdList,params.entryIdList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.browser.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryClstBrowserRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.metric.toString,row.hour.toString,row.browser.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryClstBrowserRow): Int = row.metric
    }

case class HourlyUaPrtnEntryClstBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams