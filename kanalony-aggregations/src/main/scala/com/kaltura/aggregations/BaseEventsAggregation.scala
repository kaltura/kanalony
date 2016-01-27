package com.kaltura.aggregations

import com.datastax.spark.connector.cql.TableDef
import com.datastax.spark.connector.mapper.ColumnMapper
import com.datastax.spark.connector.writer.{RowWriter, RowWriterFactory}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.{StateSpec, State, Time}
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import com.datastax.spark.connector._
import org.joda.time.DateTime

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._



abstract class BaseEventsAggregation[AggKey:ClassTag, AggValue:ClassTag, StateType:ClassTag, MapType:ClassTag, AggRes:TypeTag] extends Serializable with IAggregate {

  def tableName() : String
  def keyspace() : String = "events_aggregations"
  def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]) : DStream[(AggKey,AggValue)]
  def trackStateFunc(batchTime: Time, key: AggKey, value: Option[AggValue], state: State[StateType]): Option[MapType]
  def prepareForSave(aggregatedEvents: MapWithStateDStream[AggKey, AggValue, StateType, MapType]) : DStream[AggRes]
  def someColumns() : SomeColumns

  val stateSpec = StateSpec.function(trackStateFunc _)

  def save(aggregatedEvents: DStream[AggRes]) : Unit = {
    aggregatedEvents.foreachRDD(rdd => rdd.saveToCassandra(keyspace,tableName, someColumns()))
  }



  def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit = {
    val aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
    val aggregatedEvents = aggregatedBatchEvents.mapWithState[StateType,MapType](stateSpec)
    save(prepareForSave(aggregatedEvents));
  }

}

case class EntryKey (entryId: String, eventType: Int, time: DateTime) extends Serializable
case class EntryResult (entryId: String, eventType: Int, time: DateTime, year: DateTime, value: Long) extends Serializable
