package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnEntryPlaybackcontextTableAccessor extends CassandraTable[MinutelyAggPrtnEntryPlaybackcontextTableAccessor, MinutelyAggPrtnEntryPlaybackcontextRow] with RootConnector with IMinutelyAggPrtnEntryPlaybackcontextTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object playback_context extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_entry_playbackcontext"

  def fromRow(row: Row): MinutelyAggPrtnEntryPlaybackcontextRow = {
    MinutelyAggPrtnEntryPlaybackcontextRow(
      partner_id(row), 
entry_id(row), 
playback_context(row), 
metric(row), 
day(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnEntryPlaybackcontextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.playback_context, entity.playbackContext)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, playbackContext : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnEntryPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.playback_context eqs playbackContext)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, playbackContext : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.playback_context eqs playbackContext)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], playbackContextList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnEntryPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.playback_context in playbackContextList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], playbackContextList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.playback_context in playbackContextList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnEntryPlaybackcontextRow(partnerId:Int,
entryId:String,
playbackContext:String,
metric:String,
day:Int,
minute:DateTime,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnEntryPlaybackcontextTableAccessor {
  def query(partnerId : Int, entryId : String, playbackContext : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnEntryPlaybackcontextRow]]
 def query(partnerId : Int, entryId : String, playbackContext : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryPlaybackcontextRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], playbackContextList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnEntryPlaybackcontextRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], playbackContextList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryPlaybackcontextRow]]
}