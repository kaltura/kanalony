package com.kaltura.aggregations

import com.kaltura.aggregations.EntryEventsAggregation.EntryAggrResult
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming.{StateSpec, State, Time}
import org.apache.spark.streaming.dstream.{DStream, PairDStreamFunctions}
import com.datastax.spark.connector._


/**
 * Created by orlylampert on 1/13/16.
 */
abstract class BaseEventsAggregation[K, V] {

  var aggregatedBatchEvents: DStream[(K, V)]
  var aggregatedEvents: DStream[(K, V)]


  //def mapEvents(enrichedPlayerEvent: EnrichedPlayerEvent) : (K,V)

  def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]) : DStream[(K, V)]
  def trackStateFunc(batchTime: Time, key: K, value: Option[V], state: State[V]): Option[(K, V)]
  def save() : Unit

  def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit = {

    aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
    val stateSpec = StateSpec.function(trackStateFunc _)
    aggregatedEvents = aggregatedBatchEvents.mapWithState(stateSpec)
    save()
  }


  }


object EntryHourlyAggregation extends BaseEventsAggregation[Int, Long] {


  override def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]): DStream[(Int, Long)] = enrichedEvents.map(x => (x.eventType, 1L)).reduceByKey(_ + _)

  override def trackStateFunc(batchTime: Time, key: Int, value: Option[Long], state: State[Long]): Option[(Int, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    state.update(sum)
    Some(output)
  }

  override def save(): Unit = {

    aggregatedEvents.foreachRDD(rdd => {
      rdd.map(x => (x._1, x._1, x._2))
    }.saveToCassandra("",""))

  }

  override var aggregatedBatchEvents: DStream[(Int, Long)] = _
  override var aggregatedEvents: DStream[(Int, Long)] = _
}
