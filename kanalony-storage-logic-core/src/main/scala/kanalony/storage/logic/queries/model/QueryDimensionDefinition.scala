package kanalony.storage.logic.queries.model

import kanalony.storage.logic.Dimensions

case class QueryDimensionDefinition(dimension : Dimensions.Value, constraint : IDimensionConstraint, includeInResult : Boolean) extends IQueryDimensionDefinition
