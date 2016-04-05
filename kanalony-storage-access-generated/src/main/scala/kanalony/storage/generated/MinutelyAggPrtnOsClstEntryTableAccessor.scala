package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnOsClstEntryTableAccessor extends CassandraTable[MinutelyAggPrtnOsClstEntryTableAccessor, MinutelyAggPrtnOsClstEntryRow] with RootConnector with IMinutelyAggPrtnOsClstEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object day extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_os_clst_entry"

  def fromRow(row: Row): MinutelyAggPrtnOsClstEntryRow = {
    MinutelyAggPrtnOsClstEntryRow(
      partner_id(row), 
operating_system(row), 
day(row), 
metric(row), 
minute(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnOsClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.operating_system, entity.operatingSystem)
.value(_.day, entity.day)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, operatingSystem : Int, day : Int, metric : String) : Future[List[MinutelyAggPrtnOsClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.day eqs day)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, operatingSystem : Int, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnOsClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, operatingSystem : Int, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnOsClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], operatingSystemList : List[Int], dayList : List[Int], metricList : List[String]) : Future[List[MinutelyAggPrtnOsClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.day in dayList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnOsClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.day in dayList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnOsClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
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
case class MinutelyAggPrtnOsClstEntryRow(partnerId:Int,
operatingSystem:Int,
day:Int,
metric:String,
minute:DateTime,
entryId:String,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnOsClstEntryTableAccessor {
  def query(partnerId : Int, operatingSystem : Int, day : Int, metric : String) : Future[List[MinutelyAggPrtnOsClstEntryRow]]
 def query(partnerId : Int, operatingSystem : Int, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnOsClstEntryRow]]
 def query(partnerId : Int, operatingSystem : Int, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnOsClstEntryRow]]
def query(partnerIdList : List[Int], operatingSystemList : List[Int], dayList : List[Int], metricList : List[String]) : Future[List[MinutelyAggPrtnOsClstEntryRow]]
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnOsClstEntryRow]]
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnOsClstEntryRow]]
}