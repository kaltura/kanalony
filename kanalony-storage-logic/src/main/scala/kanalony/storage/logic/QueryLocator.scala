package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.DailyQuery
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

    if (queryParams.metrics.toSet.intersect(table.supportedMetrics).isEmpty ||
      !(constraintDiffSize == 0) ||
      !(tableSupportsAllQueryDimensions(table, queryParams))) {
      queryIncompatibleScoreThreshold + constraintDiffSize
    }
    else {
      table.dimensionInformation.length - queryParams.dimensionDefinitions.size
    }
  }

  def locate(queryParams: QueryParams) : List[(IQuery, List[Metrics.Value])] = {
    val requestedComputedDimensions = Dimensions.computedDimensions.intersect(queryParams.dimensionDefinitions.map(_.dimension).toSet)
    val requestedComputedMetrics = ComputedMetrics.values.intersect(queryParams.metrics.toSet)

    val computedMetricQueries = requestedComputedMetrics.toList.flatMap(ComputedMetrics.getQueryCreator(_)(queryParams))

    val nonComputedMetrics = (queryParams.metrics.toSet -- requestedComputedMetrics).toList
    // TODO: check if using a set instead of a list has a functional impact
    val updatedQueryParams = QueryParams(queryParams.dimensionDefinitions, nonComputedMetrics.toList, queryParams.start, queryParams.end)

    val nonComputedMetricQueries = if (requestedComputedDimensions.nonEmpty)
    {
      // Create a query for the computed dimension (locates relevant internal queries recursively)
      ComputedDimensions.getQueryCreator(requestedComputedDimensions.head)(updatedQueryParams)
    }
    else {
      locateDirectQueries(updatedQueryParams)
    }

    computedMetricQueries ::: nonComputedMetricQueries
  }

  def locateDirectQueries(queryParams: QueryParams) : List[(IQuery, List[Metrics.Value])] = {
    val sortedQueries = Queries.queries.map(tq => (tq , calcTableCompatibilityDistance(tq, queryParams))).sortBy(_._2)
    var remainingMetricsToCover = queryParams.metrics.toSet
    var result : List[(IQuery, List[Metrics.Value])] = List()
    sortedQueries
      .takeWhile(q => !remainingMetricsToCover.isEmpty && q._2 < queryIncompatibleScoreThreshold)
      .foreach(q => {
      val relevantMetricsSupportedByQuery = q._1.supportedMetrics.intersect(remainingMetricsToCover).toList
      if (!relevantMetricsSupportedByQuery.isEmpty)
      {
        result = result :+ (q._1, relevantMetricsSupportedByQuery)
        remainingMetricsToCover = remainingMetricsToCover -- relevantMetricsSupportedByQuery
      }
    })

    if (sortedQueries(0)._2 >= queryIncompatibleScoreThreshold){
      val message = "Query not supported. The most similar supported query requires equality constrains on dimensions " + sortedQueries(0)._1.dimensionInformation.filter(_.constraint.constraint == QueryConstraint.Equality).map(_.dimension)
      throw new QueryNotSupportedException(message)
    }

    if (!remainingMetricsToCover.isEmpty)
    {
      val message = s"Query not supported. No query could be found which supports the dimensions supplied and the following metrics: ${remainingMetricsToCover.mkString(",")}"
      throw new QueryNotSupportedException(message)
    }

    result
  }
}
