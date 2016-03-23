package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnEntryAppClstPlaybackContextTableAccessor extends CassandraTable[MinutelyUaPrtnEntryAppClstPlaybackContextTableAccessor, MinutelyUaPrtnEntryAppClstPlaybackContextRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object application extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object playback_context extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_entry_app_clst_playback_context"

  def fromRow(row: Row): MinutelyUaPrtnEntryAppClstPlaybackContextRow = {
    MinutelyUaPrtnEntryAppClstPlaybackContextRow(
      partner_id(row), 
entry_id(row), 
application(row), 
metric(row), 
minute(row), 
playback_context(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnEntryAppClstPlaybackContextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.application, entity.application)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.playback_context, entity.playbackContext)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, application : String, metric : Int) : SelectQuery[MinutelyUaPrtnEntryAppClstPlaybackContextTableAccessor, MinutelyUaPrtnEntryAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.application eqs application)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, entryId : String, application : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryAppClstPlaybackContextTableAccessor, MinutelyUaPrtnEntryAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, entryId : String, application : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : SelectQuery[MinutelyUaPrtnEntryAppClstPlaybackContextTableAccessor, MinutelyUaPrtnEntryAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.playback_context gte playbackContextStart)
.and(_.playback_context lt playbackContextEnd)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnEntryAppClstPlaybackContextTableAccessor, MinutelyUaPrtnEntryAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.application in applicationList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryAppClstPlaybackContextTableAccessor, MinutelyUaPrtnEntryAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : SelectQuery[MinutelyUaPrtnEntryAppClstPlaybackContextTableAccessor, MinutelyUaPrtnEntryAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.playback_context gte playbackContextStart)
.and(_.playback_context lt playbackContextEnd)
  }

}