package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnEntryClstOsTableAccessor extends CassandraTable[TensecsAggPrtnEntryClstOsTableAccessor, TensecsAggPrtnEntryClstOsRow] with RootConnector with ITensecsAggPrtnEntryClstOsTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object operating_system extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_entry_clst_os"

  def fromRow(row: Row): TensecsAggPrtnEntryClstOsRow = {
    TensecsAggPrtnEntryClstOsRow(
      partner_id(row), 
entry_id(row), 
metric(row), 
day(row), 
tensecs(row), 
operating_system(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnEntryClstOsRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.tensecs, entity.tensecs)
.value(_.operating_system, entity.operatingSystem)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, metric : String, day : Int) : Future[List[TensecsAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[TensecsAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.operating_system gte operatingSystemStart)
.and(_.operating_system lt operatingSystemEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[TensecsAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.operating_system gte operatingSystemStart)
.and(_.operating_system lt operatingSystemEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnEntryClstOsRow(partnerId:Int,
entryId:String,
metric:String,
day:Int,
tensecs:DateTime,
operatingSystem:Int,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnEntryClstOsTableAccessor {
  def query(partnerId : Int, entryId : String, metric : String, day : Int) : Future[List[TensecsAggPrtnEntryClstOsRow]]
 def query(partnerId : Int, entryId : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryClstOsRow]]
 def query(partnerId : Int, entryId : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[TensecsAggPrtnEntryClstOsRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnEntryClstOsRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryClstOsRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[TensecsAggPrtnEntryClstOsRow]]
}