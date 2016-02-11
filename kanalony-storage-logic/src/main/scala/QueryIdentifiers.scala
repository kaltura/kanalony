package kanalony.storage.logic

import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/10/2016.
 */

trait IQuery {
  val supportedMetrics : List[Metrics.Value]
  val supportedDimensions : List[Dimensions.Value]
  def execute(params : Map[String, Any]) : Future[IQueryResult]
}

trait IQueryResult {
  val headers : List[String]
  val rows : List[List[String]]
}

case class QueryResult(headers : List[String], rows : List[List[String]]) extends IQueryResult

object Metrics extends Enumeration {
  val playImpression,playRequested,play,estimatedMinutesWatched,
  averageViewDuration,playThrough25,playThrough50,
  playThrough75,playThrough100,playRatio,averageViewDropOff,
  segmentsWatched,percentageWatched,uniqueKnownUsers,
  uniquePlayerSessionId,uniqueVideos,view,dvrView,peakView,
  peakDvrView,bufferingTime,averageActualBitrate,
  loadToPlayTime = Value
}

object Dimensions extends Enumeration {
  val partner,entry,knownUserId,device,operatingSystem,browser,
  country,city,syndicationDomain,syndicationURL,application,
  category,playbackContext,day,hour,minute,tenSeconds,streamingProtocol,
  expectedQuality,uiConfID = Value
}
