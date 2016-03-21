package com.kaltura.aggregations

import com.kaltura.core.utils.ReadableTimeUnits.ReadableTimeUnits
import org.joda.time.DateTime

/**
  * Created by orlylampert on 2/21/16.
  */
trait IAggregateMinutely extends IAggregate {
  override def ttl = 5 minutes
  override def getAggrTime(eventTime: DateTime): DateTime = eventTime.minuteOfHour().roundFloorCopy()
  override def getAggrTimeUnit: String = "minute"

}
