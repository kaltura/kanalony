package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/7/2016.
 */
trait IColumnDefinition {
  val name : ColumnNames.Value
  val typeName : ColumnType.Value
  val inPartitionKey : Boolean
  val inClusteringKey : Boolean
}

object ColumnQueryKind extends Enumeration {
  val Equality, Range, List = Value
}

trait IQueryableColumnDefinition extends IColumnDefinition{
    val queryKind : ColumnQueryKind.Value
}

trait IClusteringColumnDefinition extends IColumnDefinition {
  val orderBy : OrderBy.Value
}
