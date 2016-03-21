package com.kaltura.core.utils

import org.joda.time.DateTime

/**
 * Created by orlylampert on 3/16/16.
 */
object ReadableDateUnits {
  implicit class ReadableDateUnits(date: DateTime) {
    def getYearMonthDay = getYearMonth * 100 + date.getDayOfMonth

    def getYearMonth = date.getYear * 100 + date.getMonthOfYear

  }
}
