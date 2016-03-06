package queryGenerator

import kanalony.storage.generator._

/**
 * Created by elad.benedict on 2/25/2016.
 */

trait IColumnExtendedDefinition extends IColumnDefinition {
    val inferred = false
}

class ColumnExtendedDefinition(val name : ColumnNames.Value, val typeName : ColumnType.Value, val inPartitionKey : Boolean, val inClusteringKey : Boolean, override val inferred : Boolean = false) extends IColumnExtendedDefinition

object ColumnExtendedDefinition {
    def isColumnImplicit(name: String): Boolean = name match {
        case "year" => true
        case _ => false
    }

    def convert(colDef : IColumnDefinition) : IColumnExtendedDefinition = {
        new ColumnExtendedDefinition(colDef.name, colDef.typeName, colDef.inPartitionKey, colDef.inClusteringKey, isColumnImplicit(colDef.name.toString))
    }
}
