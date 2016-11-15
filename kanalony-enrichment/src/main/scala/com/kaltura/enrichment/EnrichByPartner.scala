package com.kaltura.enrichment

import com.kaltura.core.sessions.KSParserBase
import com.kaltura.model.cache.PartnerCache
import com.kaltura.model.entities.Partner
import com.kaltura.model.events.RawPlayerEvent

/**
 * Created by ofirk on 31/01/2016.
 */
class EnrichByPartner extends IEnrich[Integer,Partner] with KSParserBase {

  override def loadEntity(partnerId: Integer) : Partner = PartnerCache.getById(partnerId)

  def enrich(playerEvent:RawPlayerEvent):RawPlayerEvent = {
    if (playerEvent.isValid) {
      val partnerId = playerEvent.partnerId.get
      if (partnerId > 0) {
        val ks = playerEvent.params.getOrElse("ks", "")
        val ksData = parse(ks).getOrElse(KSData(partnerId))
        val userId = if (ksData.userId == "0") "Unknown" else ksData.userId
        playerEvent.copy(params = playerEvent.params + ("userId" -> userId))
        playerEvent.copy(params = playerEvent.params + ("partnerPackage" -> localCache.get(partnerId).packageId.getOrElse(-1).toString))
      }
      else playerEvent
    }
    else playerEvent
  }

  override def getPartnerSecret(partnerId:Int) = localCache.get(partnerId).secret.getOrElse("")

}
