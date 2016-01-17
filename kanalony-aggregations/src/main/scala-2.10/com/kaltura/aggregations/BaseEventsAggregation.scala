package com.kaltura.aggregations

import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.{StateSpec, State, Time}
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import com.datastax.spark.connector._
import org.joda.time.DateTime

import scala.reflect.ClassTag


abstract class BaseEventsAggregation[AggKey: ClassTag, AggValue:ClassTag, StateType:ClassTag, MapType: ClassTag, AggRes: ClassTag] extends Serializable {

  def tableName() : String
  def keyspace() : String = "events_aggregations"
  def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]) : DStream[(AggKey,AggValue)]
  def trackStateFunc(batchTime: Time, key: AggKey, value: Option[AggValue], state: State[StateType]): Option[MapType]
  def prepareForSave(aggregatedEvents: MapWithStateDStream[AggKey, AggValue, StateType, MapType]) : DStream[AggRes]

  def save(aggregatedEvents: DStream[AggRes]) : Unit = {
    aggregatedEvents.foreachRDD(rdd => rdd.saveToCassandra(keyspace,tableName))
  }

  def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit = {
    val stateSpec = StateSpec.function(trackStateFunc _)
    val aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
    val aggregatedEvents = aggregatedBatchEvents.mapWithState[StateType,MapType](stateSpec)
    save(prepareForSave(aggregatedEvents));
  }

}



case class EntryKey (entryId: String, eventType: Int, time: DateTime)
