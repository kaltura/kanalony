package kanalony.storage.logic

import com.kaltura.model.entities.InternalMetrics
/**
 * Created by elad.benedict on 2/10/2016.
 */
trait UserActivityQuery extends IQuery{
  val supportedMetrics = Set(InternalMetrics.playImpression,InternalMetrics.playRequested,InternalMetrics.play,InternalMetrics.estimatedMinutesWatched,
    InternalMetrics.averageViewDuration,InternalMetrics.playThrough25,InternalMetrics.playThrough50,
    InternalMetrics.playThrough75,InternalMetrics.playThrough100,InternalMetrics.averageViewDropOff,
    InternalMetrics.segmentsWatched,InternalMetrics.percentageWatched,InternalMetrics.view,InternalMetrics.dvrView,InternalMetrics.peakView,
    InternalMetrics.peakDvrView,InternalMetrics.bufferingTime,InternalMetrics.averageActualBitrate,
    InternalMetrics.loadToPlayTime, InternalMetrics.tenSecsViewed)
}
