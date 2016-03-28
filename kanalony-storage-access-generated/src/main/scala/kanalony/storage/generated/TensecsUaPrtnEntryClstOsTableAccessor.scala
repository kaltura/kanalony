package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsUaPrtnEntryClstOsTableAccessor extends CassandraTable[TensecsUaPrtnEntryClstOsTableAccessor, TensecsUaPrtnEntryClstOsRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object operating_system extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_ua_prtn_entry_clst_os"

  def fromRow(row: Row): TensecsUaPrtnEntryClstOsRow = {
    TensecsUaPrtnEntryClstOsRow(
      partner_id(row), 
entry_id(row), 
metric(row), 
tensecs(row), 
operating_system(row), 
value(row)
    )
  }

  def store(entity: TensecsUaPrtnEntryClstOsRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.operating_system, entity.operatingSystem)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, metric : Int) : SelectQuery[TensecsUaPrtnEntryClstOsTableAccessor, TensecsUaPrtnEntryClstOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, entryId : String, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnEntryClstOsTableAccessor, TensecsUaPrtnEntryClstOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerId : Int, entryId : String, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : SelectQuery[TensecsUaPrtnEntryClstOsTableAccessor, TensecsUaPrtnEntryClstOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.operating_system gte operatingSystemStart)
.and(_.operating_system lt operatingSystemEnd)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int]) : SelectQuery[TensecsUaPrtnEntryClstOsTableAccessor, TensecsUaPrtnEntryClstOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnEntryClstOsTableAccessor, TensecsUaPrtnEntryClstOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : SelectQuery[TensecsUaPrtnEntryClstOsTableAccessor, TensecsUaPrtnEntryClstOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.operating_system gte operatingSystemStart)
.and(_.operating_system lt operatingSystemEnd)
  }

}