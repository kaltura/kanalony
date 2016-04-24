package kanalony.storage.logic.queries.model

/**
 * Created by elad.benedict on 2/16/2016.
 */
class DimensionEqualityConstraint[T](val values : Set[T]) extends IDimensionConstraint {
   override val constraint: QueryConstraint.Value = QueryConstraint.Equality

  def canEqual(a: Any) = a.isInstanceOf[DimensionEqualityConstraint[T]]

  override def equals(that: Any): Boolean =
    that match {
      case that: DimensionEqualityConstraint[T] =>
        that.canEqual(this) && this.values == that.values && this.constraint == that.constraint
      case _ => false
    }

  override def hashCode:Int = {
    values.hashCode() ^ constraint.hashCode()
  }

 }
