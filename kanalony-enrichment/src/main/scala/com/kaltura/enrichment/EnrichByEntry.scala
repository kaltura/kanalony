package com.kaltura.enrichment


import java.util.concurrent.TimeUnit

import com.google.common.cache.{CacheBuilder, CacheLoader}
import com.kaltura.model.cache.EntryCache
import com.kaltura.model.entities.Entry
import com.kaltura.model.events.RawPlayerEvent
import org.apache.spark.rdd.RDD

/**
 * Created by ofirk on 16/02/2016.
 */
object EnrichByEntry extends IEnrich[(Int,String),Entry] {

  override def loadEntity(entryPartnerId: (Int, String)) : Entry = EntryCache.getById(entryPartnerId._1, entryPartnerId._2)

  override def enrich(playerEvents: RDD[RawPlayerEvent]): RDD[RawPlayerEvent] = {
    playerEvents.mapPartitions { eventsPart =>
      eventsPart.map { currentRow: RawPlayerEvent =>
        val partnerId = currentRow.params.getOrElse("event:partnerId","0").toInt // TODO: Potential NumberFormatException
        if (partnerId > 0) {
          val entryId = currentRow.params.getOrElse("event:entryId", "")
          val categories = localCache.get((partnerId, entryId)).categories.getOrElse("")
          currentRow.copy(params = currentRow.params + ("categories" -> categories))
        }
        else currentRow
      }
    }
  }
}
