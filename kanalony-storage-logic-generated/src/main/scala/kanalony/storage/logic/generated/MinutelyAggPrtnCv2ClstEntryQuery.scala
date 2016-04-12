package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyAggPrtnCv2ClstEntryQuery(accessor : IMinutelyAggPrtnCv2ClstEntryTableAccessor) extends QueryBase[MinutelyAggPrtnCv2ClstEntryQueryParams, MinutelyAggPrtnCv2ClstEntryRow] with IUserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyAggPrtnCv2ClstEntryQueryParams = {
        val (partner_id,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf2), params)
        MinutelyAggPrtnCv2ClstEntryQueryParams(params.startUtc, params.endUtc, partner_id,custom_var2, params.metrics.map(_.name))
      }

      override def supportsUserDefinedMetrics = true

      private[logic] override def executeQuery(params: MinutelyAggPrtnCv2ClstEntryQueryParams): Future[List[MinutelyAggPrtnCv2ClstEntryRow]] = {
        accessor.query(params.partnerIdList,params.customVar2List,params.days,params.metricList,params.startTime,params.endTime)
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.minute.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: MinutelyAggPrtnCv2ClstEntryRow): List[String] = {
        List(row.partnerId.toString,row.customVar2.toString,row.metric.toString,row.minute.toString,row.entryId.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyAggPrtnCv2ClstEntryRow): String = row.metric

      override private[logic] def updateTimezoneOffset(row : MinutelyAggPrtnCv2ClstEntryRow, timezoneOffsetFromUtc : Int) : MinutelyAggPrtnCv2ClstEntryRow = {
        MinutelyAggPrtnCv2ClstEntryRow(row.partnerId, row.customVar2, row.day, row.metric, row.minute.plusHours(timezoneOffsetFromUtc), row.entryId, row.value)
      }

    }

case class MinutelyAggPrtnCv2ClstEntryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], customVar2List : List[String], metricList : List[String]) extends IDailyPartitionedQueryParams