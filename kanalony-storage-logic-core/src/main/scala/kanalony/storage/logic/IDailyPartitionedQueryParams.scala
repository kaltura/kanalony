package kanalony.storage.logic

import org.joda.time.{DateTimeZone, Days, DateTime, Months}
import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits

/**
 * Created by elad.benedict on 2/21/2016.
 */
trait IDailyPartitionedQueryParams {
  val startTime : DateTime
  val endTime : DateTime
  val days = {
    val dayCount = Days.daysBetween(startTime.withZone(DateTimeZone.UTC), endTime.withZone(DateTimeZone.UTC)).getDays
    (0 to dayCount).map(numOfDaysToAdd => {
      val dayDateTime = startTime.withZone(DateTimeZone.UTC).plusDays(numOfDaysToAdd)
      dayDateTime.getYearMonthDay
    }).toList
  }
}
