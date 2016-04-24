package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class TensecsAggPrtnCountryCityQuery(accessor : ITensecsAggPrtnCountryCityTableAccessor) extends QueryBase[TensecsAggPrtnCountryCityQueryParams, TensecsAggPrtnCountryCityRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsAggPrtnCountryCityQueryParams = {
        val (partner_id,country,city) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.country,Dimensions.city), params)
        TensecsAggPrtnCountryCityQueryParams(params.startUtc, params.endUtc, partner_id,country,city, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: TensecsAggPrtnCountryCityQueryParams): Future[List[TensecsAggPrtnCountryCityRow]] = {
        accessor.query(params.partnerIdList,params.countryList,params.cityList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.city.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,"value")
      }

      override protected def getResultRow(row: TensecsAggPrtnCountryCityRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.city.toString,row.metric.toString,row.tensecs.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: TensecsAggPrtnCountryCityRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : TensecsAggPrtnCountryCityRow, timezoneOffsetFromUtc : Int) : TensecsAggPrtnCountryCityRow = {
        TensecsAggPrtnCountryCityRow(row.partnerId, row.country, row.city, row.metric, row.day, row.tensecs.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class TensecsAggPrtnCountryCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams