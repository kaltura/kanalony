package com.kaltura.aggregations

import org.joda.time.DateTime
import com.kaltura.core.utils.ReadableTimeUnits.ReadableTimeUnits

/**
 * Created by orlylampert on 3/2/16.
 */
trait IAggregateTenSecs extends IAggregate {
  override def ttl = 1 minutes
  def getAggrTime(eventTime: DateTime): DateTime = eventTime.minuteOfHour().roundFloorCopy().withSecondOfMinute(eventTime.getSecondOfMinute() / 10 * 10)
  override def getAggrTimeUnit: String = "tensecs"

}
