package kanalony.storage.generator
import java.io.File
import kanalony.storage.utils.fs

/**
 * Created by elad.benedict on 2/8/2016.
 */

object Driver {

  def main (args: Array[String]) {

    val tablesMetadata = TablesMetadata.metadata

    println(new File(".").getAbsoluteFile)

    tablesMetadata.foreach(tm => {
      val generatedEntity = new EntityClassGenerator(tm).generate().stripMargin
      val generatedTableAcecssor = new TableAccessorGenerator(tm).generate().stripMargin
      fs.printToFile(new File(EntityClassGenerator.getEntityName(tm) + ".scala"))(p=>(p.write(generatedEntity)))
      fs.printToFile(new File(TableAccessorGenerator.generateClassName(tm) + ".scala"))(p=>(p.write(generatedTableAcecssor)))
    })

    val dbClientFactory = DbClientFactoryGenerator.generate()
    fs.printToFile(new File("DbClientFactory.scala"))(p=>(p.write(dbClientFactory)))
  }
}
