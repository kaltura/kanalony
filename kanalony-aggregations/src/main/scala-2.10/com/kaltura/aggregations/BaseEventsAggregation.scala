package com.kaltura.aggregations

import com.kaltura.aggregations.EntryEventsAggregation.{EntryAggrKey, EntryAggrResult}
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.{StateSpec, State, Time}
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import com.datastax.spark.connector._
import org.joda.time.DateTime

import scala.reflect.ClassTag


/**
 * Created by orlylampert on 1/13/16.
 */
abstract class BaseEventsAggregation[AggKey: ClassTag, AggValue:ClassTag, StateType:ClassTag, MapType: ClassTag] extends Serializable {

  def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]) : DStream[(AggKey,AggValue)]
  def trackStateFunc(batchTime: Time, key: AggKey, value: Option[AggValue], state: State[StateType]): Option[MapType]
  def save(aggregatedEvents: MapWithStateDStream[AggKey, AggValue, StateType, MapType]) : Unit

  def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit = {
    val stateSpec = StateSpec.function(trackStateFunc _)
    val aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
    val aggregatedEvents = aggregatedBatchEvents.mapWithState[StateType,MapType](stateSpec)
    save(aggregatedEvents)
  }

}


object EntryHourlyAggregation extends BaseEventsAggregation[(String, Int, DateTime), Long, Long, ((String, Int, DateTime),Long)] {

  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[((String, Int, DateTime), Long)] = enrichedEvents.map(x => ((x.entryId, x.eventType, x.eventTime.minuteOfHour().roundFloorCopy()),1L)).reduceByKey(_ + _)

  override def trackStateFunc(batchTime: Time, key: (String, Int, DateTime), value: Option[Long], state: State[Long]): Option[((String, Int, DateTime), Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    state.update(sum)
    Some(output)
  }

  override def save(aggregatedEvents: MapWithStateDStream[(String, Int, DateTime), Long, Long, ((String, Int, DateTime),Long)]): Unit = {

    aggregatedEvents.foreachRDD(rdd => {
      rdd.map(x => (x._1._2, x._1._3, x._1._1, x._2))
    }.saveToCassandra("schema_tests","entry_events_by_minute"))

  }

}
