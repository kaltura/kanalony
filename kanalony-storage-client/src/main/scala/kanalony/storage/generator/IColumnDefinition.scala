package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/7/2016.
 */
trait IColumnDefinition {
  val name : String
  val typeName : ColumnType.Value
}

trait IClusteringColumnDefinition extends IColumnDefinition {
  val orderBy : OrderBy.Value
}
