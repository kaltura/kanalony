package queryGenerator

import kanalony.storage.generator._

/**
 * Created by elad.benedict on 2/29/2016.
 */
class QueryGenerator {

  def generateParamsType(tableName: String, definitions: List[IColumnExtendedDefinition]) = ???

  def generateQueries(tableName : String) = {
    val tableMetadata = TablesMetadata.metadata.find(_.tableName eq tableName).get

    // Generate params Type
    val paramsType = ParamsTypeGenerator(tableMetadata.tableName, colsInfo.filter(c => !(c.inferred))).generate()

    // 2. Generate Query Type
    val queryType = QueryTypeGenerator(tableMetadata)


  }

}
