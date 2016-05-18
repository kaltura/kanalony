package com.kaltura.aggregations

import com.datastax.spark.connector._
import com.kaltura.model.events.EnrichedPlayerEvent
import org.apache.spark.HashPartitioner
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.{DStream, MapWithStateDStream}

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._
import com.datastax.spark.connector.streaming._



abstract class BaseAggregation[AggKey:ClassTag, AggRes:TypeTag :ClassTag] extends Serializable with IAggregate {

  val tableMetadata: Map[String, SomeColumns]
  val keyspace = "kanalony_agg"

  def aggregateBatchEvents(enrichedEvents: DStream[EnrichedPlayerEvent]) : DStream[(AggKey,Long)] =
    enrichedEvents.map(e => (aggKey(e), 1L)).reduceByKey(_ + _) //.reduceByKey((x: Long, y: Long) => x + y, 2 * enrichedEvents.context.sparkContext.defaultParallelism)

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
    aggregatedEvents.cache()
    tableMetadata.foreach {
      case (tableName, columns) => aggregatedEvents.saveToCassandra(keyspace, tableName, columns)
    }
  }

  def aggregate(enrichedEvents: DStream[EnrichedPlayerEvent]) : Unit = {
    if (enrichedEvents.conf.get("spark.kanalony.events_aggregations.enabled_aggregations","").split(",").contains(this.getClass.getSimpleName.stripSuffix("$"))) {
      val aggregatedBatchEvents = aggregateBatchEvents(enrichedEvents)
      val aggregatedEvents = aggregatedBatchEvents.mapWithState[Long,(AggKey, Long)](stateSpec)
      save(prepareForSave(aggregatedEvents))
    }
  }

  def toSomeColumns( columnNames: List[(String, String)] ) : SomeColumns =
  {
    SomeColumns(columnNames.map(x => x._1 as x._2 ): _*)
  }

}



