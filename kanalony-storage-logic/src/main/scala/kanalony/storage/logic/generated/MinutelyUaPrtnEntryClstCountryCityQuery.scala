package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryClstCountryCityQuery extends QueryBase[MinutelyUaPrtnEntryClstCountryCityQueryParams, MinutelyUaPrtnEntryClstCountryCityRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryClstCountryCityQueryParams = {
        val (partner_id,entry_id) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.entry), params)
        MinutelyUaPrtnEntryClstCountryCityQueryParams(params.start, params.end, partner_id,entry_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryClstCountryCityQueryParams): Future[List[MinutelyUaPrtnEntryClstCountryCityRow]] = {
        val rawQueryResult = MinutelyUaPrtnEntryClstCountryCityTableAccessor.query(params.partnerIdList,params.entryIdList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.country.toString,Dimensions.city.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryClstCountryCityRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.metric.toString,row.minute.toString,row.country.toString,row.city.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryClstCountryCityRow): Int = row.metric
    }

case class MinutelyUaPrtnEntryClstCountryCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int]) 