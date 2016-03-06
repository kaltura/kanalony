package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_prtn_osQuery extends QueryBase[minutely_ua_prtn_osQueryParams, minutely_ua_prtn_osRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_prtn_osQueryParams = {
        val (partner_id,operating_system) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.operatingSystem), params)
        minutely_ua_prtn_osQueryParams(params.start, params.end, partner_id,operating_system, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_prtn_osQueryParams): Future[List[minutely_ua_prtn_osRow]] = {
        val rawQueryResult = minutely_ua_prtn_osTableAccessor.query(params.partner_id_list,params.operating_system_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_prtn_osRow): List[String] = {
        List(row.partner_id.toString,row.operating_system.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: minutely_ua_prtn_osRow): Int = row.metric
    }

case class minutely_ua_prtn_osQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], operating_system_list : List[Int], metric_list : List[Int]) 