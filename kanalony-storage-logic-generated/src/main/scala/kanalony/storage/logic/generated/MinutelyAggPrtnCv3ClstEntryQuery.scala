package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.{DateTimeZone, DateTime}
    import scala.concurrent.Future

    class MinutelyAggPrtnCv3ClstEntryQuery(accessor : IMinutelyAggPrtnCv3ClstEntryTableAccessor) extends QueryBase[MinutelyAggPrtnCv3ClstEntryQueryParams, MinutelyAggPrtnCv3ClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnCv3ClstEntryQueryParams = {
        val (partner_id,custom_var3) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf3), params)
        MinutelyAggPrtnCv3ClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,custom_var3, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnCv3ClstEntryQueryParams): Future[List[MinutelyAggPrtnCv3ClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.customVar3List,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf3.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnCv3ClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.customVar3.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf3, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnCv3ClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnCv3ClstEntryRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnCv3ClstEntryRow = {
        MinutelyAggPrtnCv3ClstEntryRow(row.partnerId, row.customVar3, row.day, row.metric, row.minute.withZone(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetFromUtc / 60, timezoneOffsetFromUtc % 60)), row.entryId, row.value)
      }

    }

case class MinutelyAggPrtnCv3ClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar3List : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams