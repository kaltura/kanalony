package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/9/2016.
 */

class QueryMethodsGenerator(val tm : TableMetadata) {

  private def getRangeParamNames(colDef : IColumnDefinition)  = {
    (colDef.name + "Start", colDef.name + "End")
  }

  private def generateColDefs(cols : List[IColumnQueryDefinition]) : String = cols match {
    case x::Nil => {
      genParamDefinition(x)
    }
    case x::xs => {
      genParamDefinition(x) + ", " + generateColDefs(xs)
    }
  }

  private def genParamDefinition(cqd : IColumnQueryDefinition) = {
    cqd.queryKind match {
      case ColumnQueryKind.Equality => {
        cqd.name + " : " + cqd.typeName
      }
      case ColumnQueryKind.Range => {
        getRangeParamNames(cqd)._1 + " : " + cqd.typeName + ", " +
        getRangeParamNames(cqd)._2 + " : " + cqd.typeName
      }
    }
  }

  private def generateWhereCondition(x: IColumnDefinition) = {
    val whereClauseTemplate = GenerationTemplates.whereClauseTemplate;
    val condition = generateEqualityCondition(x)
    val whereClause = whereClauseTemplate.content.replace(whereClauseTemplate.conditionPlaceholder, condition)
    whereClause
  }

  private def generateEqualityCondition(x: IColumnDefinition) = {
    val conditionTemplate = GenerationTemplates.equalityConditionTemplate
    val condition = conditionTemplate.content.replace(conditionTemplate.columnNamePlaceholder, x.name)
    condition
  }

  private def generateRangeCondition(x: IColumnQueryDefinition) = {
    val andClauseTemplate = GenerationTemplates.andClauseTemplate

    val gtConditionTemplate = GenerationTemplates.greaterThanConditionTemplate
    var gtCondition = gtConditionTemplate.content.replace(gtConditionTemplate.columnNamePlaceholder, x.name)
    gtCondition = gtCondition.replace(gtConditionTemplate.rangeValuePlaceholder, getRangeParamNames(x)._1)
    val gtAndClauseTemplate = andClauseTemplate.content.replace(andClauseTemplate.conditionPlaceholder, gtCondition)

    val ltConditionTemplate = GenerationTemplates.lessThanConditionTemplate
    var ltCondition = ltConditionTemplate.content.replace(ltConditionTemplate.columnNamePlaceholder, x.name)
    ltCondition = ltCondition.replace(ltConditionTemplate.rangeValuePlaceholder, getRangeParamNames(x)._2)
    val ltAndClauseTemplate = andClauseTemplate.content.replace(andClauseTemplate.conditionPlaceholder, ltCondition)

    gtAndClauseTemplate + "\n" + ltAndClauseTemplate

  }

  private def generateAndClause(x: IColumnQueryDefinition) = {
    val andClauseTemplate = GenerationTemplates.andClauseTemplate;
    val andClause = x.queryKind match {
      case ColumnQueryKind.Equality => {
        andClauseTemplate.content.replace(andClauseTemplate.conditionPlaceholder, generateEqualityCondition(x))
      }
      case ColumnQueryKind.Range => generateRangeCondition(x)
    }
    andClause
  }

  private def generateAndConditions(xs: List[IColumnQueryDefinition]): String = {
    xs.map(generateAndClause).mkString("\n")
  }

  def generateQueryMethod(cols : List[IColumnQueryDefinition]): String = {
    val generatedQueryTemplate = GenerationTemplates.queryDefinitionTemplate
    var generatedQuery = generatedQueryTemplate.content
    val colsDefs = generateColDefs(cols)
    generatedQuery = generatedQuery.replace(generatedQueryTemplate.paramDefsPlaceholder, colsDefs)
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
