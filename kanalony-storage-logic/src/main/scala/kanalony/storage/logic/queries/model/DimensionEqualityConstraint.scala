package kanalony.storage.logic.queries.model

/**
 * Created by elad.benedict on 2/16/2016.
 */
class DimensionEqualityConstraint[T](val values : Set[T]) extends IDimensionConstraint {
   override val constraint: QueryConstraint.Value = QueryConstraint.Equality
 }
