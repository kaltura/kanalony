package com.kaltura.enrichment

import com.kaltura.model.cache.EntryMetadataCache
import com.kaltura.model.entities.EntryMetadata
import com.kaltura.model.events.EnrichedPlayerEvent

/**
 * Created by orlylampert on 1/15/17.
 */
class EnricheEntryMetadata extends IEnrichDB[(Int,String),EntryMetadata] {
  override def loadEntity(entryPartnerId: (Int, String)): EntryMetadata = EntryMetadataCache.getById(entryPartnerId._1, entryPartnerId._2)

  override def enrich(playerEvents: EnrichedPlayerEvent): Unit = localCache.get((playerEvents.partnerId, playerEvents.entryId))
}
