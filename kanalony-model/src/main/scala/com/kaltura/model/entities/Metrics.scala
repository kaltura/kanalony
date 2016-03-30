package com.kaltura.model.entities

/**
 * Created by elad.benedict on 2/16/2016.
 */

case class Metric(name : String, aggregationKind: AggregationKind.Value = AggregationKind.Sum, isPredefined : Boolean = true) {
  val isUserDefined = !isPredefined
}

object AggregationKind extends Enumeration {
  val Sum, Max = Value
}

object Metrics {
  object playImpression extends Metric("playImpression")
  object playRequested extends Metric("playRequested")
  object play extends Metric("play")
  object playThrough25 extends Metric("playThrough25")
  object playThrough50 extends Metric("playThrough50")
  object playThrough75 extends Metric("playThrough75")
  object playThrough100 extends Metric("playThrough100")
  object playRatio extends Metric("playRatio")
  object averageViewDropOff extends Metric("averageViewDropOff")
  object segmentsWatched extends Metric("segmentsWatched")
  object percentageWatched extends Metric("percentageWatched")
  object uniqueKnownUsers extends Metric("uniqueKnownUsers")
  object uniquePlayerSessionId extends Metric("uniquePlayerSessionId")
  object uniqueVideos extends Metric("uniqueVideos")
  object view extends Metric("view")
  object dvrView extends Metric("dvrView")
  object peakView extends Metric("peakView", AggregationKind.Max)
  object peakDvrView extends Metric("peakDvrView", AggregationKind.Max)
  object bufferingTime extends Metric("bufferingTime")
  object averageActualBitrate extends Metric("averageActualBitrate")
  object averageViewDuration extends Metric("averageViewDuration")
  object estimatedMinutesWatched extends Metric("estimatedMinutesWatched")
  object loadToPlayTime extends Metric("loadToPlayTime")
  object tenSecsViewed extends Metric("tenSecsViewed")
  object actualBitrate extends Metric("actualBitrate")

  def get(name : String) : Metric = {
    val metric = Metrics.values.find(_.name == name)
    if (metric.isDefined)
    {
      metric.get
    }
    else
    {
      Metric(name, AggregationKind.Sum, false)
    }
  }

  val values = Set(playImpression, playRequested, play,
    playThrough25, playThrough50, playThrough75, playThrough100,
    playRatio, averageViewDropOff, segmentsWatched, percentageWatched,
    uniqueKnownUsers, uniquePlayerSessionId, uniqueVideos, view,
    dvrView, peakView, peakDvrView, bufferingTime, averageActualBitrate,
    averageViewDuration, estimatedMinutesWatched, loadToPlayTime, tenSecsViewed,
    actualBitrate)
}