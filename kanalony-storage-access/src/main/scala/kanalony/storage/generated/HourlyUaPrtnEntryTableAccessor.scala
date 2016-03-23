package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnEntryTableAccessor extends CassandraTable[HourlyUaPrtnEntryTableAccessor, HourlyUaPrtnEntryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object month extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_entry"

  def fromRow(row: Row): HourlyUaPrtnEntryRow = {
    HourlyUaPrtnEntryRow(
      partner_id(row), 
entry_id(row), 
metric(row), 
month(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.metric, entity.metric)
.value(_.month, entity.month)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, metric : Int, month : Int) : SelectQuery[HourlyUaPrtnEntryTableAccessor, HourlyUaPrtnEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.month eqs month)
  }
 def query(partnerId : Int, entryId : String, metric : Int, month : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnEntryTableAccessor, HourlyUaPrtnEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.month eqs month)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int], monthList : List[Int]) : SelectQuery[HourlyUaPrtnEntryTableAccessor, HourlyUaPrtnEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.month in monthList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int], monthList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnEntryTableAccessor, HourlyUaPrtnEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.month in monthList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }

}