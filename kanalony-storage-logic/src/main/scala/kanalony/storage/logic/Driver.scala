package kanalony.storage.logic

import java.io.File
import kanalony.storage.utils.fs
import kanalony.storage.generator.TablesMetadata
import queryGenerator._

/**
 * Created by elad.benedict on 3/3/2016.
 */
object Driver {
  def main(args: Array[String]) {
    TablesMetadata.metadata.foreach(tm => {
      val paramType = ParamsTypeGenerator().generate(tm.tableName, tm.primaryKey.pk.columns map { ColumnExtendedDefinition.convert(_) })
      val queryType = QueryTypeGenerator(tm).generate()
      val result = queryType + "\n\n" + paramType
      fs.printToFile(new File(QueryTypeGenerator.getQueryName(tm) + ".scala"))(p=>(p.write(result)))
      val queryList = QueryListGenerator.generate()
      fs.printToFile(new File("Queries.scala"))(p=>(p.write(queryList)))
    })

  }
}
