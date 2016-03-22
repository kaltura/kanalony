package kanalony.storage.logic

import org.joda.time.{Days, DateTime, Months}

/**
 * Created by elad.benedict on 2/21/2016.
 */
trait IDailyPartitionedQueryParams {
  val startTime : DateTime
  val endTime : DateTime
  val days = {
    val dayCount = Days.daysBetween(startTime, endTime).getDays
    (0 to dayCount).map(numOfDaysToAdd => {
      val dayDateTime = startTime.plusDays(numOfDaysToAdd)
      dayDateTime.getYear * 10000 + dayDateTime.getMonthOfYear * 100 + dayDateTime.getDayOfMonth
    }).toList
  }
}
