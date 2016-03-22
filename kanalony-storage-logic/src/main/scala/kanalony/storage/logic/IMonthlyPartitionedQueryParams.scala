package kanalony.storage.logic

import org.joda.time.{Months, DateTime}

/**
 * Created by elad.benedict on 2/21/2016.
 */
trait IMonthlyPartitionedQueryParams {
  val startTime : DateTime
  val endTime : DateTime
  val months = {
    val monthCount = Months.monthsBetween(startTime, endTime).getMonths
    (0 to monthCount).map(numOfMonthsToAdd => {
      val monthDateTime = startTime.plusMonths(numOfMonthsToAdd)
      monthDateTime.getYear * 100 + monthDateTime.getMonthOfYear
    }).toList
  }
}
