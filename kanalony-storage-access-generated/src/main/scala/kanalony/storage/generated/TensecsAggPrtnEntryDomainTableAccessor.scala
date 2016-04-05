package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnEntryDomainTableAccessor extends CassandraTable[TensecsAggPrtnEntryDomainTableAccessor, TensecsAggPrtnEntryDomainRow] with RootConnector with ITensecsAggPrtnEntryDomainTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object domain extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_entry_domain"

  def fromRow(row: Row): TensecsAggPrtnEntryDomainRow = {
    TensecsAggPrtnEntryDomainRow(
      partner_id(row), 
entry_id(row), 
domain(row), 
metric(row), 
day(row), 
tensecs(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnEntryDomainRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.domain, entity.domain)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.tensecs, entity.tensecs)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, domain : String, metric : String, day : Int) : Future[List[TensecsAggPrtnEntryDomainRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, domain : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryDomainRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], domainList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnEntryDomainRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], domainList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryDomainRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnEntryDomainRow(partnerId:Int,
entryId:String,
domain:String,
metric:String,
day:Int,
tensecs:DateTime,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnEntryDomainTableAccessor {
  def query(partnerId : Int, entryId : String, domain : String, metric : String, day : Int) : Future[List[TensecsAggPrtnEntryDomainRow]]
 def query(partnerId : Int, entryId : String, domain : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryDomainRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], domainList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnEntryDomainRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], domainList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryDomainRow]]
}