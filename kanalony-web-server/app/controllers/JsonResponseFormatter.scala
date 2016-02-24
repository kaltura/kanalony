package controllers

import model.AnalyticsResponse
import argonaut._
import Argonaut._
import model.Implicits._

/**
 * Created by elad.benedict on 2/24/2016.
 */
class JsonResponseFormatter() extends IResponseFormatter {
  override def format(analyticsResponse: AnalyticsResponse): FormattedResponse = {
    val resData = analyticsResponse.asJson
    FormattedResponse(resData.toString, "application/json")
  }
}
