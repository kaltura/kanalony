package kanalony.storage.logic

import kanalony.storage.logic.queries.model.QueryConstraint

/**
 * Created by elad.benedict on 2/16/2016.
 */
object QueryTableLocator {

  def tableAndQueryEqualityConstraintsMatch(tq: IQuery, queryParams: QueryParams) : Boolean = {
    val tableEqualityConstrainedDefs = tq.dimensionInformation.filter(_.constraint.constraint == QueryConstraint.Equality).map(_.dimension).toSet
    val queryEqualityConstraintDefs = queryParams.dimensionDefinitions.filter(_.constraint.constraint == QueryConstraint.Equality).map(_.dimension)
    queryEqualityConstraintDefs == tableEqualityConstrainedDefs
  }

  def tableSupportsAllQueryDimensions(tq: IQuery, queryParams: QueryParams) = {
    val queryDimnesions = queryParams.dimensionDefinitions.map(_.dimension)
    val tableDimensions = tq.dimensionInformation.map(_.dimension).toSet
    queryDimnesions subsetOf tableDimensions
  }

  def calcTableCompatibilityDistance(table: IQuery, queryParams : QueryParams) : Int = {
    var distance = Int.MaxValue
    if (!(table.supportedMetrics contains queryParams.metric)){
      return distance
    }
    if (!(tableAndQueryEqualityConstraintsMatch(table, queryParams)))
    {
      return distance
    }

    if (!(tableSupportsAllQueryDimensions(table, queryParams)))
    {
      return distance
    }

    distance = table.dimensionInformation.length - queryParams.dimensionDefinitions.size
    distance
  }

  def locate(queryParams: QueryParams) : Option[IQuery]  = {
    val sortedQueries = Queries.queries.map(tq => (tq , calcTableCompatibilityDistance(tq, queryParams))).sortBy(_._2)
    if (sortedQueries(0)._2 == Int.MaxValue) None else Some(sortedQueries(0)._1)
  }
}
