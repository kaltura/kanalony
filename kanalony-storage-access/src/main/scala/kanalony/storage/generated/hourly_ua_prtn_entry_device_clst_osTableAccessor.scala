package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class hourly_ua_prtn_entry_device_clst_osTableAccessor extends CassandraTable[hourly_ua_prtn_entry_device_clst_osTableAccessor, hourly_ua_prtn_entry_device_clst_osRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object device extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object operating_system extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_entry_device_clst_os"

  def fromRow(row: Row): hourly_ua_prtn_entry_device_clst_osRow = {
    hourly_ua_prtn_entry_device_clst_osRow(
      partner_id(row), 
entry_id(row), 
device(row), 
metric(row), 
year(row), 
hour(row), 
operating_system(row), 
value(row)
    )
  }

  def store(entity: hourly_ua_prtn_entry_device_clst_osRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.entry_id, entity.entry_id)
.value(_.device, entity.device)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.operating_system, entity.operating_system)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, entry_id : String, device : Int, metric : Int, year : Int) : SelectQuery[hourly_ua_prtn_entry_device_clst_osTableAccessor, hourly_ua_prtn_entry_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.device eqs device)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, entry_id : String, device : Int, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_entry_device_clst_osTableAccessor, hourly_ua_prtn_entry_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.device eqs device)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id : Int, entry_id : String, device : Int, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, operating_systemStart : Int, operating_systemEnd : Int) : SelectQuery[hourly_ua_prtn_entry_device_clst_osTableAccessor, hourly_ua_prtn_entry_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.device eqs device)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.operating_system gte operating_systemStart)
.and(_.operating_system lt operating_systemEnd)
  }
def query(partner_id_list : List[Int], entry_id_list : List[String], device_list : List[Int], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_ua_prtn_entry_device_clst_osTableAccessor, hourly_ua_prtn_entry_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.device in device_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], device_list : List[Int], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_entry_device_clst_osTableAccessor, hourly_ua_prtn_entry_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.device in device_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], device_list : List[Int], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, operating_systemStart : Int, operating_systemEnd : Int) : SelectQuery[hourly_ua_prtn_entry_device_clst_osTableAccessor, hourly_ua_prtn_entry_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.device in device_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.operating_system gte operating_systemStart)
.and(_.operating_system lt operating_systemEnd)
  }

}