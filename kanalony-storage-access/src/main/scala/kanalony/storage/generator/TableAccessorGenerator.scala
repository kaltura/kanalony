package kanalony.storage.generator

import kanalony.storage.generator.GenerationTemplates.{partitionKeyColumnDefinitionTemplate, clusteringKeyColumnDefinitionTemplate}

/**
 * Created by elad.benedict on 2/8/2016.
 */
class TableAccessorGenerator(val tm : TableMetadata) {

  private def generateClassName(): String = {
    return TableAccessorGenerator.generateClassName(tm)
  }

  private def generateEntityClassName(): String = {
    EntityClassGenerator.getEntityName(tm)
  }

  private def generateValuePopulation(): String = {
    var res = "";
    val columns = tm.columns
    for (c <- columns){
      var valueAssignment = GenerationTemplates.valueDefinitionTemplate.content
      valueAssignment = valueAssignment.replace(GenerationTemplates.valueDefinitionTemplate.propertyNamePlaceholder, c.name)
      res = res + valueAssignment + "\n"
    }
    res
  }

  private def generateTableName() = tm.tableName

  private def generateColumnDefs[T <: IColumnDefinition](colDefs : List[T], templateCreator : T => IColumnDefinitionTemplate) : String = {
    var res = "";
    for (colDef <- colDefs){
      val colDefTemplate = templateCreator(colDef)
      val colDefResult = colDefTemplate.content.replace(colDefTemplate.propNamePlaceholder, colDef.name)
        .replace(colDefTemplate.typePlaceholder, colDef.typeName.toString)
      res = res + colDefResult + "\n"
    }
    res
  }

  private def generatePartitionKeyDefs(colDefs : List[IColumnDefinition]) : String = {
    generateColumnDefs[IColumnDefinition](colDefs, c => new partitionKeyColumnDefinitionTemplate())
  }

  private def generateAdditionalColumnsDefs(colDefs : List[IColumnDefinition]) : String = {
    generateColumnDefs[IColumnDefinition](colDefs, c => new GenerationTemplates.columnDefinitionTemplate())
  }

  private def generateClusteringKeyDefs(colDefs : List[IClusteringColumnDefinition]) : String = {
    generateColumnDefs[IClusteringColumnDefinition](colDefs, c => new GenerationTemplates.clusteringKeyColumnDefinitionTemplate(c.orderBy))
  }

  private def generateTableColDefs(): String = {
    var generatedContent = ""
    generatedContent = generatedContent + generatePartitionKeyDefs(tm.primaryKey.pk.columns)
    generatedContent = generatedContent + generateClusteringKeyDefs(tm.primaryKey.ck.columns)
    generatedContent = generatedContent + generateAdditionalColumnsDefs(tm.additionalColumns)
    generatedContent
  }

  private def generateRowDecomposition() : String = generateRowDecomposition(tm.columns)

  private def generateRowDecomposition(cols : List[IColumnDefinition]) = {
    cols.map(x => GenerationTemplates.propertyDecompositionTemplate.content.replace(GenerationTemplates.propertyDecompositionTemplate.propNamePlaceholder, x.name))
        .mkString(", \n")
  }

  private def generateQueryMethods(pkSingleValueEquality : Boolean) : String = {
    val pkColumnQueryKind = if (pkSingleValueEquality) ColumnQueryKind.Equality else ColumnQueryKind.List
    val methodGenerator = new QueryMethodsGenerator(tm)
    val partitionKeyQueryColumnDefs = tm.primaryKey.pk.columns.map(x => new QueryableColumnDefinition(x.name, x.typeName, pkColumnQueryKind))
    var generatedQueries = methodGenerator.generateQueryMethod(partitionKeyQueryColumnDefs);

    val clusteringQueryColumnDefs = tm.primaryKey.ck.columns.map(x => new QueryableColumnDefinition(x.name, x.typeName, ColumnQueryKind.Range))
    for( i <- 1 to clusteringQueryColumnDefs.length) {
      generatedQueries = generatedQueries + "\n " + methodGenerator.generateQueryMethod(partitionKeyQueryColumnDefs ::: clusteringQueryColumnDefs.take(i));
    }

    generatedQueries
  }

  def generate() = {
    var generatedContent = GenerationTemplates.packageTemplate.content + "\n";
    val tableAccessorTemplate = GenerationTemplates.tableAccessorTemplate;
    generatedContent = generatedContent + tableAccessorTemplate.content

    generatedContent = generatedContent.replace(tableAccessorTemplate.classNamePlaceholder, generateClassName())
    generatedContent = generatedContent.replace(tableAccessorTemplate.entityClassNamePlaceholder, generateEntityClassName())
    generatedContent = generatedContent.replace(tableAccessorTemplate.valuePopulationPlaceholder, generateValuePopulation())
    generatedContent = generatedContent.replace(tableAccessorTemplate.rowDecompositionPlaceholder, generateRowDecomposition())
    generatedContent = generatedContent.replace(tableAccessorTemplate.tableColDefsPlaceholder, generateTableColDefs())
    generatedContent = generatedContent.replace(tableAccessorTemplate.tableNamePlaceholder, generateTableName())
    var queryMethods = generateQueryMethods(true)
    queryMethods = queryMethods + "\n" + generateQueryMethods(false)
    generatedContent = generatedContent.replace(tableAccessorTemplate.queryMethodsPlaceholder, queryMethods)

    generatedContent
  }
}

object TableAccessorGenerator{
  def generateClassName(tm : TableMetadata): String = {
    tm.tableName + "TableAccessor"
  }
}
