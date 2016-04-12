package kanalony.storage.logic

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic.queries.model._
import org.joda.time.DateTime

/**
 * Created by elad.benedict on 2/14/2016.
 */

case class QueryParams(dimensionDefinitions : List[IQueryDimensionDefinition], metrics : List[Metric], start : DateTime, end : DateTime, timezoneOffset : Int) {
  val startUtc = start.minusHours(timezoneOffset)
  val endUtc = end.minusHours(timezoneOffset)
}
