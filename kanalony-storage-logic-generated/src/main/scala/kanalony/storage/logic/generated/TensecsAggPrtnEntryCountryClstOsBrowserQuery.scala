package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggPrtnEntryCountryClstOsBrowserQuery(accessor : ITensecsAggPrtnEntryCountryClstOsBrowserTableAccessor) extends QueryBase[TensecsAggPrtnEntryCountryClstOsBrowserQueryParams, TensecsAggPrtnEntryCountryClstOsBrowserRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnEntryCountryClstOsBrowserQueryParams = {
        val (partner_id,entry_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.country), params)
        TensecsAggPrtnEntryCountryClstOsBrowserQueryParams(params.startUtc, params.endUtc, partner_id,entry_id,country, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnEntryCountryClstOsBrowserQueryParams): Future[List[TensecsAggPrtnEntryCountryClstOsBrowserRow]] = {
        accessor.query(params.partnerIdList,params.entryIdList,params.countryList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnEntryCountryClstOsBrowserRow): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.country.toString,row.metric.toString,row.tensecs.toString,row.operatingSystem.toString,row.browser.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 7

      override private[logic] def extractMetric(row: TensecsAggPrtnEntryCountryClstOsBrowserRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggPrtnEntryCountryClstOsBrowserRow, timezoneOffsetFromUtc : Int) : TensecsAggPrtnEntryCountryClstOsBrowserRow = {
        TensecsAggPrtnEntryCountryClstOsBrowserRow(row.partnerId, row.entryId, row.country, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.operatingSystem, row.browser, row.value)
      }

    }

case class TensecsAggPrtnEntryCountryClstOsBrowserQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams