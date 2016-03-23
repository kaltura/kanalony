package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class HourlyUaPrtnEntryClstCv2Query extends QueryBase[HourlyUaPrtnEntryClstCv2QueryParams, HourlyUaPrtnEntryClstCv2Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): HourlyUaPrtnEntryClstCv2QueryParams = {
        val (partner_id,entry_id) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.entry), params)
        HourlyUaPrtnEntryClstCv2QueryParams(params.start, params.end, partner_id,entry_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: HourlyUaPrtnEntryClstCv2QueryParams): Future[List[HourlyUaPrtnEntryClstCv2Row]] = {
        val rawQueryResult = HourlyUaPrtnEntryClstCv2TableAccessor.query(params.partnerIdList,params.entryIdList,params.metricList,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.cf2.toString,"value")
      }

      override protected def getResultRow(row: HourlyUaPrtnEntryClstCv2Row): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.metric.toString,row.hour.toString,row.customVar2.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: HourlyUaPrtnEntryClstCv2Row): Int = row.metric
    }

case class HourlyUaPrtnEntryClstCv2QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int]) extends IYearlyPartitionedQueryParams