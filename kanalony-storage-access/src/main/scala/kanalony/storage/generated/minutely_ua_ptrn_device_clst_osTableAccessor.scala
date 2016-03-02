package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class minutely_ua_ptrn_device_clst_osTableAccessor extends CassandraTable[minutely_ua_ptrn_device_clst_osTableAccessor, minutely_ua_ptrn_device_clst_osRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object device extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object os extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_ptrn_device_clst_os"

  def fromRow(row: Row): minutely_ua_ptrn_device_clst_osRow = {
    minutely_ua_ptrn_device_clst_osRow(
      partner_id(row), 
device(row), 
metric(row), 
minute(row), 
os(row), 
value(row)
    )
  }

  def store(entity: minutely_ua_ptrn_device_clst_osRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.device, entity.device)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.os, entity.os)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, device : Int, metric : Int) : SelectQuery[minutely_ua_ptrn_device_clst_osTableAccessor, minutely_ua_ptrn_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.device eqs device)
.and(_.metric eqs metric)
  }
 def query(partner_id : Int, device : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_ptrn_device_clst_osTableAccessor, minutely_ua_ptrn_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.device eqs device)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partner_id : Int, device : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, osStart : Int, osEnd : Int) : SelectQuery[minutely_ua_ptrn_device_clst_osTableAccessor, minutely_ua_ptrn_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.device eqs device)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.os gte osStart)
.and(_.os lt osEnd)
  }
def query(partner_id_list : List[Int], device_list : List[Int], metric_list : List[Int]) : SelectQuery[minutely_ua_ptrn_device_clst_osTableAccessor, minutely_ua_ptrn_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.device in device_list)
.and(_.metric in metric_list)
  }
 def query(partner_id_list : List[Int], device_list : List[Int], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_ptrn_device_clst_osTableAccessor, minutely_ua_ptrn_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.device in device_list)
.and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partner_id_list : List[Int], device_list : List[Int], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime, osStart : Int, osEnd : Int) : SelectQuery[minutely_ua_ptrn_device_clst_osTableAccessor, minutely_ua_ptrn_device_clst_osRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.device in device_list)
.and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.os gte osStart)
.and(_.os lt osEnd)
  }

}