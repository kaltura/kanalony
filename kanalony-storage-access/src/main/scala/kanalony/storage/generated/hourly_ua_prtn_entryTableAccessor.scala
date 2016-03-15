package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class hourly_ua_prtn_entryTableAccessor extends CassandraTable[hourly_ua_prtn_entryTableAccessor, hourly_ua_prtn_entryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object month extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_entry"

  def fromRow(row: Row): hourly_ua_prtn_entryRow = {
    hourly_ua_prtn_entryRow(
      partner_id(row), 
entry_id(row), 
metric(row), 
month(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: hourly_ua_prtn_entryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.entry_id, entity.entry_id)
.value(_.metric, entity.metric)
.value(_.month, entity.month)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, entry_id : String, metric : Int, month : Int) : SelectQuery[hourly_ua_prtn_entryTableAccessor, hourly_ua_prtn_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.metric eqs metric)
.and(_.month eqs month)
  }
 def query(partner_id : Int, entry_id : String, metric : Int, month : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_entryTableAccessor, hourly_ua_prtn_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.metric eqs metric)
.and(_.month eqs month)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
def query(partner_id_list : List[Int], entry_id_list : List[String], metric_list : List[Int], month_list : List[Int]) : SelectQuery[hourly_ua_prtn_entryTableAccessor, hourly_ua_prtn_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.metric in metric_list)
.and(_.month in month_list)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], metric_list : List[Int], month_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_entryTableAccessor, hourly_ua_prtn_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.metric in metric_list)
.and(_.month in month_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }

}