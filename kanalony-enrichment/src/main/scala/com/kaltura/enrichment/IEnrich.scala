package com.kaltura.enrichment

import java.util.concurrent.TimeUnit

import com.google.common.cache.{CacheBuilder, CacheLoader, LoadingCache, Cache}
import com.kaltura.model.events.RawPlayerEvent
import org.apache.spark.rdd.RDD


/**
 * Created by ofirk on 16/02/2016.
 */
trait IEnrich[IDType <: Object,EnrichType <: Object] {

  val maxCacheSize = 100000
  val expireAfterWrite: (Long, TimeUnit) = (1L, TimeUnit.HOURS)
  val localCache = buildLocalCache

  def buildLocalCache: LoadingCache[IDType, EnrichType] = {
    CacheBuilder.newBuilder()
      .maximumSize(maxCacheSize)
      .expireAfterWrite(expireAfterWrite._1, expireAfterWrite._2)
      .build(
        new CacheLoader[IDType ,EnrichType]() {
          override def load(id: IDType) : EnrichType = loadEntity(id)
        }
      )
  }
  def loadEntity(id: IDType) : EnrichType
  def enrich(playerEvents:RawPlayerEvent):RawPlayerEvent
}

