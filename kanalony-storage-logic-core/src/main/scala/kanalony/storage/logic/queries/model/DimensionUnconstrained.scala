package kanalony.storage.logic.queries.model

/**
 * Created by elad.benedict on 2/16/2016.
 */
class DimensionUnconstrained extends IDimensionConstraint {
   override val constraint: QueryConstraint.Value = QueryConstraint.Unconstrained

  def canEqual(a: Any) = a.isInstanceOf[DimensionUnconstrained]

  override def equals(that: Any): Boolean =
    that match {
      case that: DimensionUnconstrained =>
        that.canEqual(this) && this.constraint == that.constraint
      case _ => false
    }

  override def hashCode:Int = {
    constraint.hashCode()
  }
}
