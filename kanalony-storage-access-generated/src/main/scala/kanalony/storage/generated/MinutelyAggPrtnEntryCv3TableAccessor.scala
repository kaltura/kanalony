package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnEntryCv3TableAccessor extends CassandraTable[MinutelyAggPrtnEntryCv3TableAccessor, MinutelyAggPrtnEntryCv3Row] with RootConnector with IMinutelyAggPrtnEntryCv3TableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object custom_var3 extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_entry_cv3"

  def fromRow(row: Row): MinutelyAggPrtnEntryCv3Row = {
    MinutelyAggPrtnEntryCv3Row(
      partner_id(row), 
entry_id(row), 
custom_var3(row), 
metric(row), 
day(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnEntryCv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.custom_var3, entity.customVar3)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, customVar3 : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnEntryCv3Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var3 eqs customVar3)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, customVar3 : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryCv3Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var3 eqs customVar3)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], customVar3List : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnEntryCv3Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var3 in customVar3List)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar3List : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryCv3Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var3 in customVar3List)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnEntryCv3Row(partnerId:Int,
entryId:String,
customVar3:String,
metric:String,
day:Int,
minute:DateTime,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnEntryCv3TableAccessor {
  def query(partnerId : Int, entryId : String, customVar3 : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnEntryCv3Row]]
 def query(partnerId : Int, entryId : String, customVar3 : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryCv3Row]]
def query(partnerIdList : List[Int], entryIdList : List[String], customVar3List : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnEntryCv3Row]]
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar3List : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnEntryCv3Row]]
}