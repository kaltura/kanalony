package kanalony.storage.generator

import com.google.common.base.CaseFormat

/**
 * Created by elad.benedict on 2/7/2016.
 */
class EntityClassGenerator(val tableMetadata: TableMetadata) {

  private def generateClassColumns(columns: List[IColumnDefinition]) : String = {
    columns.map { col => s"${EntityClassGenerator.getParamName(col.name)}:${col.typeName}" }.mkString(",\n")
  }

  def generate() = {
    var generatedContent = GenerationTemplates.packageTemplate.content + "\n";
    val entityTemplate = GenerationTemplates.entityClassTemplate;
    val entityTemplateContent = entityTemplate.content
    val columns = tableMetadata.columns
    val generatedColumnsDefs = generateClassColumns(columns)
    generatedContent = generatedContent + entityTemplateContent;
    generatedContent = generatedContent.replace(entityTemplate.classNamePlaceholder, EntityClassGenerator.getEntityName(tableMetadata))
    generatedContent = generatedContent.replace(entityTemplate.colDefsPlaceholder, generatedColumnsDefs)
    println(generatedContent)
    generatedContent
  }
}

object EntityClassGenerator {
  def getEntityName(tm : TableMetadata): String ={
    CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tm.tableName) + GenerationTemplates.entityClassTemplate.classNameSuffix
  }

  def getParamName(columnName : ColumnNames.Value): String = {
    CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName.toString)
  }
}