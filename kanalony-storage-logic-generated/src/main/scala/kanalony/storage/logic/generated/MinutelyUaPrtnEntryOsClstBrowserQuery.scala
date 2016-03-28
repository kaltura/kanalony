package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryOsClstBrowserQuery extends QueryBase[MinutelyUaPrtnEntryOsClstBrowserQueryParams, MinutelyUaPrtnEntryOsClstBrowserRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryOsClstBrowserQueryParams = {
        val (partner_id,entry_id,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.entry,Dimensions.operatingSystem), params)
        MinutelyUaPrtnEntryOsClstBrowserQueryParams(params.start, params.end, partner_id,entry_id,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryOsClstBrowserQueryParams): Future[List[MinutelyUaPrtnEntryOsClstBrowserRow]] = {
        val rawQueryResult = MinutelyUaPrtnEntryOsClstBrowserTableAccessor.query(params.partnerIdList,params.entryIdList,params.operatingSystemList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.browser.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryOsClstBrowserRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.operatingSystem.toString,row.metric.toString,row.minute.toString,row.browser.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryOsClstBrowserRow): Int = row.metric
    }

case class MinutelyUaPrtnEntryOsClstBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], metricList : List[Int]) 