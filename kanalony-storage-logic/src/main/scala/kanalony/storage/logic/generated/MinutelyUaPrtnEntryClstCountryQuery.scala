package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryClstCountryQuery extends QueryBase[MinutelyUaPrtnEntryClstCountryQueryParams, MinutelyUaPrtnEntryClstCountryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryClstCountryQueryParams = {
        val (partner_id,entry_id) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.entry), params)
        MinutelyUaPrtnEntryClstCountryQueryParams(params.start, params.end, partner_id,entry_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryClstCountryQueryParams): Future[List[MinutelyUaPrtnEntryClstCountryRow]] = {
        val rawQueryResult = MinutelyUaPrtnEntryClstCountryTableAccessor.query(params.partnerIdList,params.entryIdList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.country.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryClstCountryRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.metric.toString,row.minute.toString,row.country.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryClstCountryRow): Int = row.metric
    }

case class MinutelyUaPrtnEntryClstCountryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int]) 