package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryClstCv1Query extends QueryBase[HourlyUaPrtnEntryClstCv1QueryParams, HourlyUaPrtnEntryClstCv1Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryClstCv1QueryParams = {
        val (partner_id,entry_id) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.entry), params)
        HourlyUaPrtnEntryClstCv1QueryParams(params.start, params.end, partner_id,entry_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryClstCv1QueryParams): Future[List[HourlyUaPrtnEntryClstCv1Row]] = {
        val rawQueryResult = HourlyUaPrtnEntryClstCv1TableAccessor.query(params.partnerIdList,params.entryIdList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.cf1.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryClstCv1Row): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.metric.toString,row.hour.toString,row.customVar1.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryClstCv1Row): Int = row.metric
    }

case class HourlyUaPrtnEntryClstCv1QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams