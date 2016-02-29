package com.kaltura.aggregations

import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.DStream

trait IAggregate {
  def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit
  def ttl: Int
}


