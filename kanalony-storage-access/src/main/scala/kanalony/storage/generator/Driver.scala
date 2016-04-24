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
      val tableAccessorGenerator = new TableAccessorGenerator(tm)
      val generatedTableAcecssor = tableAccessorGenerator.generate().stripMargin
      val generatedInterface = tableAccessorGenerator.generateInterface().stripMargin
      fs.printToFile(new File(TableAccessorGenerator.generateClassName(tm) + ".scala"))(
        p => {
          p.write({
            generatedTableAcecssor + "\n\n" +
              generatedEntity + "\n\n" +
              generatedInterface
          })
        })
    })
    val dbClientFactory = DbClientFactoryGenerator.generate()
    fs.printToFile(new File("DbClientFactory.scala"))(p=>(p.write(dbClientFactory)))
  }
}
