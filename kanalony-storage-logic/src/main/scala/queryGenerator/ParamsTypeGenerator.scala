package queryGenerator

import kanalony.storage.generator._
import org.joda.time.DateTime

object ParamsTypeGenerator {

  def apply() = new ParamsTypeGenerator()
  def getClassName(tableName : String) = s"${tableName}QueryParams"

  def getParamName(colDef : IColumnExtendedDefinition) = {
    if (colDef.inferred)
    {
      RowBreakerFactory.getBreaker(colDef.name).partitionKeyParamName
    }
    else {
      QueryMethodsGenerator.getListParamName(colDef)
    }
  }
}

case class ImplicitColumnInferrer(cols : List[IColumnExtendedDefinition]) {
  def implementation: String = {
    if (cols.isEmpty) {
      return ""
    }

    if (cols.length != 1)
    {
      throw new IllegalArgumentException("Only a single inferred column is supported")
    }

    val implementingTraitName = RowBreakerFactory.getBreaker(cols.head.name).implementingTrait
    s"extends ${implementingTraitName}"
  }
}

class ParamsTypeGenerator() {
  val paramsClassTemplate = "case class %QUERY_PARAMS_CLASS_NAME%(startTime : DateTime, endTime : DateTime, %EQ_COL_DEFS%) %IMPLICIT_VALUES_INFERRER%"
  val colDefsPlaceholder = "%EQ_COL_DEFS%"
  val classNamePlaceholder = "%QUERY_PARAMS_CLASS_NAME%"
  val implicitValueImplementerPlaceholder = "%IMPLICIT_VALUES_INFERRER%"

  def generate(tableName : String, colDefs : List[IColumnExtendedDefinition]) = {
    val explicitColumns = colDefs.filter(c => !(c.inferred)).map( c => new QueryableColumnDefinition(c.name, c.typeName, ColumnQueryKind.List, c.inPartitionKey, c.inClusteringKey))
    val implicitColumns = colDefs.filter(_.inferred)
    val generatedColDefs = QueryMethodsGenerator.generateColDefs(explicitColumns)

    paramsClassTemplate
      .replace(colDefsPlaceholder, generatedColDefs)
      .replace(classNamePlaceholder, ParamsTypeGenerator.getClassName(tableName))
      .replace(implicitValueImplementerPlaceholder, ImplicitColumnInferrer(implicitColumns).implementation)
  }
}
