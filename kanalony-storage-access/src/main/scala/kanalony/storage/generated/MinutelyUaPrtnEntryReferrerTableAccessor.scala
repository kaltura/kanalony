package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnEntryReferrerTableAccessor extends CassandraTable[MinutelyUaPrtnEntryReferrerTableAccessor, MinutelyUaPrtnEntryReferrerRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object referrer extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_entry_referrer"

  def fromRow(row: Row): MinutelyUaPrtnEntryReferrerRow = {
    MinutelyUaPrtnEntryReferrerRow(
      partner_id(row), 
entry_id(row), 
referrer(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnEntryReferrerRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.referrer, entity.referrer)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, referrer : String, metric : Int) : SelectQuery[MinutelyUaPrtnEntryReferrerTableAccessor, MinutelyUaPrtnEntryReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.referrer eqs referrer)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, entryId : String, referrer : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryReferrerTableAccessor, MinutelyUaPrtnEntryReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.referrer eqs referrer)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], referrerList : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnEntryReferrerTableAccessor, MinutelyUaPrtnEntryReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.referrer in referrerList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], referrerList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryReferrerTableAccessor, MinutelyUaPrtnEntryReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.referrer in referrerList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}