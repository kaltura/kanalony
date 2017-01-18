package com.kaltura.enrichment

import java.util.concurrent.TimeUnit

import com.google.common.cache.{CacheLoader, CacheBuilder, LoadingCache}

/**
 * Created by orlylampert on 1/15/17.
 */
trait IEntityCache[IDType <: Object,EntityType <: Object] {

  val maxCacheSize = 100000
  val expireAfterWrite: (Long, TimeUnit) = (1L, TimeUnit.HOURS)
  val localCache = buildLocalCache

  def buildLocalCache: LoadingCache[IDType, EntityType] = {
    CacheBuilder.newBuilder()
      .maximumSize(maxCacheSize)
      .expireAfterWrite(expireAfterWrite._1, expireAfterWrite._2)
      .build(
        new CacheLoader[IDType ,EntityType]() {
          override def load(id: IDType) : EntityType = loadEntity(id)
        }
      )
  }
  def loadEntity(id: IDType) : EntityType

}
