package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnDeviceClstEntryTableAccessor extends CassandraTable[MinutelyAggPrtnDeviceClstEntryTableAccessor, MinutelyAggPrtnDeviceClstEntryRow] with RootConnector with IMinutelyAggPrtnDeviceClstEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object device extends IntColumn(this)with PartitionKey[Int]
object day extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_device_clst_entry"

  def fromRow(row: Row): MinutelyAggPrtnDeviceClstEntryRow = {
    MinutelyAggPrtnDeviceClstEntryRow(
      partner_id(row), 
device(row), 
day(row), 
metric(row), 
minute(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnDeviceClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.device, entity.device)
.value(_.day, entity.day)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, device : Int, day : Int, metric : String) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.device eqs device)
.and(_.day eqs day)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, device : Int, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.device eqs device)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, device : Int, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.device eqs device)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], deviceList : List[Int], dayList : List[Int], metricList : List[String]) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.device in deviceList)
.and(_.day in dayList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], deviceList : List[Int], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.device in deviceList)
.and(_.day in dayList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], deviceList : List[Int], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.device in deviceList)
.and(_.day in dayList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnDeviceClstEntryRow(partnerId:Int,
device:Int,
day:Int,
metric:String,
minute:DateTime,
entryId:String,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnDeviceClstEntryTableAccessor {
  def query(partnerId : Int, device : Int, day : Int, metric : String) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]]
 def query(partnerId : Int, device : Int, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]]
 def query(partnerId : Int, device : Int, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]]
def query(partnerIdList : List[Int], deviceList : List[Int], dayList : List[Int], metricList : List[String]) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]]
 def query(partnerIdList : List[Int], deviceList : List[Int], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]]
 def query(partnerIdList : List[Int], deviceList : List[Int], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnDeviceClstEntryRow]]
}