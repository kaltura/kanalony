package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnEntryDeviceOsTableAccessor extends CassandraTable[MinutelyUaPrtnEntryDeviceOsTableAccessor, MinutelyUaPrtnEntryDeviceOsRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object device extends IntColumn(this)with PartitionKey[Int]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_entry_device_os"

  def fromRow(row: Row): MinutelyUaPrtnEntryDeviceOsRow = {
    MinutelyUaPrtnEntryDeviceOsRow(
      partner_id(row), 
entry_id(row), 
device(row), 
operating_system(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnEntryDeviceOsRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.device, entity.device)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, device : Int, operatingSystem : Int, metric : Int) : SelectQuery[MinutelyUaPrtnEntryDeviceOsTableAccessor, MinutelyUaPrtnEntryDeviceOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.device eqs device)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, entryId : String, device : Int, operatingSystem : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryDeviceOsTableAccessor, MinutelyUaPrtnEntryDeviceOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.device eqs device)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnEntryDeviceOsTableAccessor, MinutelyUaPrtnEntryDeviceOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.device in deviceList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryDeviceOsTableAccessor, MinutelyUaPrtnEntryDeviceOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.device in deviceList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}