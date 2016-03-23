package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnOsBrowserClstEntryQuery extends QueryBase[MinutelyUaPrtnOsBrowserClstEntryQueryParams, MinutelyUaPrtnOsBrowserClstEntryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnOsBrowserClstEntryQueryParams = {
        val (partner_id,operating_system,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.operatingSystem,Dimensions.browser), params)
        MinutelyUaPrtnOsBrowserClstEntryQueryParams(params.start, params.end, partner_id,operating_system,browser, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnOsBrowserClstEntryQueryParams): Future[List[MinutelyUaPrtnOsBrowserClstEntryRow]] = {
        val rawQueryResult = MinutelyUaPrtnOsBrowserClstEntryTableAccessor.query(params.partnerIdList,params.operatingSystemList,params.browserList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnOsBrowserClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.operatingSystem.toString,row.browser.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyUaPrtnOsBrowserClstEntryRow): Int = row.metric
    }

case class MinutelyUaPrtnOsBrowserClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], operatingSystemList : List[Int], browserList : List[Int], metricList : List[Int]) 