package kanalony.storage.logic.queries.model

/**
 * Created by elad.benedict on 2/16/2016.
 */
class DimensionConstraintDeclaration(val constraint: QueryConstraint.Value) extends IDimensionConstraint {
  def canEqual(a: Any) = a.isInstanceOf[DimensionConstraintDeclaration]

  override def equals(that: Any): Boolean =
    that match {
      case that: DimensionConstraintDeclaration =>
        that.canEqual(this) && this.constraint == that.constraint
      case _ => false
    }

  override def hashCode:Int = {
    constraint.hashCode
  }
}
