package controllers

import model.AnalyticsResponse

/**
 * Created by elad.benedict on 2/24/2016.
 */

class CsvResponseFormatter() extends IResponseFormatter {
  override def format(analyticsResponse: AnalyticsResponse): FormattedResponse = {
    val resData = (analyticsResponse.headers :: analyticsResponse.data).map(_.mkString(",")).mkString("\n")
    FormattedResponse(resData, "text/csv")
  }
}
