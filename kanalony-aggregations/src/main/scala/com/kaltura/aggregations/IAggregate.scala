package com.kaltura.aggregations

import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.dstream.DStream
import org.joda.time.DateTime

trait IAggregate {
  def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit
  def ttl: Int
  def getAggrTime(eventTime: DateTime): DateTime
  def getAggrTimeUnit: String

}


