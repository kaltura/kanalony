package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnEntryAppTableAccessor extends CassandraTable[TensecsAggPrtnEntryAppTableAccessor, TensecsAggPrtnEntryAppRow] with RootConnector with ITensecsAggPrtnEntryAppTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object application extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_entry_app"

  def fromRow(row: Row): TensecsAggPrtnEntryAppRow = {
    TensecsAggPrtnEntryAppRow(
      partner_id(row), 
entry_id(row), 
application(row), 
metric(row), 
day(row), 
tensecs(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnEntryAppRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.application, entity.application)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.tensecs, entity.tensecs)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, application : String, metric : String, day : Int) : Future[List[TensecsAggPrtnEntryAppRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, application : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryAppRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnEntryAppRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryAppRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnEntryAppRow(partnerId:Int,
entryId:String,
application:String,
metric:String,
day:Int,
tensecs:DateTime,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnEntryAppTableAccessor {
  def query(partnerId : Int, entryId : String, application : String, metric : String, day : Int) : Future[List[TensecsAggPrtnEntryAppRow]]
 def query(partnerId : Int, entryId : String, application : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryAppRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnEntryAppRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], applicationList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryAppRow]]
}