package com.kaltura.enrichment


import com.datastax.spark.connector._
import com.kaltura.model.cache.EntryCache
import com.kaltura.model.entities.Entry
import com.kaltura.model.events.RawPlayerEvent
import org.apache.spark.rdd.RDD
import scala.collection.convert.decorateAsScala._
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by ofirk on 16/02/2016.
 */
object EnrichByEntry extends IEnrich {
  override type IDType = String
  override type EnrichType = Entry
  override def createLocalCache = new ConcurrentHashMap[String, Entry]().asScala
  localCache.put("-1", Entry("-1"))

  override def enrich(playerEvents: RDD[RawPlayerEvent]): RDD[RawPlayerEvent] = {
    playerEvents.mapPartitions { eventsPart =>
      eventsPart.map { currentRow: RawPlayerEvent =>
        val partnerId = currentRow.params.getOrElse("event:partnerId", "-1").toInt
        val entryId = currentRow.params.getOrElse("event:entryId", "")
        if (!localCache.contains(entryId)) {
          localCache.putIfAbsent(entryId, {
            EntryCache.getById(partnerId, entryId)
          })
        }
        val categories = localCache.getOrElse(entryId, Entry(entryId)).categories.getOrElse("")
        currentRow.copy(params = currentRow.params + ("categories" -> categories))
      }
    }
  }
}
