package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsUaPrtnEntryCountryQuery extends QueryBase[TensecsUaPrtnEntryCountryQueryParams, TensecsUaPrtnEntryCountryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsUaPrtnEntryCountryQueryParams = {
        val (partner_id,entry_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.country), params)
        TensecsUaPrtnEntryCountryQueryParams(params.start, params.end, partner_id,entry_id,country, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: TensecsUaPrtnEntryCountryQueryParams): Future[List[TensecsUaPrtnEntryCountryRow]] = {
        val rawQueryResult = TensecsUaPrtnEntryCountryTableAccessor.query(params.partnerIdList,params.entryIdList,params.countryList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,"value")
      }

      override protected def getResultRow(row: TensecsUaPrtnEntryCountryRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.country.toString,row.metric.toString,row.tensecs.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: TensecsUaPrtnEntryCountryRow): Int = row.metric
    }

case class TensecsUaPrtnEntryCountryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[Int]) 