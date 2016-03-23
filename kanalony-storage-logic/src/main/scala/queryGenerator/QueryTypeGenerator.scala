package queryGenerator

import com.sun.xml.internal.ws.resources.ServerMessages
import kanalony.storage.generator._
import kanalony.storage.logic.Dimensions
import com.google.common.base.CaseFormat

/**
 * Created by elad.benedict on 3/2/2016.
 */

class QueryTypeGenerator(tm : TableMetadata) {

  // TODO: Consider treating Metric just like any other dimension
  val extendedColumnInformation = tm.columns map { ColumnExtendedDefinition.convert(_) }
  val explicitColumnExtendedInformation = extendedColumnInformation.take(extendedColumnInformation.length-1).filter(c => !(c.inferred))
  val explicitNonMetricColumnExtendedInformation = explicitColumnExtendedInformation.filter(c => !(c.name == ColumnNames.metric))
  val extendedPartitionKeyColumnsInformation = tm.primaryKey.pk.columns.map(ColumnExtendedDefinition.convert(_))
  val explicitPartitionKeyColumnsExtendedInformation = extendedPartitionKeyColumnsInformation.filter(c => !c.inferred)
  val explicitNonMetricPartitionKeyColumnsExtendedInformation = explicitPartitionKeyColumnsExtendedInformation.filter(c => !(c.name == ColumnNames.metric))

  def getQueryName : String = QueryTypeGenerator.getQueryName(tm)
  def getTableRowType : String = EntityClassGenerator.getEntityName(tm)
  def getQueryParamsTypeName : String = ParamsTypeGenerator.getClassName(tm.tableName)
  def getSupportedMetricsProvider : String = "UserActivityQuery" // This will require additional metadata and include logic when we support additional query kinds
  def getEqualityConstraintExplicitColumnsNotIncludingMetrics : String = explicitNonMetricPartitionKeyColumnsExtendedInformation.map(c => c.name).mkString(",")
  def getEqualityConstraintColumnTypesNotIncludingMetricPlaceholder : String = explicitNonMetricPartitionKeyColumnsExtendedInformation.map(c => c.typeName).mkString(",")
  def getEqualityConstraintExplicitColumnsNotIncludingMetricsEnumValues : String = explicitNonMetricPartitionKeyColumnsExtendedInformation.map(c => s"Dimensions.${Dimensions.fromColumnName(c.name)}").mkString(",")


  def getTableQueryingImplementation : String = {
    def getEqualityArguments : String = extendedPartitionKeyColumnsInformation.map(x => s"params.${ParamsTypeGenerator.getParamName(x)}").mkString(",")
    QueryTemplates.TableQueryingTemplate.content
      .replace(QueryTemplates.TableQueryingTemplate.tableAccessorNamePlaceholder, TableAccessorGenerator.generateClassName(tm))
      .replace(QueryTemplates.TableQueryingTemplate.allEqualityValuesPlaceholder, getEqualityArguments)
  }

  def getMetricValueLocation : String  = explicitColumnExtendedInformation.length.toString
  def getResultHeadersImplementation : String = {
    val headers = explicitColumnExtendedInformation
      .map(x => s"Dimensions.${Dimensions.fromColumnName(x.name)}.toString")
    val res = (headers :+ "\"value\"").mkString(",")
    s"List(${res})"
  }

  def getResultRowImplementation : String = {
    val rowData = explicitColumnExtendedInformation
      .map(x => s"row.${EntityClassGenerator.getParamName(x.name)}.toString")
    val res = (rowData :+ "row.value.toString").mkString(",")
    s"List(${res})"
  }

  def getDimensionInformationImplementation : String = {
    val res = "List(%Dims%)"
    res.replace("%Dims%", explicitNonMetricColumnExtendedInformation
      .filter(c => c.inPartitionKey || c.inClusteringKey)
      .map(cd => {
                    val queryConstraint = if (cd.inPartitionKey) { "Equality"} else { "Range"}
                    s"DimensionDefinition(Dimensions.${Dimensions.fromColumnName(cd.name).toString}, new DimensionConstraintDeclaration(QueryConstraint.${queryConstraint}))"
      }).mkString(",\n"))
  }

  def generate() : String = {
    QueryTemplates.QueryClassTemplate.content
      .replace(QueryTemplates.QueryClassTemplate.queryNamePlaceholder, getQueryName)
      .replace(QueryTemplates.QueryClassTemplate.queryParamsClassNamePlaceholder, getQueryParamsTypeName)
      .replace(QueryTemplates.QueryClassTemplate.supportedMetricsProviderPlaceholder, getSupportedMetricsProvider)
      .replace(QueryTemplates.QueryClassTemplate.equalityConstraintColumnsNotIncludingMetricEnumValuesPlaceholder, getEqualityConstraintExplicitColumnsNotIncludingMetricsEnumValues)
      .replace(QueryTemplates.QueryClassTemplate.equalityConstraintColumnsNotIncludingMetricPlaceholder, getEqualityConstraintExplicitColumnsNotIncludingMetrics)
      .replace(QueryTemplates.QueryClassTemplate.equalityConstraintColumnTypesNotIncludingMetricPlaceholder, getEqualityConstraintColumnTypesNotIncludingMetricPlaceholder)
      .replace(QueryTemplates.QueryClassTemplate.tableQueryingImplementationPlaceholder, getTableQueryingImplementation)
      .replace(QueryTemplates.QueryClassTemplate.getResultHeadersImplementationPlaceholder, getResultHeadersImplementation)
      .replace(QueryTemplates.QueryClassTemplate.getResultRowImplementationPlaceholder, getResultRowImplementation)
      .replace(QueryTemplates.QueryClassTemplate.dimensionInformationImplementationPlaceholder, getDimensionInformationImplementation)
      .replace(QueryTemplates.QueryClassTemplate.metricValueLocationPlaceholder, getMetricValueLocation)
      .replace(QueryTemplates.QueryClassTemplate.tableRowTypePlaceholder, getTableRowType)
  }
}

object QueryTypeGenerator {
  def getQueryName(tm : TableMetadata) : String = s"${CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tm.tableName)}Query"
  def apply(tm : TableMetadata) = new QueryTypeGenerator(tm)
}
