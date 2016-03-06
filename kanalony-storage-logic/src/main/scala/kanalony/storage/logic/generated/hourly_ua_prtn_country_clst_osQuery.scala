package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_prtn_country_clst_osQuery extends QueryBase[hourly_ua_prtn_country_clst_osQueryParams, hourly_ua_prtn_country_clst_osRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_prtn_country_clst_osQueryParams = {
        val (partner_id,country) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.country), params)
        hourly_ua_prtn_country_clst_osQueryParams(params.start, params.end, partner_id,country, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_prtn_country_clst_osQueryParams): Future[List[hourly_ua_prtn_country_clst_osRow]] = {
        val rawQueryResult = hourly_ua_prtn_country_clst_osTableAccessor.query(params.partner_id_list,params.country_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.country.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.operatingSystem.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_prtn_country_clst_osRow): List[String] = {
        List(row.partner_id.toString,row.country.toString,row.metric.toString,row.hour.toString,row.operating_system.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: hourly_ua_prtn_country_clst_osRow): Int = row.metric
    }

case class hourly_ua_prtn_country_clst_osQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], country_list : List[String], metric_list : List[Int]) extends IYearlyPartitionedQueryParams