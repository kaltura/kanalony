package kanalony.storage.logic

import org.joda.time.{DateTimeZone, DateTime}

/**
 * Created by elad.benedict on 2/21/2016.
 */
trait IYearlyPartitionedQueryParams {
  val startTime : DateTime
  val endTime : DateTime
  val years = (startTime.withZone(DateTimeZone.UTC).getYear to endTime.withZone(DateTimeZone.UTC).getYear).toList
}
