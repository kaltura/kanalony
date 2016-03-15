package com.kaltura.model.entities

/**
 * Created by elad.benedict on 2/16/2016.
 */
object InternalMetrics extends Enumeration {
  val playImpression = Value(1)
  val playRequested = Value(2)
  val play = Value(3)
  val playThrough25 = Value(11)
  val playThrough50 = Value(12)
  val playThrough75 = Value(13)
  val playThrough100 = Value(14)
  val playRatio, averageViewDropOff,
  segmentsWatched, percentageWatched, uniqueKnownUsers,
  uniquePlayerSessionId, uniqueVideos, view, dvrView, peakView,
  peakDvrView, bufferingTime, averageActualBitrate, averageViewDuration,
  estimatedMinutesWatched, loadToPlayTime = Value
  val tenSecsViewed = Value(99)
  val actualBitrate = Value(1000)
}