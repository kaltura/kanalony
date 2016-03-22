package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_prtn_categoryQuery extends QueryBase[hourly_ua_prtn_categoryQueryParams, hourly_ua_prtn_categoryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_prtn_categoryQueryParams = {
        val (partner_id,category) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.category), params)
        hourly_ua_prtn_categoryQueryParams(params.start, params.end, partner_id,category, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_prtn_categoryQueryParams): Future[List[hourly_ua_prtn_categoryRow]] = {
        val rawQueryResult = hourly_ua_prtn_categoryTableAccessor.query(params.partner_id_list,params.category_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.category.toString,Dimensions.metric.toString,Dimensions.hour.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_prtn_categoryRow): List[String] = {
        List(row.partner_id.toString,row.category.toString,row.metric.toString,row.hour.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.category, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: hourly_ua_prtn_categoryRow): Int = row.metric
    }

case class hourly_ua_prtn_categoryQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], category_list : List[String], metric_list : List[Int]) extends IYearlyPartitionedQueryParams