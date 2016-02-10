package com.kaltura.core.userAgent.enums

/**
 * Created by ofirk on 01/02/2016.
 */
object Device extends Enumeration {
  val COMPUTER = Value(1, "Computer")
  val MOBILE = Value(2, "Mobile")
  val TABLET = Value(3, "Tablet")
  val GAME_CONSOLE = Value(4, "Game console")
  val DMR = Value(5, "Digital media receiver")
  val WEARABLE = Value(6, "Wearable computer")
  val UNKNOWN = Value(7, "Unknown")
}
