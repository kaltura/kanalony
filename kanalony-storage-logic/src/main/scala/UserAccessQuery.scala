package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
/**
 * Created by elad.benedict on 2/10/2016.
 */
trait UserActivityQuery extends IQuery{
  val supportedMetrics = Set(Metrics.playImpression,Metrics.playRequested,Metrics.play,Metrics.estimatedMinutesWatched,
    Metrics.averageViewDuration,Metrics.playThrough25,Metrics.playThrough50,
    Metrics.playThrough75,Metrics.playThrough100,Metrics.averageViewDropOff,
    Metrics.segmentsWatched,Metrics.percentageWatched,Metrics.view,Metrics.dvrView,Metrics.peakView,
    Metrics.peakDvrView,Metrics.bufferingTime,Metrics.averageActualBitrate,
    Metrics.loadToPlayTime)
}
