package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryCountryCityQuery extends QueryBase[MinutelyUaPrtnEntryCountryCityQueryParams, MinutelyUaPrtnEntryCountryCityRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryCountryCityQueryParams = {
        val (partner_id,entry_id,country,city) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.country,Dimensions.city), params)
        MinutelyUaPrtnEntryCountryCityQueryParams(params.start, params.end, partner_id,entry_id,country,city, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryCountryCityQueryParams): Future[List[MinutelyUaPrtnEntryCountryCityRow]] = {
        val rawQueryResult = MinutelyUaPrtnEntryCountryCityTableAccessor.query(params.partnerIdList,params.entryIdList,params.countryList,params.cityList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.country.toString,Dimensions.city.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryCountryCityRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.country.toString,row.city.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryCountryCityRow): Int = row.metric
    }

case class MinutelyUaPrtnEntryCountryCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], cityList : List[String], metricList : List[Int]) 