package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class minutely_ua_prtn_os_browserQuery extends QueryBase[minutely_ua_prtn_os_browserQueryParams, minutely_ua_prtn_os_browserRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): minutely_ua_prtn_os_browserQueryParams = {
        val (partner_id,operating_system,browser) = QueryParamsValidator.extractEqualityConstraintParams[Int,Int,Int]((Dimensions.partner,Dimensions.operatingSystem,Dimensions.browser), params)
        minutely_ua_prtn_os_browserQueryParams(params.start, params.end, partner_id,operating_system,browser, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: minutely_ua_prtn_os_browserQueryParams): Future[List[minutely_ua_prtn_os_browserRow]] = {
        val rawQueryResult = minutely_ua_prtn_os_browserTableAccessor.query(params.partner_id_list,params.operating_system_list,params.browser_list,params.metric_list,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.operatingSystem.toString,Dimensions.browser.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: minutely_ua_prtn_os_browserRow): List[String] = {
        List(row.partner_id.toString,row.operating_system.toString,row.browser.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.operatingSystem, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.browser, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 5

      override private[logic] def extractMetric(row: minutely_ua_prtn_os_browserRow): Int = row.metric
    }

case class minutely_ua_prtn_os_browserQueryParams(startTime : DateTime, endTime : DateTime, partner_id_list : List[Int], operating_system_list : List[Int], browser_list : List[Int], metric_list : List[Int]) 