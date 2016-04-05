package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnEntryClstPlaybackcontextTableAccessor extends CassandraTable[MinutelyAggPrtnEntryClstPlaybackcontextTableAccessor, MinutelyAggPrtnEntryClstPlaybackcontextRow] with RootConnector with IMinutelyAggPrtnEntryClstPlaybackcontextTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object playback_context extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_entry_clst_playbackcontext"

  def fromRow(row: Row): MinutelyAggPrtnEntryClstPlaybackcontextRow = {
    MinutelyAggPrtnEntryClstPlaybackcontextRow(
      partner_id(row), 
entry_id(row), 
metric(row), 
day(row), 
minute(row), 
playback_context(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnEntryClstPlaybackcontextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.playback_context, entity.playbackContext)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.playback_context gte playbackContextStart)
.and(_.playback_context lt playbackContextEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.playback_context gte playbackContextStart)
.and(_.playback_context lt playbackContextEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnEntryClstPlaybackcontextRow(partnerId:Int,
entryId:String,
metric:String,
day:Int,
minute:DateTime,
playbackContext:String,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnEntryClstPlaybackcontextTableAccessor {
  def query(partnerId : Int, entryId : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]]
 def query(partnerId : Int, entryId : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]]
 def query(partnerId : Int, entryId : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[MinutelyAggPrtnEntryClstPlaybackcontextRow]]
}