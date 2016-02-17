package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/7/2016.
 */
class EntityClassGenerator(val tableMetadata: TableMetadata) {

  private def generateClassColumns(columns: List[IColumnDefinition]) : String = {
    columns.map { col => s"${col.name}:${col.typeName}" }.mkString(",\n")
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
    tm.tableName + GenerationTemplates.entityClassTemplate.classNameSuffix
  }
}