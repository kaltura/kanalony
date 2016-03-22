package kanalony.storage.generator

import kanalony.storage.generator.GenerationTemplates.dbClientFactoryTemplate

/**
 * Created by elad.benedict on 3/6/2016.
 */
object DbClientFactoryGenerator {
  def generate() = {
    dbClientFactoryTemplate.content.stripMargin
      .replace(dbClientFactoryTemplate.accessorObjectDefinitionsPlaceholder,
        TablesMetadata.metadata.map(tm => {
          val accessorName = TableAccessorGenerator.generateClassName(tm)
          s"object ${accessorName} extends ${accessorName} with connector.Connector"
        }).mkString("\n"))
  }
}
