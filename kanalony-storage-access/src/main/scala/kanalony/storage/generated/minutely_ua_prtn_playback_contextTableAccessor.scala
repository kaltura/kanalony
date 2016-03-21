package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class minutely_ua_prtn_playback_contextTableAccessor extends CassandraTable[minutely_ua_prtn_playback_contextTableAccessor, minutely_ua_prtn_playback_contextRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object playback_context extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_playback_context"

  def fromRow(row: Row): minutely_ua_prtn_playback_contextRow = {
    minutely_ua_prtn_playback_contextRow(
      partner_id(row), 
playback_context(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: minutely_ua_prtn_playback_contextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.playback_context, entity.playback_context)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, playback_context : String, metric : Int) : SelectQuery[minutely_ua_prtn_playback_contextTableAccessor, minutely_ua_prtn_playback_contextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.playback_context eqs playback_context)
.and(_.metric eqs metric)
  }
 def query(partner_id : Int, playback_context : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_playback_contextTableAccessor, minutely_ua_prtn_playback_contextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.playback_context eqs playback_context)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partner_id_list : List[Int], playback_context_list : List[String], metric_list : List[Int]) : SelectQuery[minutely_ua_prtn_playback_contextTableAccessor, minutely_ua_prtn_playback_contextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.playback_context in playback_context_list)
.and(_.metric in metric_list)
  }
 def query(partner_id_list : List[Int], playback_context_list : List[String], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_playback_contextTableAccessor, minutely_ua_prtn_playback_contextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.playback_context in playback_context_list)
.and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}