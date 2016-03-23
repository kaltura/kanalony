package kanalony.storage.logic.generated

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class MinutelyUaPrtnCategoryQuery extends QueryBase[MinutelyUaPrtnCategoryQueryParams, MinutelyUaPrtnCategoryRow] with UserActivityQuery {
      private[logic] override def extractParams(params: QueryParams): MinutelyUaPrtnCategoryQueryParams = {
        val (partner_id,category) = QueryParamsValidator.extractEqualityConstraintParams[Int,String]((Dimensions.partner,Dimensions.category), params)
        MinutelyUaPrtnCategoryQueryParams(params.start, params.end, partner_id,category, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: MinutelyUaPrtnCategoryQueryParams): Future[List[MinutelyUaPrtnCategoryRow]] = {
        val rawQueryResult = MinutelyUaPrtnCategoryTableAccessor.query(params.partnerIdList,params.categoryList,params.metricList,params.startTime,params.endTime)
      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
    rawQueryResult
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        List(Dimensions.partner.toString,Dimensions.category.toString,Dimensions.metric.toString,Dimensions.minute.toString,"value")
      }

      override protected def getResultRow(row: MinutelyUaPrtnCategoryRow): List[String] = {
        List(row.partnerId.toString,row.category.toString,row.metric.toString,row.minute.toString,row.value.toString)
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        List(DimensionDefinition(Dimensions.partner, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.category, new DimensionConstraintDeclaration(QueryConstraint.Equality)),
DimensionDefinition(Dimensions.minute, new DimensionConstraintDeclaration(QueryConstraint.Range)))
      }

      override def metricValueLocationIndex(): Int = 4

      override private[logic] def extractMetric(row: MinutelyUaPrtnCategoryRow): Int = row.metric
    }

case class MinutelyUaPrtnCategoryQueryParams(startTime : DateTime, endTime : DateTime, partnerIdList : List[Int], categoryList : List[String], metricList : List[Int]) 