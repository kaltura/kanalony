package kanalony.storage.logic

object Dimensions extends Enumeration {
  val partner,entry,knownUserId,device,operatingSystem,browser,
  country,city,syndicationDomain,syndicationURL,application,
  category,playbackContext,day,hour,minute,tenSeconds,streamingProtocol,
  expectedQuality,uiConfID = Value
}
