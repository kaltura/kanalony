package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class TensecsUaClstCountryQuery extends QueryBase[TensecsUaClstCountryQueryParams, TensecsUaClstCountryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): TensecsUaClstCountryQueryParams = {
        val (partner_id) = QueryParamsValidator.extractEqualityConstraintParams[Int]((Dimensions.partner), params)
        TensecsUaClstCountryQueryParams(params.start, params.end, partner_id, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: TensecsUaClstCountryQueryParams): Future[List[TensecsUaClstCountryRow]] = {
        val rawQueryResult = TensecsUaClstCountryTableAccessor.query(params.partnerIdList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.metric.toString,Dimensions.tenSeconds.toString,Dimensions.country.toString,"value")
      }

      override protected def getResultRow(row: TensecsUaClstCountryRow): List[String] = {
        List(row.partnerId.toString,row.metric.toString,row.tensecs.toString,row.country.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.tenSeconds, new DimensionConstraintDeclaration(QueryConstraint.Range)),
DimensionDefinition(Dimensions.country, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: TensecsUaClstCountryRow): Int = row.metric
    }

case class TensecsUaClstCountryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], metricList : List[Int]) 