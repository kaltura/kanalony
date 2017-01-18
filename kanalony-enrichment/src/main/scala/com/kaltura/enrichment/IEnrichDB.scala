package com.kaltura.enrichment

import com.kaltura.model.events.EnrichedPlayerEvent

/**
 * Created by orlylampert on 1/15/17.
 */
trait IEnrichDB [IDType <: Object,EnrichType <: Object] extends IEntityCache[IDType, EnrichType]  {
  def enrich(playerEvents:EnrichedPlayerEvent):Unit

}
