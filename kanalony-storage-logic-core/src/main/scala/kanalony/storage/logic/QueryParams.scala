package kanalony.storage.logic

import com.kaltura.model.entities.Metric
import kanalony.storage.logic.queries.model._
import org.joda.time.{DateTimeZone, LocalDateTime}

/**
 * Created by elad.benedict on 2/14/2016.
 */

case class QueryParams(dimensionDefinitions : List[IQueryDimensionDefinition], metrics : List[Metric], start : LocalDateTime, end : LocalDateTime, timezoneOffset : Int = 0, orderBy: OrderDefinition, pager: PagerDefinition) {
  val timezoneOffsetHours = timezoneOffset / 60
  val timezoneOffsetMinutes = timezoneOffset % 60

  val startUtc = start.toDateTime(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetHours, timezoneOffsetMinutes))
  val endUtc = end.toDateTime(DateTimeZone.forOffsetHoursMinutes(timezoneOffsetHours, timezoneOffsetMinutes))
}
