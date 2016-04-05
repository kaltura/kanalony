package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggClstDeviceTableAccessor extends CassandraTable[MinutelyAggClstDeviceTableAccessor, MinutelyAggClstDeviceRow] with RootConnector with IMinutelyAggClstDeviceTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object device extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_clst_device"

  def fromRow(row: Row): MinutelyAggClstDeviceRow = {
    MinutelyAggClstDeviceRow(
      partner_id(row), 
metric(row), 
day(row), 
minute(row), 
device(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggClstDeviceRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.device, entity.device)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : String, day : Int) : Future[List[MinutelyAggClstDeviceRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstDeviceRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, deviceStart : Int, deviceEnd : Int) : Future[List[MinutelyAggClstDeviceRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.device gte deviceStart)
.and(_.device lt deviceEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggClstDeviceRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstDeviceRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, deviceStart : Int, deviceEnd : Int) : Future[List[MinutelyAggClstDeviceRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.device gte deviceStart)
.and(_.device lt deviceEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggClstDeviceRow(partnerId:Int,
metric:String,
day:Int,
minute:DateTime,
device:Int,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggClstDeviceTableAccessor {
  def query(partnerId : Int, metric : String, day : Int) : Future[List[MinutelyAggClstDeviceRow]]
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstDeviceRow]]
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, deviceStart : Int, deviceEnd : Int) : Future[List[MinutelyAggClstDeviceRow]]
def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggClstDeviceRow]]
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstDeviceRow]]
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, deviceStart : Int, deviceEnd : Int) : Future[List[MinutelyAggClstDeviceRow]]
}