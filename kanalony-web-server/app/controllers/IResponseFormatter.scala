package controllers

import model.AnalyticsResponse

/**
 * Created by elad.benedict on 2/24/2016.
 */
trait IResponseFormatter {
  def format(analyticsResponse: AnalyticsResponse) : FormattedResponse
}
