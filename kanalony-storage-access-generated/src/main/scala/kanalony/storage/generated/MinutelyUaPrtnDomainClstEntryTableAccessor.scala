package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnDomainClstEntryTableAccessor extends CassandraTable[MinutelyUaPrtnDomainClstEntryTableAccessor, MinutelyUaPrtnDomainClstEntryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object domain extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_domain_clst_entry"

  def fromRow(row: Row): MinutelyUaPrtnDomainClstEntryRow = {
    MinutelyUaPrtnDomainClstEntryRow(
      partner_id(row), 
domain(row), 
day(row), 
metric(row), 
minute(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnDomainClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.domain, entity.domain)
.value(_.day, entity.day)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, domain : String, day : Int, metric : Int) : SelectQuery[MinutelyUaPrtnDomainClstEntryTableAccessor, MinutelyUaPrtnDomainClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.day eqs day)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, domain : String, day : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnDomainClstEntryTableAccessor, MinutelyUaPrtnDomainClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, domain : String, day : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[MinutelyUaPrtnDomainClstEntryTableAccessor, MinutelyUaPrtnDomainClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }
def query(partnerIdList : List[Int], domainList : List[String], dayList : List[Int], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnDomainClstEntryTableAccessor, MinutelyUaPrtnDomainClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.day in dayList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], domainList : List[String], dayList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnDomainClstEntryTableAccessor, MinutelyUaPrtnDomainClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.day in dayList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], domainList : List[String], dayList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[MinutelyUaPrtnDomainClstEntryTableAccessor, MinutelyUaPrtnDomainClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.day in dayList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }

}