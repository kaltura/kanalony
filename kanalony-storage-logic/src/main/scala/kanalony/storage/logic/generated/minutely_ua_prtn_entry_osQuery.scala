package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_prtn_entry_osQuery extends QueryBase[minutely_ua_prtn_entry_osQueryParams, minutely_ua_prtn_entry_osRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_prtn_entry_osQueryParams = {
        val (partner_id,entry_id,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,Int]((Dimensions.partner,Dimensions.entry,Dimensions.operatingSystem), params)
        minutely_ua_prtn_entry_osQueryParams(params.start, params.end, partner_id,entry_id,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_prtn_entry_osQueryParams): Future[List[minutely_ua_prtn_entry_osRow]] = {
        val rawQueryResult = minutely_ua_prtn_entry_osTableAccessor.query(params.partner_id_list,params.entry_id_list,params.operating_system_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_prtn_entry_osRow): List[String] = {
        List(row.partner_id.toString,row.entry_id.toString,row.operating_system.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: minutely_ua_prtn_entry_osRow): Int = row.metric
    }

case class minutely_ua_prtn_entry_osQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], entry_id_list : List[String], operating_system_list : List[Int], metric_list : List[Int]) 