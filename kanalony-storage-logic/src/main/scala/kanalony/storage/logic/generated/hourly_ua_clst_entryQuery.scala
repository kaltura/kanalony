package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_clst_entryQuery extends QueryBase[hourly_ua_clst_entryQueryParams, hourly_ua_clst_entryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_clst_entryQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        hourly_ua_clst_entryQueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_clst_entryQueryParams): Future[List[hourly_ua_clst_entryRow]] = {
        val rawQueryResult = hourly_ua_clst_entryTableAccessor.query(params.partner_id_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.entry.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_clst_entryRow): List[String] = {
        List(row.partner_id.toString,row.metric.toString,row.hour.toString,row.entry_id.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: hourly_ua_clst_entryRow): Int = row.metric
    }

case class hourly_ua_clst_entryQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], metric_list : List[Int]) extends IYearlyPartitionedQueryParams