package kanalony.storage.generator

import com.google.common.base.CaseFormat

/**
 * Created by elad.benedict on 2/9/2016.
 */

object QueryMethodsGenerator {

  def generateColDefs(cols : List[IQueryableColumnDefinition]) = {
    cols.map(QueryMethodsGenerator.genParamDefinition).mkString(", ")
  }

  private def genParamDefinition(cqd : IQueryableColumnDefinition) = {
    cqd.queryKind match {
      case ColumnQueryKind.Equality => {
        getPlainParamName(cqd) + " : " + cqd.typeName
      }
      case ColumnQueryKind.List => {
        getListParamName(cqd) + " : " + "List[" + cqd.typeName + "]"
      }
      case ColumnQueryKind.Range => {
        getRangeParamNames(cqd)._1 + " : " + cqd.typeName + ", " +
          getRangeParamNames(cqd)._2 + " : " + cqd.typeName
      }
    }
  }

  private def getRangeParamNames(colDef : IColumnDefinition)  = {
    val camelCasedName = getCamelCasedParamName(colDef.name.toString)
    (camelCasedName + "Start", camelCasedName + "End")
  }

  def getListParamName(colDef : IColumnDefinition)  = {
    val camelCasedName = getCamelCasedParamName(colDef.name.toString)
    camelCasedName + "List"
  }

  def getPlainParamName(cqd: IColumnDefinition) = {
    getCamelCasedParamName(cqd.name.toString)
  }

  private def getCamelCasedParamName(name : String) = {
    CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name)
  }
}

class QueryMethodsGenerator(val tm : TableMetadata) {

  private def generateColDefs(cols : List[IQueryableColumnDefinition]) = {
    QueryMethodsGenerator.generateColDefs(cols)
  }

  def generateContainmentCondition(x: IQueryableColumnDefinition) = {
    val containmentConditionTemplate = GenerationTemplates.containmentConditionTemplate;
    var condition = containmentConditionTemplate.content.replace(containmentConditionTemplate.listPlaceholder, QueryMethodsGenerator.getListParamName(x))
    condition = condition.replace(containmentConditionTemplate.columnNamePlaceholder, x.name.toString)
    condition
  }

  private def generateWhereCondition(x: IQueryableColumnDefinition) = {
    val whereClauseTemplate = GenerationTemplates.whereClauseTemplate;
    val condition =  x.queryKind match  {
      case ColumnQueryKind.Equality => generateEqualityCondition(x)
      case ColumnQueryKind.List => generateContainmentCondition(x)
    }

    val whereClause = whereClauseTemplate.content.replace(whereClauseTemplate.conditionPlaceholder, condition)
    whereClause
  }

  private def generateEqualityCondition(x: IColumnDefinition) = {
    val conditionTemplate = GenerationTemplates.equalityConditionTemplate
    val condition = conditionTemplate.content
                        .replace(conditionTemplate.columnNamePlaceholder, x.name.toString)
                        .replace(conditionTemplate.paramNamePlaceholder, QueryMethodsGenerator.getPlainParamName(x))
    condition
  }

  private def generateRangeCondition(x: IQueryableColumnDefinition) = {
    val andClauseTemplate = GenerationTemplates.andClauseTemplate

    val gtConditionTemplate = GenerationTemplates.greaterThanOrEqualConditionTemplate
    var gtCondition = gtConditionTemplate.content.replace(gtConditionTemplate.columnNamePlaceholder, x.name.toString)
    gtCondition = gtCondition.replace(gtConditionTemplate.rangeValuePlaceholder, QueryMethodsGenerator.getRangeParamNames(x)._1)
    val gtAndClauseTemplate = andClauseTemplate.content.replace(andClauseTemplate.conditionPlaceholder, gtCondition)

    val ltConditionTemplate = GenerationTemplates.lessThanConditionTemplate
    var ltCondition = ltConditionTemplate.content.replace(ltConditionTemplate.columnNamePlaceholder, x.name.toString)
    ltCondition = ltCondition.replace(ltConditionTemplate.rangeValuePlaceholder, QueryMethodsGenerator.getRangeParamNames(x)._2)
    val ltAndClauseTemplate = andClauseTemplate.content.replace(andClauseTemplate.conditionPlaceholder, ltCondition)

    gtAndClauseTemplate + "\n" + ltAndClauseTemplate

  }

  private def generateAndClause(x: IQueryableColumnDefinition) = {
    val andClauseTemplate = GenerationTemplates.andClauseTemplate;
    val andClause = x.queryKind match {
      case ColumnQueryKind.Equality => {
        andClauseTemplate.content.replace(andClauseTemplate.conditionPlaceholder, generateEqualityCondition(x))
      }
      case ColumnQueryKind.List => {
        andClauseTemplate.content.replace(andClauseTemplate.conditionPlaceholder, generateContainmentCondition(x))
      }
      case ColumnQueryKind.Range => generateRangeCondition(x)
    }
    andClause
  }

  private def generateAndConditions(xs: List[IQueryableColumnDefinition]): String = {
    xs.map(generateAndClause).mkString("\n")
  }

  def generateQueryMethod(cols : List[IQueryableColumnDefinition]): String = {
    val generatedQueryTemplate = GenerationTemplates.queryDefinitionTemplate
    var generatedQuery = generatedQueryTemplate.content
    val colsDefs = generateColDefs(cols)
    generatedQuery = generatedQuery.replace(generatedQueryTemplate.paramDefsPlaceholder, colsDefs)
    generatedQuery = generatedQuery.replace(generatedQueryTemplate.tableClassNamePlaceholder, TableAccessorGenerator.generateClassName(tm))
    val entityClassName = EntityClassGenerator.getEntityName(tm)
    generatedQuery = generatedQuery.replace(generatedQueryTemplate.entityClassNamePlaceholder, entityClassName)
    val filterClauses = cols match {
      case x::Nil => generateWhereCondition(x)
      case x::xs => generateWhereCondition(x) + generateAndConditions(xs)
    }
    generatedQuery = generatedQuery.replace(generatedQueryTemplate.filterClausesPlaceholder, filterClauses)
    generatedQuery
  }
}
