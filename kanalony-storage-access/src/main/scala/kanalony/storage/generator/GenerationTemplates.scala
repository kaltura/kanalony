package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/7/2016.
 */
object GenerationTemplates {

  case object entityClassTemplate {
    var content = "import org.joda.time.DateTime\n"
    content = content + "case class %CLASSNAME%(%COLDEFS%)"
    val classNamePlaceholder = "%CLASSNAME%"
    val colDefsPlaceholder = "%COLDEFS%"
    val classNameSuffix = "Row"
  }

  case object packageTemplate {
    val content = "package kanalony.storage.generated"
  }

  case object valueDefinitionTemplate {
    val content = ".value(_.%PROPNAME%, entity.%PARAM_NAME%)"
    val propertyNamePlaceholder = "%PROPNAME%"
    val paramNamePlaceholder = "%PARAM_NAME%"
  }

  class columnDefinitionTemplate() extends IColumnDefinitionTemplate {
    def content = "object %PROPNAME% extends %TYPE%Column(this)"
    def typePlaceholder = "%TYPE%"
    def propNamePlaceholder = "%PROPNAME%"
  }

  class partitionKeyColumnDefinitionTemplate() extends columnDefinitionTemplate {
    override def content = super.content + "with PartitionKey[" + super.typePlaceholder + "]"
  }

  class clusteringKeyColumnDefinitionTemplate(val orderBy : OrderBy.Value = OrderBy.Ascending) extends columnDefinitionTemplate {
    override def content = super.content + "with ClusteringOrder[" + super.typePlaceholder + "] with " + orderBy.toString
  }

  case object propertyDecompositionTemplate {
    val content = "%PROPNAME%(row)"
    val propNamePlaceholder = "%PROPNAME%"
  }

  case object whereClauseTemplate {
    val content = ".where(%COND%)"
    val conditionPlaceholder = "%COND%"
  }

  case object andClauseTemplate {
    val content = ".and(%COND%)"
    val conditionPlaceholder = "%COND%"
  }

  case object equalityConditionTemplate {
    val content = "_.%COLNAME% eqs %PARAM_NAME%"
    val columnNamePlaceholder = "%COLNAME%"
    val paramNamePlaceholder = "%PARAM_NAME%"
  }

  case object containmentConditionTemplate {
    val content = "_.%COLNAME% in %LIST%"
    val columnNamePlaceholder = "%COLNAME%"
    val listPlaceholder = "%LIST%"
  }

  case object greaterThanConditionTemplate {
    val content = "_.%COLNAME% gt %RANGE_COLNAME%"
    val columnNamePlaceholder = "%COLNAME%"
    val rangeValuePlaceholder = "%RANGE_COLNAME%"
  }

  case object greaterThanOrEqualConditionTemplate {
    val content = "_.%COLNAME% gte %RANGE_COLNAME%"
    val columnNamePlaceholder = "%COLNAME%"
    val rangeValuePlaceholder = "%RANGE_COLNAME%"
  }

  case object lessThanConditionTemplate {
    val content = "_.%COLNAME% lt %RANGE_COLNAME%"
    val columnNamePlaceholder = "%COLNAME%"
    val rangeValuePlaceholder = "%RANGE_COLNAME%"
  }

  case object queryDefinitionTemplate {
    val content = """def query(%PARAM_DEFS%) : Future[List[%ENTITY_CLASS%]] = {
                    |    select%FILTERS%
                    |    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
                    |  }"""
    val paramDefsPlaceholder = "%PARAM_DEFS%"
    val entityClassNamePlaceholder = "%ENTITY_CLASS%"
    val filterClausesPlaceholder = "%FILTERS%"
  }

  case object queryDefinitionSignatureTemplate {
    val content = """def query(%PARAM_DEFS%) : Future[List[%ENTITY_CLASS%]]"""
    val paramDefsPlaceholder = "%PARAM_DEFS%"
    val entityClassNamePlaceholder = "%ENTITY_CLASS%"
  }

  case object tableAccessorTemplate {
    val classNamePlaceholder = "%CLASSNAME%"
    val interfaceNamePlaceholder = "%TABLE_ACCESSOR_INTERFACE%"
    val entityClassNamePlaceholder = "%ENTITY_CLASS%"
    val valuePopulationPlaceholder = "%VALUE_POPULATION%"
    val rowDecompositionPlaceholder = "%ROW_DECOMPOSITION%"
    val tableColDefsPlaceholder = "%TABLE_COL_DEFS%"
    val tableNamePlaceholder = "%TABLE_NAME%"
    val queryMethodsPlaceholder = "%QUERY_METHODS%"

    val content = """import com.websudos.phantom.dsl._
                    |import com.websudos.phantom.builder.query._
                    |import com.websudos.phantom.builder._
                    |import shapeless.HNil
                    |import scala.concurrent.Future
                    |
                    |abstract class %CLASSNAME% extends CassandraTable[%CLASSNAME%, %ENTITY_CLASS%] with RootConnector with %TABLE_ACCESSOR_INTERFACE% {
                    |
                    |  %TABLE_COL_DEFS%
                    |
                    |  override def tableName = "%TABLE_NAME%"
                    |
                    |  def fromRow(row: Row): %ENTITY_CLASS% = {
                    |    %ENTITY_CLASS%(
                    |      %ROW_DECOMPOSITION%
                    |    )
                    |  }
                    |
                    |  def store(entity: %ENTITY_CLASS%): Future[ResultSet] = {
                    |    insert%VALUE_POPULATION%
                    |      .future()
                    |  }
                    |
                    |  %QUERY_METHODS%
                    |
                    |}"""
  }

  case object tableAccessorInterfaceTemplate {
    val content = """
                    |import scala.concurrent.Future
                    |
                    |trait %TABLE_ACCESSOR_INTERFACE_NAME% {
                    |  %QUERY_METHODS_SIGNATURES%
                    |}"""

    val tableAccessorInterfaceNamePlaceholder = "%TABLE_ACCESSOR_INTERFACE_NAME%"
    val queryMethodsSignaturesPlaceholder = "%QUERY_METHODS_SIGNATURES%"
  }

  case object dbClientFactoryTemplate {
    val content = """package kanalony.storage
                |
                |import kanalony.storage._
                |import com.websudos.phantom.connectors.KeySpace
                |import kanalony.storage.generated._
                |
                |object DbClientFactory {
                |
                |  val connector = ConnectorFactory.connector
                |
                |  val session = connector.session
                |  val keyspace = KeySpace(connector.name)
                |
                |  %ACCESSOR_OBJECT_DEFINITION%
                |
                |}
                |"""
    val accessorObjectDefinitionsPlaceholder = "%ACCESSOR_OBJECT_DEFINITION%"
  }
}

trait IColumnDefinitionTemplate {
  def content : String
  def typePlaceholder : String
  def propNamePlaceholder : String
}
