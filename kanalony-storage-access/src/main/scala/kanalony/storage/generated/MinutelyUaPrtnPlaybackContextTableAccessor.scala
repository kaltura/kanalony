package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnPlaybackContextTableAccessor extends CassandraTable[MinutelyUaPrtnPlaybackContextTableAccessor, MinutelyUaPrtnPlaybackContextRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object playback_context extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_playback_context"

  def fromRow(row: Row): MinutelyUaPrtnPlaybackContextRow = {
    MinutelyUaPrtnPlaybackContextRow(
      partner_id(row), 
playback_context(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnPlaybackContextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.playback_context, entity.playbackContext)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, playbackContext : String, metric : Int) : SelectQuery[MinutelyUaPrtnPlaybackContextTableAccessor, MinutelyUaPrtnPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.playback_context eqs playbackContext)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, playbackContext : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnPlaybackContextTableAccessor, MinutelyUaPrtnPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.playback_context eqs playbackContext)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partnerIdList : List[Int], playbackContextList : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnPlaybackContextTableAccessor, MinutelyUaPrtnPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.playback_context in playbackContextList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], playbackContextList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnPlaybackContextTableAccessor, MinutelyUaPrtnPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.playback_context in playbackContextList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}