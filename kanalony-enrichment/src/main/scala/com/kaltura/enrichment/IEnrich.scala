package com.kaltura.enrichment

import com.kaltura.model.events.RawPlayerEvent
import org.apache.spark.rdd.RDD


/**
 * Created by ofirk on 16/02/2016.
 */
trait IEnrich {
  type IDType
  type EnrichType

  var localCache = createLocalCache

  def createLocalCache: scala.collection.concurrent.Map[IDType, EnrichType]
  def enrich(playerEvents:RDD[RawPlayerEvent]):RDD[RawPlayerEvent]
}

