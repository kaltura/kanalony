package kanalony.storage.logic

import kanalony.storage.logic.queries.model.QueryConstraint

/**
 * Created by elad.benedict on 2/16/2016.
 */

object QueryLocator {

  val queryIncompatibleScoreThreshold = 1000

  def tableAndQueryEqualityConstraintsSymmetricDifferenceSize(tq: IQuery, queryParams: QueryParams) : Int = {
    val tableEqualityConstrainedDefs = tq.dimensionInformation.filter(_.constraint.constraint == QueryConstraint.Equality).map(_.dimension).toSet
    val queryEqualityConstraintDefs = queryParams.dimensionDefinitions.filter(_.constraint.constraint == QueryConstraint.Equality).map(_.dimension).toSet
    val symmetricDifference = (queryEqualityConstraintDefs -- tableEqualityConstrainedDefs) ++ (tableEqualityConstrainedDefs -- queryEqualityConstraintDefs )
    symmetricDifference.size
  }

  def tableSupportsAllQueryDimensions(tq: IQuery, queryParams: QueryParams) = {
    val queryDimnesions = queryParams.dimensionDefinitions.map(_.dimension).toSet
    val tableDimensions = tq.dimensionInformation.map(_.dimension).toSet
    queryDimnesions subsetOf tableDimensions
  }

  def calcTableCompatibilityDistance(table: IQuery, queryParams : QueryParams) : Int = {

    val constraintDiffSize = tableAndQueryEqualityConstraintsSymmetricDifferenceSize(table, queryParams)

    if (!(queryParams.metrics.toSet subsetOf table.supportedMetrics) ||
        !(constraintDiffSize == 0) ||
        !(tableSupportsAllQueryDimensions(table, queryParams))) {
      queryIncompatibleScoreThreshold + constraintDiffSize
    }
    else {
      table.dimensionInformation.length - queryParams.dimensionDefinitions.size
    }
  }

  def locate(queryParams: QueryParams) : IQuery = {
    val sortedQueries = Queries.queries.map(tq => (tq , calcTableCompatibilityDistance(tq, queryParams))).sortBy(_._2)
    val potentialQuery = sortedQueries(0)
    if (potentialQuery._2 >= queryIncompatibleScoreThreshold){
      val message = "Query not supported. The most similar supported query requires equality constrains on dimensions " + potentialQuery._1.dimensionInformation.filter(_.constraint.constraint == QueryConstraint.Equality).map(_.dimension)
      throw new QueryNotSupportedException(message)
    }
    potentialQuery._1
  }
}
