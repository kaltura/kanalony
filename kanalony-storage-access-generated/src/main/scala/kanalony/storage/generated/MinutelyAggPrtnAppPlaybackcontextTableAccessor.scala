package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnAppPlaybackcontextTableAccessor extends CassandraTable[MinutelyAggPrtnAppPlaybackcontextTableAccessor, MinutelyAggPrtnAppPlaybackcontextRow] with RootConnector with IMinutelyAggPrtnAppPlaybackcontextTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object application extends StringColumn(this)with PartitionKey[String]
object playback_context extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_app_playbackcontext"

  def fromRow(row: Row): MinutelyAggPrtnAppPlaybackcontextRow = {
    MinutelyAggPrtnAppPlaybackcontextRow(
      partner_id(row), 
application(row), 
playback_context(row), 
metric(row), 
day(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnAppPlaybackcontextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.application, entity.application)
.value(_.playback_context, entity.playbackContext)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, application : String, playbackContext : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnAppPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.playback_context eqs playbackContext)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, application : String, playbackContext : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnAppPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.playback_context eqs playbackContext)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], applicationList : List[String], playbackContextList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnAppPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.playback_context in playbackContextList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], applicationList : List[String], playbackContextList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnAppPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.playback_context in playbackContextList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnAppPlaybackcontextRow(partnerId:Int,
application:String,
playbackContext:String,
metric:String,
day:Int,
minute:DateTime,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnAppPlaybackcontextTableAccessor {
  def query(partnerId : Int, application : String, playbackContext : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnAppPlaybackcontextRow]]
 def query(partnerId : Int, application : String, playbackContext : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnAppPlaybackcontextRow]]
def query(partnerIdList : List[Int], applicationList : List[String], playbackContextList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnAppPlaybackcontextRow]]
 def query(partnerIdList : List[Int], applicationList : List[String], playbackContextList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnAppPlaybackcontextRow]]
}