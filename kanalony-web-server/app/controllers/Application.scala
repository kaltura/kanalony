package controllers

import kanalony.storage.logic.queries.model.{DimensionUnconstrained, DimensionEqualityConstraint, QueryDimensionDefinition}
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

  def query = Action.async({ implicit request =>
    val input = request.body.asJson
    val req = input flatMap { jsonObj => jsonObj.toString().decodeOption[AnalyticsRequest] }
    val queryParams = req map requestToQueryParams
    val queryExecutionResult = queryParams flatMap execute

    val responsePromise : Future[Result] = queryExecutionResult match {
      case Some(resPromise) => {
        resPromise map { data =>
          val resObj = AnalyticsResponse(data.headers, data.rows)
          Ok(resObj.asJson.toString)
        }
      }
      case None => {
        val p = Promise[Result]
        p success {
          InternalServerError("Error!")
        }
        p.future
      }
    }

    responsePromise
  })

  def execute(queryParams: QueryParams): Option[Future[IQueryResult]] = {
    QueryLocator.locate(queryParams) map {
      q => q.query(queryParams)
    }
  }

  def requestToQueryParams(req : AnalyticsRequest) = {
    val dimensionsInResult = req.dimensions map {Dimensions.withName(_)}

    val constrainedDimensionDefinitions = req.filters.map {
      d => {
        if (d.dimension.toString == "partner")
          QueryDimensionDefinition(Dimensions.withName(d.dimension), new DimensionEqualityConstraint(d.values.toSet[String] map {_.toInt}), dimensionsInResult.map(_.toString) contains d.dimension)
        else
          QueryDimensionDefinition(Dimensions.withName(d.dimension), new DimensionEqualityConstraint(d.values.toSet), dimensionsInResult.map(_.toString) contains d.dimension)
      }
    }

    val additionalResultDimensions = req.dimensions.filter(d => !(constrainedDimensionDefinitions.map(_.dimension).toString contains d)) map {
      d => QueryDimensionDefinition(Dimensions.withName(d), new DimensionUnconstrained, true)
    }

    QueryParams(constrainedDimensionDefinitions ::: additionalResultDimensions, Metrics.withName(req.metrics(0)), new DateTime(req.from), new DateTime(req.to))
  }

}
