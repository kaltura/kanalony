package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryAppPlaybackcontextTableAccessor extends CassandraTable[HourlyAggPrtnEntryAppPlaybackcontextTableAccessor, HourlyAggPrtnEntryAppPlaybackcontextRow] with RootConnector with IHourlyAggPrtnEntryAppPlaybackcontextTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object application extends StringColumn(this)with PartitionKey[String]
object playback_context extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_app_playbackcontext"

  def fromRow(row: Row): HourlyAggPrtnEntryAppPlaybackcontextRow = {
    HourlyAggPrtnEntryAppPlaybackcontextRow(
      partner_id(row), 
entry_id(row), 
application(row), 
playback_context(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryAppPlaybackcontextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.application, entity.application)
.value(_.playback_context, entity.playbackContext)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, application : String, playbackContext : String, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryAppPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.application eqs application)
.and(_.playback_context eqs playbackContext)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, application : String, playbackContext : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryAppPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.application eqs application)
.and(_.playback_context eqs playbackContext)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], playbackContextList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryAppPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.application in applicationList)
.and(_.playback_context in playbackContextList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], playbackContextList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryAppPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.application in applicationList)
.and(_.playback_context in playbackContextList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnEntryAppPlaybackcontextRow(partnerId:Int,
entryId:String,
application:String,
playbackContext:String,
metric:String,
year:Int,
hour:DateTime,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryAppPlaybackcontextTableAccessor {
  def query(partnerId : Int, entryId : String, application : String, playbackContext : String, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryAppPlaybackcontextRow]]
 def query(partnerId : Int, entryId : String, application : String, playbackContext : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryAppPlaybackcontextRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], playbackContextList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryAppPlaybackcontextRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], playbackContextList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryAppPlaybackcontextRow]]
}