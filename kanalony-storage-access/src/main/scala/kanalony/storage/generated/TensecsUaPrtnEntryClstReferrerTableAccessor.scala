package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsUaPrtnEntryClstReferrerTableAccessor extends CassandraTable[TensecsUaPrtnEntryClstReferrerTableAccessor, TensecsUaPrtnEntryClstReferrerRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object referrer extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_ua_prtn_entry_clst_referrer"

  def fromRow(row: Row): TensecsUaPrtnEntryClstReferrerRow = {
    TensecsUaPrtnEntryClstReferrerRow(
      partner_id(row), 
entry_id(row), 
metric(row), 
tensecs(row), 
referrer(row), 
value(row)
    )
  }

  def store(entity: TensecsUaPrtnEntryClstReferrerRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.referrer, entity.referrer)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, metric : Int) : SelectQuery[TensecsUaPrtnEntryClstReferrerTableAccessor, TensecsUaPrtnEntryClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, entryId : String, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnEntryClstReferrerTableAccessor, TensecsUaPrtnEntryClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerId : Int, entryId : String, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime, referrerStart : String, referrerEnd : String) : SelectQuery[TensecsUaPrtnEntryClstReferrerTableAccessor, TensecsUaPrtnEntryClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.referrer gte referrerStart)
.and(_.referrer lt referrerEnd)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int]) : SelectQuery[TensecsUaPrtnEntryClstReferrerTableAccessor, TensecsUaPrtnEntryClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnEntryClstReferrerTableAccessor, TensecsUaPrtnEntryClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, referrerStart : String, referrerEnd : String) : SelectQuery[TensecsUaPrtnEntryClstReferrerTableAccessor, TensecsUaPrtnEntryClstReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.referrer gte referrerStart)
.and(_.referrer lt referrerEnd)
  }

}