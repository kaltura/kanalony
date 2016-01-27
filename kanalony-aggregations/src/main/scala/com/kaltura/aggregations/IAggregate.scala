package com.kaltura.aggregations

import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.DStream

/**
 * Created by orlylampert on 1/18/16.
 */
trait IAggregate {
  def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit
  def stam : String

  }
