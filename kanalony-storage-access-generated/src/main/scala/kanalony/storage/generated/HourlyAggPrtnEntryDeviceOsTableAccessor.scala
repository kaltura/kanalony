package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryDeviceOsTableAccessor extends CassandraTable[HourlyAggPrtnEntryDeviceOsTableAccessor, HourlyAggPrtnEntryDeviceOsRow] with RootConnector with IHourlyAggPrtnEntryDeviceOsTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object device extends IntColumn(this)with PartitionKey[Int]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_device_os"

  def fromRow(row: Row): HourlyAggPrtnEntryDeviceOsRow = {
    HourlyAggPrtnEntryDeviceOsRow(
      partner_id(row), 
entry_id(row), 
device(row), 
operating_system(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryDeviceOsRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.device, entity.device)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, device : Int, operatingSystem : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryDeviceOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.device eqs device)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, device : Int, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryDeviceOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.device eqs device)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryDeviceOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.device in deviceList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryDeviceOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.device in deviceList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnEntryDeviceOsRow(partnerId:Int,
entryId:String,
device:Int,
operatingSystem:Int,
metric:String,
year:Int,
hour:DateTime,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryDeviceOsTableAccessor {
  def query(partnerId : Int, entryId : String, device : Int, operatingSystem : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryDeviceOsRow]]
 def query(partnerId : Int, entryId : String, device : Int, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryDeviceOsRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryDeviceOsRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], deviceList : List[Int], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryDeviceOsRow]]
}