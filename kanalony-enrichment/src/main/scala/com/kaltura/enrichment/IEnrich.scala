package com.kaltura.enrichment

import com.kaltura.model.events.RawPlayerEvent


/**
 * Created by ofirk on 16/02/2016.
 */
trait IEnrich[IDType <: Object,EnrichType <: Object] extends IEntityCache[IDType, EnrichType]  {
def enrich(playerEvents:RawPlayerEvent):RawPlayerEvent
}

