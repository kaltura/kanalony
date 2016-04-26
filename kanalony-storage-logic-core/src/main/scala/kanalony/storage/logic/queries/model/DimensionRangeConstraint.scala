package kanalony.storage.logic.queries.model

/**
 * Created by elad.benedict on 2/16/2016.
 */
class DimensionRangeConstraint[T](val start : T, val end : T) extends IDimensionConstraint {
   override val constraint: QueryConstraint.Value = QueryConstraint.Range

  def canEqual(a: Any) = a.isInstanceOf[DimensionRangeConstraint[T]]

  override def equals(that: Any): Boolean =
    that match {
      case that: DimensionRangeConstraint[T] =>
        that.canEqual(this) && this.start == that.start && this.end == that.end && this.constraint == that.constraint
      case _ => false
    }

  override def hashCode:Int = {
    start.hashCode() ^ end.hashCode() ^ constraint.hashCode()
  }
 }
