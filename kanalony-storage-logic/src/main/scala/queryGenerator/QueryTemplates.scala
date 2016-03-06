package queryGenerator

/**
 * Created by elad.benedict on 3/2/2016.
 */
object QueryTemplates {

  object QueryClassTemplate {

    val queryNamePlaceholder = "%QUERY_NAME%"
    val tableRowTypePlaceholder = "%TABLE_ROW_TYPE%"
    val queryParamsClassNamePlaceholder = "%QUERY_PARAMS_TYPE%"
    val supportedMetricsProviderPlaceholder = "%SUPPORTED_METRICS_PROVIDER%"
    val tableQueryingImplementationPlaceholder = "%QUERY_TABLE_WITH_PARAMS_ARGS%"
    val getResultHeadersImplementationPlaceholder = "%RESULT_HEADERS_IMPLEMENTATION%"
    val getResultRowImplementationPlaceholder = "%RESULT_ROW_IMPLEMENTATION%"
    val dimensionInformationImplementationPlaceholder = "%DIMENSION_INFORMATION_IMPLEMENTATION%"
    val metricValueLocationPlaceholder = "%METRIC_VALUE_LOCATION%"
    val equalityConstraintColumnsNotIncludingMetricPlaceholder = "%EQUALITY_CONS_DIMS_WITHOUT_METRIC%"
    val equalityConstraintColumnTypesNotIncludingMetricPlaceholder = "%EQUALITY_CONS_TYPES%"
    val equalityConstraintColumnsNotIncludingMetricEnumValuesPlaceholder = "%EQUALITY_CONS_DIMS_WITHOUT_METRIC_ENUM_VALUES%"

    val content =
      """package kanalony.storage.logic.queries

    import kanalony.storage.generated._
    import kanalony.storage.logic._
    import kanalony.storage.logic.queries.model._
    import kanalony.storage.api.DbClientFactory._
    import org.joda.time.DateTime
    import scala.concurrent.Future

    class %QUERY_NAME% extends QueryBase[%QUERY_PARAMS_TYPE%, %TABLE_ROW_TYPE%] with %SUPPORTED_METRICS_PROVIDER% {
      private[logic] override def extractParams(params: QueryParams): %QUERY_PARAMS_TYPE% = {
        val (%EQUALITY_CONS_DIMS_WITHOUT_METRIC%) = QueryParamsValidator.extractEqualityConstraintParams[%EQUALITY_CONS_TYPES%]((%EQUALITY_CONS_DIMS_WITHOUT_METRIC_ENUM_VALUES%), params)
        %QUERY_PARAMS_TYPE%(params.start, params.end, %EQUALITY_CONS_DIMS_WITHOUT_METRIC%, params.metrics.map(_.id))
      }

      private[logic] override def executeQuery(params: %QUERY_PARAMS_TYPE%): Future[List[%TABLE_ROW_TYPE%]] = {
        %QUERY_TABLE_WITH_PARAMS_ARGS%
      }

      override private[logic] def getResultHeaders(): List[String] =  {
        %RESULT_HEADERS_IMPLEMENTATION%
      }

      override protected def getResultRow(row: %TABLE_ROW_TYPE%): List[String] = {
        %RESULT_ROW_IMPLEMENTATION%
      }

      override val dimensionInformation: List[DimensionDefinition] = {
        %DIMENSION_INFORMATION_IMPLEMENTATION%
      }

      override def metricValueLocationIndex(): Int = %METRIC_VALUE_LOCATION%

      override private[logic] def extractMetric(row: %TABLE_ROW_TYPE%): Int = row.metric
    }"""
  }

  object TableQueryingTemplate {
    val content = """val rawQueryResult = %TABLE_ACCESSOR_NAME%.query(%EQUALITY_VALUES%,params.startTime,params.endTime)
                    |      .fetch()(dbApi.session, scala.concurrent.ExecutionContext.Implicits.global, dbApi.keyspace)
                    |    rawQueryResult""".stripMargin
    val tableAccessorNamePlaceholder = "%TABLE_ACCESSOR_NAME%"
    val allEqualityValuesPlaceholder = "%EQUALITY_VALUES%"
  }

  object QueryListTemplate {
    val content = """package kanalony.storage.logic
                    |
                    |import kanalony.storage.logic.queries._
                    |import kanalony.storage.logic._
                    |
                    |object Queries {
                    |  %QUERY_OBJECTS_DEFINITION%
                    |  val queries : List[IQuery] = List(
                    |    %QUERY_OBJECTS%
                    |  )
                    |}
                    |"""

    val queryObjectsDefinitionPlaceholder = "%QUERY_OBJECTS_DEFINITION%"
    val queryObjectsPlaceholder = "%QUERY_OBJECTS%"
  }
}

