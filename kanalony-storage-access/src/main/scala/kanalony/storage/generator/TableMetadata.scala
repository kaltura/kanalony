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


class ColumnDefinition(val name : ColumnNames.Value, val typeName : ColumnType.Value) extends IColumnDefinition

object ColumnDefinition {
  def unapply(definition: IColumnDefinition): Option[(ColumnNames.Value,ColumnType.Value)] = {
    Some((definition.name, definition.typeName))
  }

  def apply(name : ColumnNames.Value, typeName : ColumnType.Value) = {
    new ColumnDefinition(name, typeName)
  }
}

class QueryableColumnDefinition(override val name : ColumnNames.Value, override val typeName : ColumnType.Value, val queryKind: ColumnQueryKind.Value) extends ColumnDefinition(name, typeName) with IQueryableColumnDefinition

class ClusteringColumnDefinition(colName : ColumnNames.Value, colType : ColumnType.Value, val orderBy : OrderBy.Value, val queryKind : ColumnQueryKind.Value = ColumnQueryKind.Range) extends ColumnDefinition(colName, colType) with IClusteringColumnDefinition

object ClusteringColumnDefinition {
  def apply(name : ColumnNames.Value, typeName : ColumnType.Value, orderBy : OrderBy.Value = OrderBy.Ascending) = {
    new ClusteringColumnDefinition(name, typeName, orderBy)
  }
}

case class ClusteringKey(columns : List[IClusteringColumnDefinition])

class PartitionKey(val columns : List[IColumnDefinition])

object PartitionKey {
  def apply(columns : List[IColumnDefinition]) = {
    new PartitionKey(columns)
  }
}

case class PrimaryKey(pk : PartitionKey, ck : ClusteringKey)

class TableMetadata(val tableName : String,
                    val primaryKey : PrimaryKey,
                    val additionalColumns : List[IColumnDefinition])
{
  val columns = primaryKey.pk.columns ::: primaryKey.ck.columns ::: additionalColumns
}

object TableMetadata {
  def apply(tableName : String,
            primaryKey : PrimaryKey,
            additionalColumns : List[IColumnDefinition]) = {
    new TableMetadata(tableName, primaryKey, additionalColumns)
  }
}
