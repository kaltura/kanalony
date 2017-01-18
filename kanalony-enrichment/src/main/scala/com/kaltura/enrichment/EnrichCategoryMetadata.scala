package com.kaltura.enrichment

import com.kaltura.model.cache.{CategoryMetadataCache, CategoryMetadataCache$$}
import com.kaltura.model.entities.CategoryMetadata
import com.kaltura.model.events.EnrichedPlayerEvent

/**
 * Created by orlylampert on 1/15/17.
 */
class EnrichCategoryMetadata extends IEnrichDB[(Int,String),CategoryMetadata] {
  override def enrich(playerEvent: EnrichedPlayerEvent): Unit = {
    val categories = playerEvent.categories
    categories.split(",").map(category => localCache.get((playerEvent.partnerId, category)))

  }

  override def loadEntity(catgoryId: (Int, String)): CategoryMetadata = CategoryMetadataCache.getById(catgoryId._1, catgoryId._2)
}
