package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnEntryTableAccessor extends CassandraTable[TensecsAggPrtnEntryTableAccessor, TensecsAggPrtnEntryRow] with RootConnector with ITensecsAggPrtnEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_entry"

  def fromRow(row: Row): TensecsAggPrtnEntryRow = {
    TensecsAggPrtnEntryRow(
      partner_id(row), 
entry_id(row), 
day(row), 
metric(row), 
tensecs(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.day, entity.day)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, day : Int, metric : String) : Future[List[TensecsAggPrtnEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.day eqs day)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, day : Int, metric : String, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], dayList : List[Int], metricList : List[String]) : Future[List[TensecsAggPrtnEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.day in dayList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], dayList : List[Int], metricList : List[String], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.day in dayList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnEntryRow(partnerId:Int,
entryId:String,
day:Int,
metric:String,
tensecs:DateTime,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnEntryTableAccessor {
  def query(partnerId : Int, entryId : String, day : Int, metric : String) : Future[List[TensecsAggPrtnEntryRow]]
 def query(partnerId : Int, entryId : String, day : Int, metric : String, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], dayList : List[Int], metricList : List[String]) : Future[List[TensecsAggPrtnEntryRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], dayList : List[Int], metricList : List[String], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryRow]]
}