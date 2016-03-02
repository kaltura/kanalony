package queryGenerator

import kanalony.storage.generator._
import org.joda.time.DateTime

/**
 * Created by elad.benedict on 2/29/2016.
 */

object ParamsTypeGenerator {

  def apply(tableName : String, colDefs : List[IColumnExtendedDefinition]) = new ParamsTypeGenerator(tableName, colDefs)
  def getClassName(tableName : String) = s"${tableName}QueryParams"
}

case class ImplicitColumnInferrer(cols : List[IColumnExtendedDefinition]) {
  def implementation: String = {
    if (cols.isEmpty) {
      return ""
    }

    if (cols.length != 1 || cols.head.name != "year")
    {
      throw new IllegalArgumentException("Only 'year' is currently supported")
    }
    else
    {
      "extends IYearlyPartitionedQueryParams"
    }
  }
}

class ParamsTypeGenerator(tableName : String, colDefs : List[IColumnExtendedDefinition]) {
  val paramsClassTemplate = """import org.joda.time.DateTime
                              |case class %QUERY_PARAMS_CLASS_NAME%(startTime : DateTime, endTime : DateTime, %EQ_COL_DEFS%) %IMPLICIT_VALUES_INFERRER%
                              |""".stripMargin

  val colDefsPlaceholder = "%EQ_COL_DEFS%"
  val classNamePlaceholder = "%QUERY_PARAMS_CLASS_NAME%"
  val implicitValueImplementerPlaceholder = "%IMPLICIT_VALUES_INFERRER%"

  def generate() = {
    val explicitColumns = colDefs.filter(c => !(c.inferred)).map( c => new QueryableColumnDefinition(c.name, c.typeName, ColumnQueryKind.List))
    val implicitColumns = colDefs.filter(_.inferred)
    val generatedColDefs = QueryMethodsGenerator.generateColDefs(explicitColumns)

    paramsClassTemplate
      .replace(colDefsPlaceholder, generatedColDefs)
      .replace(classNamePlaceholder, ParamsTypeGenerator.getClassName(tableName))
      .replace(implicitValueImplementerPlaceholder, ImplicitColumnInferrer(implicitColumns).implementation)
  }
}
