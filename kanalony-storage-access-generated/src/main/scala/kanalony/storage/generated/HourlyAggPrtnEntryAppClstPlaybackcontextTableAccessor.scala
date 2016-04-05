package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryAppClstPlaybackcontextTableAccessor extends CassandraTable[HourlyAggPrtnEntryAppClstPlaybackcontextTableAccessor, HourlyAggPrtnEntryAppClstPlaybackcontextRow] with RootConnector with IHourlyAggPrtnEntryAppClstPlaybackcontextTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object application extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object playback_context extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_app_clst_playbackcontext"

  def fromRow(row: Row): HourlyAggPrtnEntryAppClstPlaybackcontextRow = {
    HourlyAggPrtnEntryAppClstPlaybackcontextRow(
      partner_id(row), 
entry_id(row), 
application(row), 
metric(row), 
year(row), 
hour(row), 
playback_context(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryAppClstPlaybackcontextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.application, entity.application)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.playback_context, entity.playbackContext)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, application : String, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, application : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, application : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.playback_context gte playbackContextStart)
.and(_.playback_context lt playbackContextEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.playback_context gte playbackContextStart)
.and(_.playback_context lt playbackContextEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnEntryAppClstPlaybackcontextRow(partnerId:Int,
entryId:String,
application:String,
metric:String,
year:Int,
hour:DateTime,
playbackContext:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryAppClstPlaybackcontextTableAccessor {
  def query(partnerId : Int, entryId : String, application : String, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]]
 def query(partnerId : Int, entryId : String, application : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]]
 def query(partnerId : Int, entryId : String, application : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : Future[List[HourlyAggPrtnEntryAppClstPlaybackcontextRow]]
}