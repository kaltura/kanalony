package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_prtn_entry_cf1_clst_cf2Query extends QueryBase[hourly_ua_prtn_entry_cf1_clst_cf2QueryParams, hourly_ua_prtn_entry_cf1_clst_cf2Row] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_prtn_entry_cf1_clst_cf2QueryParams = {
        val (partner_id,entry_id,cf1) = QueryParamsValidator.extractEqualityConstraintParams[Int,String,String]((Dimensions.partner,Dimensions.entry,Dimensions.cf1), params)
        hourly_ua_prtn_entry_cf1_clst_cf2QueryParams(params.start, params.end, partner_id,entry_id,cf1, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_prtn_entry_cf1_clst_cf2QueryParams): Future[List[hourly_ua_prtn_entry_cf1_clst_cf2Row]] = {
        val rawQueryResult = hourly_ua_prtn_entry_cf1_clst_cf2TableAccessor.query(params.partner_id_list,params.entry_id_list,params.cf1_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.entry.toString,Dimensions.cf1.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.cf2.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_prtn_entry_cf1_clst_cf2Row): List[String] = {
        List(row.partner_id.toString,row.entry_id.toString,row.cf1.toString,row.metric.toString,row.hour.toString,row.cf2.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.entry, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.cf1, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.cf2, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 6

      override private[logic] def extractMetric(row: hourly_ua_prtn_entry_cf1_clst_cf2Row): Int = row.metric
    }

case class hourly_ua_prtn_entry_cf1_clst_cf2QueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], entry_id_list : List[String], cf1_list : List[String], metric_list : List[Int]) extends IYearlyPartitionedQueryParams