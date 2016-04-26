package queryGenerator

import kanalony.storage.generator.{TableAccessorGenerator, TablesMetadata}

/**
 * Created by elad.benedict on 3/6/2016.
 */

object QueryListGenerator {
  def generate() = {
    val queryNames = TablesMetadata.metadata.map(tm => QueryTypeGenerator.getQueryName(tm))
    QueryTemplates.QueryListTemplate.content.stripMargin
      .replace(QueryTemplates.QueryListTemplate.queryObjectsPlaceholder, queryNames.mkString(",\n"))
      .replace(QueryTemplates.QueryListTemplate.queryObjectsDefinitionPlaceholder, TablesMetadata.metadata.map(tm => s"object ${QueryTypeGenerator.getQueryName(tm)} extends ${QueryTypeGenerator.getQueryName(tm)}(DbClientFactory.${TableAccessorGenerator.generateClassName(tm)})").mkString("\n"))}
}
