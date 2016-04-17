package com.kaltura.enrichment


import com.kaltura.model.cache.EntryCache
import com.kaltura.model.entities.Entry
import com.kaltura.model.events.RawPlayerEvent

/**
 * Created by ofirk on 16/02/2016.
 */
class EnrichByEntry extends IEnrich[(Int,String),Entry] {

  override def loadEntity(entryPartnerId: (Int, String)) : Entry = EntryCache.getById(entryPartnerId._1, entryPartnerId._2)

  override def enrich(playerEvent: RawPlayerEvent): RawPlayerEvent = {
    if (playerEvent.isValid) {
      val partnerId = playerEvent.partnerId.get
      if (partnerId > 0) {
        val entryId = playerEvent.entryId.get
        val categories = localCache.get((partnerId, entryId)).categories.getOrElse("")
        playerEvent.copy(params = playerEvent.params + ("categories" -> categories))
      }
      else playerEvent
    }
    else playerEvent
  }
}
