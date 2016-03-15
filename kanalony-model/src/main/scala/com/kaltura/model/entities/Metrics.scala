package com.kaltura.model.entities

/**
 * Created by elad.benedict on 3/15/2016.
 */

object Metrics extends Enumeration {
  val playImpression, playRequested, play, playThrough25, playThrough50,
  playThrough75, playThrough100, playRatio, averageViewDropOff,
  segmentsWatched, percentageWatched, uniqueKnownUsers,
  uniquePlayerSessionId, uniqueVideos, view, dvrView, peakView,
  peakDvrView, bufferingTime, averageActualBitrate, averageViewDuration,
  estimatedMinutesWatched, loadToPlayTime = Value
}
