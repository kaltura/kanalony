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
          val interfaceName = TableAccessorGenerator.generateInterfaceName(tm)
          val privateMemberName = s"${accessorName}Obj"
          s"private object ${privateMemberName} extends ${accessorName} with connector.Connector with ${interfaceName}" + "\n" +
          s"lazy val ${accessorName} : ${interfaceName} = ${privateMemberName}"
        }).mkString("\n"))
  }
}
