package queryGenerator

import kanalony.storage.generator.{ColumnNames, TableMetadata}
import kanalony.storage.logic.Dimensions

/**
 * Created by elad.benedict on 3/2/2016.
 */

class QueryTypeGenerator(tm : TableMetadata) {

  val extendedPartitionKeyColumnInformation = tm.primaryKey.pk.columns map { ColumnExtendedDefinition.convert(_) }
  val explicitNonMetricColumns = extendedPartitionKeyColumnInformation.filter(c => !(c.inferred) || c.name != ColumnNames.metric.toString)

  def getTableName : String = tm.tableName
  def getQueryName : String = s"${tm.tableName}Query"
  def getQueryParamsTypeName : String = ParamsTypeGenerator.getClassName(tm.tableName)
  def getSupportedMetricsProvider : String = "with UserActivityQuery" // This will require additional metadata and include logic when we support additional query kinds
  def getEqualityConstraintExplicitColumnsNotIncludingMetrics : String = explicitNonMetricColumns.map(c => c.name).mkString(",")
  def getEqualityConstraintExplicitColumnsNotIncludingMetricsEnumValues : String = explicitNonMetricColumns.map(c => Dimensions.fromColumnName(c.name)).mkString(",")
  def getTableQueryingImplementation : String = ???
  def getResultHeadersImplementation : String = ???
  def getResultRowImplementation : String = ???
  def getDimensionInformationImeplementation : String = ???
  def getMetricLocation : String  = ???
}

object QueryTypeGenerator {
  def apply(tm : TableMetadata) = new QueryTypeGenerator(tm)
}
