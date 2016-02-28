package com.kaltura.aggregations

import com.kaltura.core.utils.ReadableTimeUnits.ReadableTimeUnits

/**
  * Created by orlylampert on 2/21/16.
  */
trait IAggregateMinutely extends IAggregate {
  override def ttl = 5 minutes
}
