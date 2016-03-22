package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class hourly_ua_ptrn_device_clst_osQuery extends QueryBase[hourly_ua_ptrn_device_clst_osQueryParams, hourly_ua_ptrn_device_clst_osRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): hourly_ua_ptrn_device_clst_osQueryParams = {
        val (partner_id,device) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int]((Dimensions.partner,Dimensions.device), params)
        hourly_ua_ptrn_device_clst_osQueryParams(params.start, params.end, partner_id,device, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: hourly_ua_ptrn_device_clst_osQueryParams): Future[List[hourly_ua_ptrn_device_clst_osRow]] = {
        val rawQueryResult = hourly_ua_ptrn_device_clst_osTableAccessor.query(params.partner_id_list,params.device_list,params.metric_list,params.years,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.device.toString,Dimensions.metric.toString,Dimensions.hour.toString,Dimensions.operatingSystem.toString,"value")
      }

      override protected def getResultRow(row: hourly_ua_ptrn_device_clst_osRow): List[String] = {
        List(row.partner_id.toString,row.device.toString,row.metric.toString,row.hour.toString,row.operating_system.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.device, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.hour, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: hourly_ua_ptrn_device_clst_osRow): Int = row.metric
    }

case class hourly_ua_ptrn_device_clst_osQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], device_list : List[Int], metric_list : List[Int]) extends IYearlyPartitionedQueryParams