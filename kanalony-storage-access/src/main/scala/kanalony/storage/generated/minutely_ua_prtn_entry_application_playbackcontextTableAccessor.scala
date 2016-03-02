package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class minutely_ua_prtn_entry_application_playbackcontextTableAccessor extends CassandraTable[minutely_ua_prtn_entry_application_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_playbackcontextRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object application extends StringColumn(this)with PartitionKey[String]
object playbackContext extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_entry_application_playbackcontext"

  def fromRow(row: Row): minutely_ua_prtn_entry_application_playbackcontextRow = {
    minutely_ua_prtn_entry_application_playbackcontextRow(
      partner_id(row), 
entry_id(row), 
application(row), 
playbackContext(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: minutely_ua_prtn_entry_application_playbackcontextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.entry_id, entity.entry_id)
.value(_.application, entity.application)
.value(_.playbackContext, entity.playbackContext)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, entry_id : String, application : String, playbackContext : String, metric : Int) : SelectQuery[minutely_ua_prtn_entry_application_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.application eqs application)
.and(_.playbackContext eqs playbackContext)
.and(_.metric eqs metric)
  }
 def query(partner_id : Int, entry_id : String, application : String, playbackContext : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_entry_application_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.application eqs application)
.and(_.playbackContext eqs playbackContext)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partner_id_list : List[Int], entry_id_list : List[String], application_list : List[String], playbackContext_list : List[String], metric_list : List[Int]) : SelectQuery[minutely_ua_prtn_entry_application_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.application in application_list)
.and(_.playbackContext in playbackContext_list)
.and(_.metric in metric_list)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], application_list : List[String], playbackContext_list : List[String], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_entry_application_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.application in application_list)
.and(_.playbackContext in playbackContext_list)
.and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}