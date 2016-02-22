package kanalony.storage.logic

import org.joda.time.DateTime

/**
 * Created by elad.benedict on 2/21/2016.
 */
trait IYearlyPartitionedQueryParams {
  val startTime : DateTime
  val endTime : DateTime
  val years = (startTime.getYear to endTime.getYear).toList
}
