package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnEntryDeviceOsTableAccessor extends CassandraTable[MinutelyAggPrtnEntryDeviceOsTableAccessor, MinutelyAggPrtnEntryDeviceOsRow] with RootConnector with IMinutelyAggPrtnEntryDeviceOsTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object device extends IntColumn(this)with PartitionKey[Int]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_entry_device_os"

  def fromRow(row: Row): MinutelyAggPrtnEntryDeviceOsRow = {
    MinutelyAggPrtnEntryDeviceOsRow(
      partner_id(row), 
entry_id(row), 
device(row), 
operating_system(row), 
metric(row), 
day(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnEntryDeviceOsRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.device, entity.device)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, device : Int, operatingSystem : Int, metric : String, day : Int) : Future[List[MinutelyAggPrtnEntryDeviceOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.device eqs device)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, device : Int, operatingSystem : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryDeviceOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.device eqs device)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnEntryDeviceOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.device in deviceList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryDeviceOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.device in deviceList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnEntryDeviceOsRow(partnerId:Int,
entryId:String,
device:Int,
operatingSystem:Int,
metric:String,
day:Int,
minute:DateTime,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnEntryDeviceOsTableAccessor {
  def query(partnerId : Int, entryId : String, device : Int, operatingSystem : Int, metric : String, day : Int) : Future[List[MinutelyAggPrtnEntryDeviceOsRow]]
 def query(partnerId : Int, entryId : String, device : Int, operatingSystem : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryDeviceOsRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnEntryDeviceOsRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryDeviceOsRow]]
}