package kanalony.storage.logic

import kanalony.storage.logic.queries.model.QueryConstraint

/**
 * Created by elad.benedict on 2/16/2016.
 */
object QueryLocator {

  def tableAndQueryEqualityConstraintsMatch(tq: IQuery, queryParams: QueryParams) : Boolean = {
    val tableEqualityConstrainedDefs = tq.dimensionInformation.filter(_.constraint.constraint == QueryConstraint.Equality).map(_.dimension).toSet
    val queryEqualityConstraintDefs = queryParams.dimensionDefinitions.filter(_.constraint.constraint == QueryConstraint.Equality).map(_.dimension).toSet
    queryEqualityConstraintDefs == tableEqualityConstrainedDefs
  }

  def tableSupportsAllQueryDimensions(tq: IQuery, queryParams: QueryParams) = {
    val queryDimnesions = queryParams.dimensionDefinitions.map(_.dimension).toSet
    val tableDimensions = tq.dimensionInformation.map(_.dimension).toSet
    queryDimnesions subsetOf tableDimensions
  }

  def calcTableCompatibilityDistance(table: IQuery, queryParams : QueryParams) : Int = {
    if (!(table.supportedMetrics contains queryParams.metric) ||
        !(tableAndQueryEqualityConstraintsMatch(table, queryParams)) ||
        !(tableSupportsAllQueryDimensions(table, queryParams))) {
      Int.MaxValue
    }
    else {
      table.dimensionInformation.length - queryParams.dimensionDefinitions.size
    }
  }

  def locate(queryParams: QueryParams) : Option[IQuery]  = {
    val sortedQueries = Queries.queries.map(tq => (tq , calcTableCompatibilityDistance(tq, queryParams))).sortBy(_._2)
    if (sortedQueries(0)._2 == Int.MaxValue) None else Some(sortedQueries(0)._1)
  }
}
