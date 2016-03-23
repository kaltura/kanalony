package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnEntryDomainClstReferrerTableAccessor extends CassandraTable[MinutelyUaPrtnEntryDomainClstReferrerTableAccessor, MinutelyUaPrtnEntryDomainClstReferrerRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object domain extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object referrer extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_entry_domain_clst_referrer"

  def fromRow(row: Row): MinutelyUaPrtnEntryDomainClstReferrerRow = {
    MinutelyUaPrtnEntryDomainClstReferrerRow(
      partner_id(row), 
entry_id(row), 
domain(row), 
metric(row), 
minute(row), 
referrer(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnEntryDomainClstReferrerRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.domain, entity.domain)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.referrer, entity.referrer)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, domain : String, metric : Int) : SelectQuery[MinutelyUaPrtnEntryDomainClstReferrerTableAccessor, MinutelyUaPrtnEntryDomainClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.domain eqs domain)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, entryId : String, domain : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryDomainClstReferrerTableAccessor, MinutelyUaPrtnEntryDomainClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, entryId : String, domain : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, referrerStart : String, referrerEnd : String) : SelectQuery[MinutelyUaPrtnEntryDomainClstReferrerTableAccessor, MinutelyUaPrtnEntryDomainClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.referrer gte referrerStart)
.and(_.referrer lt referrerEnd)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], domainList : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnEntryDomainClstReferrerTableAccessor, MinutelyUaPrtnEntryDomainClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.domain in domainList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], domainList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryDomainClstReferrerTableAccessor, MinutelyUaPrtnEntryDomainClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], domainList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, referrerStart : String, referrerEnd : String) : SelectQuery[MinutelyUaPrtnEntryDomainClstReferrerTableAccessor, MinutelyUaPrtnEntryDomainClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.referrer gte referrerStart)
.and(_.referrer lt referrerEnd)
  }

}