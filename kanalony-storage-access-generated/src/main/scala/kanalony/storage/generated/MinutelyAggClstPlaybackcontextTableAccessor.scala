package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggClstPlaybackcontextTableAccessor extends CassandraTable[MinutelyAggClstPlaybackcontextTableAccessor, MinutelyAggClstPlaybackcontextRow] with RootConnector with IMinutelyAggClstPlaybackcontextTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object playback_context extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_clst_playbackcontext"

  def fromRow(row: Row): MinutelyAggClstPlaybackcontextRow = {
    MinutelyAggClstPlaybackcontextRow(
      partner_id(row), 
metric(row), 
day(row), 
minute(row), 
playback_context(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggClstPlaybackcontextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.playback_context, entity.playbackContext)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : String, day : Int) : Future[List[MinutelyAggClstPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[MinutelyAggClstPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.playback_context gte playbackContextStart)
.and(_.playback_context lt playbackContextEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggClstPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[MinutelyAggClstPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.playback_context gte playbackContextStart)
.and(_.playback_context lt playbackContextEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggClstPlaybackcontextRow(partnerId:Int,
metric:String,
day:Int,
minute:DateTime,
playbackContext:String,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggClstPlaybackcontextTableAccessor {
  def query(partnerId : Int, metric : String, day : Int) : Future[List[MinutelyAggClstPlaybackcontextRow]]
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstPlaybackcontextRow]]
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[MinutelyAggClstPlaybackcontextRow]]
def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggClstPlaybackcontextRow]]
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstPlaybackcontextRow]]
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[MinutelyAggClstPlaybackcontextRow]]
}