package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnDomainClstEntryTableAccessor extends CassandraTable[MinutelyAggPrtnDomainClstEntryTableAccessor, MinutelyAggPrtnDomainClstEntryRow] with RootConnector with IMinutelyAggPrtnDomainClstEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object domain extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_domain_clst_entry"

  def fromRow(row: Row): MinutelyAggPrtnDomainClstEntryRow = {
    MinutelyAggPrtnDomainClstEntryRow(
      partner_id(row), 
domain(row), 
day(row), 
metric(row), 
minute(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnDomainClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.domain, entity.domain)
.value(_.day, entity.day)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, domain : String, day : Int, metric : String) : Future[List[MinutelyAggPrtnDomainClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.day eqs day)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, domain : String, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDomainClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, domain : String, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnDomainClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], domainList : List[String], dayList : List[Int], metricList : List[String]) : Future[List[MinutelyAggPrtnDomainClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.day in dayList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], domainList : List[String], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDomainClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.day in dayList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], domainList : List[String], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnDomainClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
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
case class MinutelyAggPrtnDomainClstEntryRow(partnerId:Int,
domain:String,
day:Int,
metric:String,
minute:DateTime,
entryId:String,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnDomainClstEntryTableAccessor {
  def query(partnerId : Int, domain : String, day : Int, metric : String) : Future[List[MinutelyAggPrtnDomainClstEntryRow]]
 def query(partnerId : Int, domain : String, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDomainClstEntryRow]]
 def query(partnerId : Int, domain : String, day : Int, metric : String, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnDomainClstEntryRow]]
def query(partnerIdList : List[Int], domainList : List[String], dayList : List[Int], metricList : List[String]) : Future[List[MinutelyAggPrtnDomainClstEntryRow]]
 def query(partnerIdList : List[Int], domainList : List[String], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDomainClstEntryRow]]
 def query(partnerIdList : List[Int], domainList : List[String], dayList : List[Int], metricList : List[String], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[MinutelyAggPrtnDomainClstEntryRow]]
}