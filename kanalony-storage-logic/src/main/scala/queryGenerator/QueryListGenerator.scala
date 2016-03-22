package queryGenerator

import kanalony.storage.generator.TablesMetadata

/**
 * Created by elad.benedict on 3/6/2016.
 */

object QueryListGenerator {
  def generate() = {
    val queryNames = TablesMetadata.metadata.map(tm => QueryTypeGenerator.getQueryName(tm))
    QueryTemplates.QueryListTemplate.content.stripMargin
      .replace(QueryTemplates.QueryListTemplate.queryObjectsPlaceholder, queryNames.mkString(",\n"))
      .replace(QueryTemplates.QueryListTemplate.queryObjectsDefinitionPlaceholder, queryNames.map(qn => s"object ${qn} extends ${qn}").mkString("\n"))
  }
}
