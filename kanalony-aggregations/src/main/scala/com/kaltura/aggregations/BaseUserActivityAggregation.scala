package com.kaltura.aggregations

import com.datastax.spark.connector._
import com.kaltura.core.userAgent.enums.{Browser, Device, OperatingSystem}
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}
import org.joda.time.DateTime

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._



abstract class BaseUserActivityAggregation[AggKey:ClassTag, AggRes:TypeTag :ClassTag] extends Serializable with IAggregate {

  val tableMetadata: Map[String, SomeColumns]
  val keyspace = "kanalony_user_activity"

  def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]) : DStream[(AggKey,Long)] =
    enrichedEvents.map(e => (aggKey(e),1L)).reduceByKey(_ + _)

  def aggKey(e: EnrichedPlayerEvent): AggKey

  def trackStateFunc(batchTime: Time, key: AggKey, value: Option[Long], state: State[Long]): Option[(AggKey, Long)] = {
    val sum = value.getOrElse(0L) + state.getOption.getOrElse(0L)
    val output = (key, sum)
    if (!state.isTimingOut())
      state.update(sum)
    Some(output)
  }

  def prepareForSave(aggregatedEvents: MapWithStateDStream[AggKey, Long, Long, (AggKey, Long)]) : DStream[AggRes] =
    aggregatedEvents.map(agg => toRow(agg))


  def toRow(pair:(AggKey,Long)): AggRes

  val stateSpec = StateSpec.function(trackStateFunc _).timeout(Seconds(ttl))

  def save(aggregatedEvents: DStream[AggRes]) : Unit = {
    tableMetadata.foreach {
      case (tableName, columns) => aggregatedEvents.foreachRDD({ rdd =>
        println(s"writing to $keyspace.$tableName:")
        rdd.foreach(println)
        rdd.saveToCassandra(keyspace, tableName, columns)
      })
    }
  }

  def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit = {
    val aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
    val aggregatedEvents = aggregatedBatchEvents.mapWithState[Long,(AggKey, Long)](stateSpec)
    save(prepareForSave(aggregatedEvents))
  }

}



