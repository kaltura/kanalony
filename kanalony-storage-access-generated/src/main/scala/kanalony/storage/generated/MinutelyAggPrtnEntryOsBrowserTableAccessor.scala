package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnEntryOsBrowserTableAccessor extends CassandraTable[MinutelyAggPrtnEntryOsBrowserTableAccessor, MinutelyAggPrtnEntryOsBrowserRow] with RootConnector with IMinutelyAggPrtnEntryOsBrowserTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object browser extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_entry_os_browser"

  def fromRow(row: Row): MinutelyAggPrtnEntryOsBrowserRow = {
    MinutelyAggPrtnEntryOsBrowserRow(
      partner_id(row), 
entry_id(row), 
operating_system(row), 
browser(row), 
metric(row), 
day(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnEntryOsBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.operating_system, entity.operatingSystem)
.value(_.browser, entity.browser)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, operatingSystem : Int, browser : Int, metric : String, day : Int) : Future[List[MinutelyAggPrtnEntryOsBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.operating_system eqs operatingSystem)
.and(_.browser eqs browser)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, operatingSystem : Int, browser : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryOsBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.operating_system eqs operatingSystem)
.and(_.browser eqs browser)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnEntryOsBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.operating_system in operatingSystemList)
.and(_.browser in browserList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryOsBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.operating_system in operatingSystemList)
.and(_.browser in browserList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnEntryOsBrowserRow(partnerId:Int,
entryId:String,
operatingSystem:Int,
browser:Int,
metric:String,
day:Int,
minute:DateTime,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnEntryOsBrowserTableAccessor {
  def query(partnerId : Int, entryId : String, operatingSystem : Int, browser : Int, metric : String, day : Int) : Future[List[MinutelyAggPrtnEntryOsBrowserRow]]
 def query(partnerId : Int, entryId : String, operatingSystem : Int, browser : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryOsBrowserRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnEntryOsBrowserRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryOsBrowserRow]]
}