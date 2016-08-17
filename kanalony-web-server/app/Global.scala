import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{Filter, RequestHeader, Result, WithFilters}

import scala.concurrent.Future

object Global extends WithFilters(AccessLog)
{


}

object AccessLog extends Filter {

  def apply(nextFilter: RequestHeader => Future[Result])
           (requestHeader: RequestHeader): Future[Result] = {

    val startTime = System.currentTimeMillis

    nextFilter(requestHeader).map { result =>

        val msg = s"method=${requestHeader.method} uri=${requestHeader.uri} remote-address=${requestHeader.remoteAddress} " +
          s"domain=${requestHeader.domain} query-string=${requestHeader.rawQueryString} " +
          s"referrer=${requestHeader.headers.get("referrer").getOrElse("N/A")} " +
          s"user-agent=[${requestHeader.headers.get("user-agent").getOrElse("N/A")}]"
      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime

      play.Logger.of("accesslog").info(msg)

      result.withHeaders("Request-Time" -> requestTime.toString)

    }
  }

}
