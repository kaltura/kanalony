package kanalony.storage.logic

/**
 * Created by elad.benedict on 2/16/2016.
 */
object Metrics extends Enumeration {
  val playImpression,playRequested,play, estimatedMinutesWatched,
  averageViewDuration,playThrough25,playThrough50,
  playThrough75,playThrough100,playRatio,averageViewDropOff,
  segmentsWatched,percentageWatched,uniqueKnownUsers,
  uniquePlayerSessionId,uniqueVideos,view,dvrView,peakView,
  peakDvrView,bufferingTime,averageActualBitrate,
  loadToPlayTime = Value
}
