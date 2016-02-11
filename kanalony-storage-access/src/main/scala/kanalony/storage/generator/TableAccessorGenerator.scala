package kanalony.storage.generator

import kanalony.storage.generator.GenerationTemplates.{partitionKeyColumnDefinitionTemplate, clusteringKeyColumnDefinitionTemplate}

/**
 * Created by elad.benedict on 2/8/2016.
 */
class TableAccessorGenerator(val tm : TableMetadata) {

  def generateClassName(): String = {
    return TableAccessorGenerator.generateClassName(tm)
  }

  def generateEntityClassName(): String = {
    EntityClassGenerator.getEntityName(tm)
  }

  def generateValuePopulation(): String = {
    var res = "";
    val columns = tm.columns
    for (c <- columns){
      var valueAssignment = GenerationTemplates.valueDefinitionTemplate.content
      valueAssignment = valueAssignment.replace(GenerationTemplates.valueDefinitionTemplate.propertyNamePlaceholder, c.name)
      res = res + valueAssignment + "\n"
    }
    res
  }

  def generateTableName(): String = {
    tm.tableName
  }

  def generateColumnDefs[T <: IColumnDefinition](colDefs : List[T], templateCreator : T => IColumnDefinitionTemplate) : String = {
    var res = "";
    for (colDef <- colDefs){
      val colDefTemplate = templateCreator(colDef)
      val colDefResult = colDefTemplate.content.replace(colDefTemplate.propNamePlaceholder, colDef.name)
        .replace(colDefTemplate.typePlaceholder, colDef.typeName.toString)
      res = res + colDefResult + "\n"
    }
    res
  }

  def generatePartitionKeyDefs(colDefs : List[IColumnDefinition]) : String = {
    generateColumnDefs[IColumnDefinition](colDefs, c => new partitionKeyColumnDefinitionTemplate())
  }

  def generateAdditionalColumnsDefs(colDefs : List[IColumnDefinition]) : String = {
    generateColumnDefs[IColumnDefinition](colDefs, c => new GenerationTemplates.columnDefinitionTemplate())
  }

  def generateClusteringKeyDefs(colDefs : List[IClusteringColumnDefinition]) : String = {
    generateColumnDefs[IClusteringColumnDefinition](colDefs, c => new GenerationTemplates.clusteringKeyColumnDefinitionTemplate(c.orderBy))
  }

  def generateTableColDefs(): String = {
    var generatedContent = ""
    generatedContent = generatedContent + generatePartitionKeyDefs(tm.primaryKey.pk.columns);
    generatedContent = generatedContent + generateClusteringKeyDefs(tm.primaryKey.ck.columns);
    generatedContent = generatedContent + generateAdditionalColumnsDefs(tm.additionalColumns);
    generatedContent
  }

  def generateRowDecomposition(): String = {
    generateRowDecomposition(tm.columns)
  }

  def generateRowDecomposition(cols : List[IColumnDefinition]): String = cols match{
    case x::Nil => {
      GenerationTemplates.propertyDecompositionTemplate.content.replace(GenerationTemplates.propertyDecompositionTemplate.propNamePlaceholder, x.name)
    }
    case x::xs => {
      GenerationTemplates.propertyDecompositionTemplate.content.replace(GenerationTemplates.propertyDecompositionTemplate.propNamePlaceholder, x.name) +
        ", \n" + generateRowDecomposition(xs)
    }
  }

  def generateQueryMethods(pkSingleValueEquality : Boolean) : String = {
    val pkColumnQueryKind = if (pkSingleValueEquality) ColumnQueryKind.Equality else ColumnQueryKind.List
    val methodGenerator = new QueryMethodsGenerator(tm)
    val partitionKeyQueryColumnDefs = tm.primaryKey.pk.columns.map(x => new ColumnQueryDefinition(x.name, x.typeName, pkColumnQueryKind))
    var generatedQueries = methodGenerator.generateQueryMethod(partitionKeyQueryColumnDefs);

    val clusteringQueryColumnDefs = tm.primaryKey.ck.columns.map(x => new ColumnQueryDefinition(x.name, x.typeName, ColumnQueryKind.Range))
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
