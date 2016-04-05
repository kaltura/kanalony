package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnCv3ClstEntryTableAccessor extends CassandraTable[MinutelyAggPrtnCv3ClstEntryTableAccessor, MinutelyAggPrtnCv3ClstEntryRow] with RootConnector with IMinutelyAggPrtnCv3ClstEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var3 extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_cv3_clst_entry"

  def fromRow(row: Row): MinutelyAggPrtnCv3ClstEntryRow = {
    MinutelyAggPrtnCv3ClstEntryRow(
      partner_id(row), 
custom_var3(row), 
day(row), 
metric(row), 
minute(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnCv3ClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.custom_var3, entity.customVar3)
.value(_.day, entity.day)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, customVar3 : String, day : Int, metric : String) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var3 eqs customVar3)
.and(_.day eqs day)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, customVar3 : String, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var3 eqs customVar3)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, customVar3 : String, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var3 eqs customVar3)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], customVar3List : List[String], dayList : List[Int], metricList : List[String]) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var3 in customVar3List)
.and(_.day in dayList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], customVar3List : List[String], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var3 in customVar3List)
.and(_.day in dayList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], customVar3List : List[String], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var3 in customVar3List)
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
case class MinutelyAggPrtnCv3ClstEntryRow(partnerId:Int,
customVar3:String,
day:Int,
metric:String,
minute:DateTime,
entryId:String,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnCv3ClstEntryTableAccessor {
  def query(partnerId : Int, customVar3 : String, day : Int, metric : String) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]]
 def query(partnerId : Int, customVar3 : String, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]]
 def query(partnerId : Int, customVar3 : String, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]]
def query(partnerIdList : List[Int], customVar3List : List[String], dayList : List[Int], metricList : List[String]) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]]
 def query(partnerIdList : List[Int], customVar3List : List[String], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]]
 def query(partnerIdList : List[Int], customVar3List : List[String], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnCv3ClstEntryRow]]
}