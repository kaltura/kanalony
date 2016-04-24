package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnCountryCityQuery(accessor : IMinutelyAggPrtnCountryCityTableAccessor) extends QueryBase[MinutelyAggPrtnCountryCityQueryParams, MinutelyAggPrtnCountryCityRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnCountryCityQueryParams = {
        val (partner_id,country,city) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.country,Dimensions.city), params)
        MinutelyAggPrtnCountryCityQueryParams(params.startUtc, params.endUtc, partner_id,country,city, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnCountryCityQueryParams): Future[List[MinutelyAggPrtnCountryCityRow]] = {
        accessor.query(params.partnerIdList,params.countryList,params.cityList,params.metricList,params.days,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.city.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnCountryCityRow): List[String] = {
        List(row.partnerId.toString,row.country.toString,row.city.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.city, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnCountryCityRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnCountryCityRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnCountryCityRow = {
        MinutelyAggPrtnCountryCityRow(row.partnerId, row.country, row.city, row.metric, row.day, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.value)
      }

    }

case class MinutelyAggPrtnCountryCityQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams