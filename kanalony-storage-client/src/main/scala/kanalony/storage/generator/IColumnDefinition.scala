package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/7/2016.
 */
trait IColumnDefinition {
  val name : String
  val typeName : ColumnType.Value
}

object ColumnQueryKind extends Enumeration {
  val Equality, Range, List = Value
}

trait IColumnQueryDefinition extends IColumnDefinition{
    val queryKind : ColumnQueryKind.Value
}

trait IClusteringColumnDefinition extends IColumnDefinition {
  val orderBy : OrderBy.Value
}
