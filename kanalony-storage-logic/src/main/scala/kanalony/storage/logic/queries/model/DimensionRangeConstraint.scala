package kanalony.storage.logic.queries.model

/**
 * Created by elad.benedict on 2/16/2016.
 */
class DimensionRangeConstraint[T](val start : T, val end : T) extends IDimensionConstraint {
   override val constraint: QueryConstraint.Value = QueryConstraint.Range
 }
