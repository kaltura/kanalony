package controllers

import kanalony.storage.logic.queries.model._
import kanalony.storage.logic._
import model._
import org.joda.time.DateTime
import scala.concurrent.ExecutionContext.Implicits.global
import model.Implicits._
import play.api.mvc._
import argonaut._
import Argonaut._
import scala.concurrent._

class Application extends Controller {

  def query: Action[AnyContent] = Action.async({ implicit request => doQuery(request) })

  def doQuery(request : Request[AnyContent]) : Future[Result] = {
    try {
      val analyticsRequestOption = for {
        input <- request.body.asJson
        req <- input.toString.decodeOption[AnalyticsRequest]
      } yield req

      if (analyticsRequestOption.isEmpty) {
        return wrapWithPromise(BadRequest("Invalid input - ensure proper JSON is supplied with the mandatory fields supplied and that the supplied MIME type is application/json"))
      }

      val queryParams = requestToQueryParams(analyticsRequestOption.get)
      val queryExecutionResult = execute(queryParams)
      val queryResponseData =  queryExecutionResult map { data => AnalyticsResponse(data.headers, data.rows) }

      queryResponseData map {
        data => {
          val responseFormatter = ResponseFormatterFactory.get(request)
          val formattedResponse = responseFormatter.format(data)
          Ok(formattedResponse.data).as(formattedResponse.mime)
        }
      } recover {
        case e : Exception => BadRequest(e.getMessage)
      }
    }
    catch {
      case e: QueryNotSupportedException => {
        return wrapWithPromise(BadRequest(e.getMessage))
      }
      case e: IllegalArgumentException => {
        return wrapWithPromise(BadRequest(e.getMessage))
      }
      case e: Exception => {
        return wrapWithPromise(InternalServerError(e.getMessage))
      }
    }
  }

  def wrapWithPromise(res : Result): Future[Result] = {
    return (Promise[Result] success res).future
  }

  def execute(queryParams: QueryParams): Future[IQueryResult] = {
    QueryLocator.locate(queryParams).query(queryParams)
  }

  def extractValues[T, E <: Exception](values : List[String], enumConverter : String => T, exceptionCreator : String => E): List[T] = {
    var processedValue = ""
    var res = List[T]()
    try {
      values.foreach(d => {
        processedValue = d
        res = res :+ enumConverter(d)
      })
      res
    }
    catch {
      case e : Exception => throw exceptionCreator(processedValue)
    }
  }

  def extractMetrics(metrics : List[String]): List[Metrics.Value] = {
    extractValues(metrics, Metrics.withName(_), metric => new InvalidMetricsException(s"Metric $metric not supported"))
  }

  def extractDimension(dimension : String): Dimensions.Value = {
    extractValues(List(dimension), Dimensions.withName(_), s => new InvalidDimensionException(s)).head
  }

  def extractDimensions(dimensions : List[String]): List[Dimensions.Value] = {
    extractValues(dimensions, Dimensions.withName(_), s => new InvalidDimensionException(s))
  }

  def createConstraint(dimension: Dimensions.Value, values: List[String]) : IDimensionConstraint = {
    try {
      dimension match {
        case Dimensions.partner => new DimensionEqualityConstraint[Int](values.toSet.map { (v: String) => v.toInt })
        case _ => new DimensionEqualityConstraint(values.toSet)
      }
    }
    catch {
      case e : NumberFormatException => { throw new IllegalArgumentException(s"Dimension $dimension supplied invalid values")}
    }
  }

  def requestToQueryParams(req : AnalyticsRequest) : QueryParams = {

    val dimensionsInResult = extractDimensions(req.dimensions)
    val metricsInResult = extractMetrics(req.metrics)

    if (metricsInResult.isEmpty){
      throw new MetricNotSuppliedException
    }

    val constrainedDimensionDefinitions = req.filters.map {
      f => {
        val dimension = extractDimension(f.dimension)
        val shouldAppearInResult = dimensionsInResult contains dimension
        val dimensionConstraint = createConstraint(dimension, f.values)
        QueryDimensionDefinition(dimension, dimensionConstraint, shouldAppearInResult)
      }
    }

    val unconstrainedDimensionDefinitions = dimensionsInResult.filter(d => !(constrainedDimensionDefinitions.map(_.dimension) contains d)) map {
      dimension => QueryDimensionDefinition(dimension , new DimensionUnconstrained, true)
    }

    // TODO : currently only a single metric is supported
    QueryParams(constrainedDimensionDefinitions ::: unconstrainedDimensionDefinitions, metricsInResult(0), new DateTime(req.from), new DateTime(req.to))
  }
}