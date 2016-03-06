package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.api.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_prtn_cf1Query extends QueryBase[hourly_ua_prtn_cf1QueryParams, hourly_ua_prtn_cf1Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_prtn_cf1QueryParams = {
        val (partner_id,cf1) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.cf1), params)
        hourly_ua_prtn_cf1QueryParams(params.start, params.end, partner_id,cf1, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_prtn_cf1QueryParams): Future[List[hourly_ua_prtn_cf1Row]] = {
        val rawQueryResult = hourly_ua_prtn_cf1TableAccessor.query(params.partner_id_list,params.cf1_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.cf1.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_prtn_cf1Row): List[String] = {
        List(row.partner_id.toString,row.cf1.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: hourly_ua_prtn_cf1Row): Int = row.metric
    }

case class hourly_ua_prtn_cf1QueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], cf1_list : List[String], metric_list : List[Int]) extends IYearlyPartitionedQueryParams