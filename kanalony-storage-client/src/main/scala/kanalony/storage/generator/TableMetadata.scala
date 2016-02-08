package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/7/2016.
 */

object ColumnType extends Enumeration {
  val String, Int, DateTime, Long = Value
}

object OrderBy extends Enumeration {
  val Ascending, Descending = Value
}

class ColumnDefinition(val name : String, val typeName : ColumnType.Value) extends IColumnDefinition

object ColumnDefinition {
  def apply(name:String, typeName : ColumnType.Value) = {
    new ColumnDefinition(name, typeName)
  }
}

class ClusteringColumnDefinition(colName : String, colType : ColumnType.Value, val orderBy : OrderBy.Value) extends ColumnDefinition(colName, colType) with IClusteringColumnDefinition

object ClusteringColumnDefinition {
  def apply(name:String, typeName : ColumnType.Value, orderBy : OrderBy.Value = OrderBy.Ascending) = {
    new ClusteringColumnDefinition(name, typeName, orderBy)
  }
}

case class ClusteringKey(columns : List[IClusteringColumnDefinition])

case class PartitionKey(columns : List[IColumnDefinition])

case class PrimaryKey(pk : PartitionKey, ck : ClusteringKey)

case class TableMetadata(tableName : String,
                         primaryKey : PrimaryKey,
                         additionalColumns : List[IColumnDefinition])
{
  val columns = primaryKey.pk.columns ::: primaryKey.ck.columns ::: additionalColumns
}
