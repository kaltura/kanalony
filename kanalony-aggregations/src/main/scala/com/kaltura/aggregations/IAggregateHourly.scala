package com.kaltura.aggregations

import com.kaltura.core.utils.ReadableTimeUnits.ReadableTimeUnits
import org.joda.time.DateTime

/**
 * Created by orlylampert on 2/21/16.
 */
trait IAggregateHourly extends IAggregate {
  override def ttl = 65 minutes
  override def getAggrTime(eventTime: DateTime): DateTime = eventTime.hourOfDay().roundFloorCopy()
  override def getAggrTimeUnit: String = "hour"

}
