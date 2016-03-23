package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnEntryCv2Query extends QueryBase[MinutelyUaPrtnEntryCv2QueryParams, MinutelyUaPrtnEntryCv2Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnEntryCv2QueryParams = {
        val (partner_id,entry_id,custom_var2) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.cf2), params)
        MinutelyUaPrtnEntryCv2QueryParams(params.start, params.end, partner_id,entry_id,custom_var2, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnEntryCv2QueryParams): Future[List[MinutelyUaPrtnEntryCv2Row]] = {
        val rawQueryResult = MinutelyUaPrtnEntryCv2TableAccessor.query(params.partnerIdList,params.entryIdList,params.customVar2List,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.cf2.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnEntryCv2Row): List[String] = {
        List(row.partnerId.toString,row.entryId.toString,row.customVar2.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: MinutelyUaPrtnEntryCv2Row): Int = row.metric
    }

case class MinutelyUaPrtnEntryCv2QueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], entryIdList : List[String], customVar2List : List[String], metricList : List[Int]) 