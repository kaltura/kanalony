package com.kaltura.core.utils

/**
 * Created by ofirk on 16/02/2016.
 */
object ReadableTimeUnits {
  implicit class ReadableTimeUnits(unit: Int) {
    def days = unit * 60 * 60 * 24
    def day = days

    def hours = unit * 60 * 60
    def hour = hours
  }
}

