package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnAppPlaybackContextClstEntryTableAccessor extends CassandraTable[HourlyUaPrtnAppPlaybackContextClstEntryTableAccessor, HourlyUaPrtnAppPlaybackContextClstEntryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object application extends StringColumn(this)with PartitionKey[String]
object playback_context extends StringColumn(this)with PartitionKey[String]
object month extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_app_playback_context_clst_entry"

  def fromRow(row: Row): HourlyUaPrtnAppPlaybackContextClstEntryRow = {
    HourlyUaPrtnAppPlaybackContextClstEntryRow(
      partner_id(row), 
application(row), 
playback_context(row), 
month(row), 
metric(row), 
hour(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnAppPlaybackContextClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.application, entity.application)
.value(_.playback_context, entity.playbackContext)
.value(_.month, entity.month)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, application : String, playbackContext : String, month : Int, metric : Int) : SelectQuery[HourlyUaPrtnAppPlaybackContextClstEntryTableAccessor, HourlyUaPrtnAppPlaybackContextClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.playback_context eqs playbackContext)
.and(_.month eqs month)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, application : String, playbackContext : String, month : Int, metric : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnAppPlaybackContextClstEntryTableAccessor, HourlyUaPrtnAppPlaybackContextClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.playback_context eqs playbackContext)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerId : Int, application : String, playbackContext : String, month : Int, metric : Int, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[HourlyUaPrtnAppPlaybackContextClstEntryTableAccessor, HourlyUaPrtnAppPlaybackContextClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.playback_context eqs playbackContext)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }
def query(partnerIdList : List[Int], applicationList : List[String], playbackContextList : List[String], monthList : List[Int], metricList : List[Int]) : SelectQuery[HourlyUaPrtnAppPlaybackContextClstEntryTableAccessor, HourlyUaPrtnAppPlaybackContextClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.playback_context in playbackContextList)
.and(_.month in monthList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], applicationList : List[String], playbackContextList : List[String], monthList : List[Int], metricList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnAppPlaybackContextClstEntryTableAccessor, HourlyUaPrtnAppPlaybackContextClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.playback_context in playbackContextList)
.and(_.month in monthList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerIdList : List[Int], applicationList : List[String], playbackContextList : List[String], monthList : List[Int], metricList : List[Int], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[HourlyUaPrtnAppPlaybackContextClstEntryTableAccessor, HourlyUaPrtnAppPlaybackContextClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.playback_context in playbackContextList)
.and(_.month in monthList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }

}