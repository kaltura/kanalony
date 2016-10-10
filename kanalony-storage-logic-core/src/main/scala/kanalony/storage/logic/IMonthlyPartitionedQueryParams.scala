package kanalony.storage.logic

import com.kaltura.core.utils.ReadableDateUnits.ReadableDateUnits
import org.joda.time.{DateTimeZone, Months, DateTime}

/**
 * Created by elad.benedict on 2/21/2016.
 */
trait IMonthlyPartitionedQueryParams {
  val startTime : DateTime
  val endTime : DateTime
  val months = {
    val startTimeFirstDayOfMonth = startTime.withZone(DateTimeZone.UTC).withDayOfMonth(1)
    val endTimeFirstDayOfNextMonth = endTime.withZone(DateTimeZone.UTC).plusMonths(1).withDayOfMonth(1)
    val monthCount = Months.monthsBetween(startTimeFirstDayOfMonth, endTimeFirstDayOfNextMonth).getMonths - 1
    (0 to monthCount).map(numOfMonthsToAdd => {
      val monthDateTime = startTime.withZone(DateTimeZone.UTC).plusMonths(numOfMonthsToAdd)
      monthDateTime.getYearMonth
    }).toList
  }
}
