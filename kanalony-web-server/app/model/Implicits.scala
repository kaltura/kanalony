package model

import argonaut.Argonaut._

/**
 * Created by elad.benedict on 2/21/2016.
 */
object Implicits {
  implicit lazy val AnalyticsResponseCodecJson = casecodec2(AnalyticsResponse.apply, AnalyticsResponse.unapply)("headers", "data")
  implicit lazy val EqualityFilterCodecJson = casecodec2(EqualityFilter.apply, EqualityFilter.unapply)("dimension", "values")
  implicit lazy val AnalyticsRequestCodecJson = casecodec6(AnalyticsRequest.apply, AnalyticsRequest.unapply)("from", "to", "dimensions", "filters", "metrics", "utcOffset")

}
