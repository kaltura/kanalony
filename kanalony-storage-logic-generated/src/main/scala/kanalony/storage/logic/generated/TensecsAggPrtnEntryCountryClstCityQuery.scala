package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsAggPrtnEntryCountryClstCityQuery(accessor : ITensecsAggPrtnEntryCountryClstCityTableAccessor) extends QueryBase[TensecsAggPrtnEntryCountryClstCityQueryParams, TensecsAggPrtnEntryCountryClstCityRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnEntryCountryClstCityQueryParams = {
        val (partner_id,entry_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.country), params)
        TensecsAggPrtnEntryCountryClstCityQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,country, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnEntryCountryClstCityQueryParams): Future[List[TensecsAggPrtnEntryCountryClstCityRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.countryList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.city.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnEntryCountryClstCityRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.country.toString,row.metric.toString,row.tensecs.toString,row.city.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: TensecsAggPrtnEntryCountryClstCityRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggPrtnEntryCountryClstCityRow, timezoneOffsetFromUtc : Int) : TensecsAggPrtnEntryCountryClstCityRow = {
        TensecsAggPrtnEntryCountryClstCityRow(row.partnerId, row.entryId, row.country, row.metric, row.day, row.tensecs.plusHours(timezoneOffsetFromUtc), row.city, row.value)
      }

    }

case class TensecsAggPrtnEntryCountryClstCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams