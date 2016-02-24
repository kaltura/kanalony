package controllers

import play.api.mvc.{AnyContent, Request}

/**
 * Created by elad.benedict on 2/24/2016.
 */

object ResponseFormatterFactory {
  def get(request: Request[AnyContent]) : IResponseFormatter = {
    if (request.accepts("text/csv"))
    {
      new CsvResponseFormatter()
    }
    else {
      new JsonResponseFormatter()
    }
  }
}
