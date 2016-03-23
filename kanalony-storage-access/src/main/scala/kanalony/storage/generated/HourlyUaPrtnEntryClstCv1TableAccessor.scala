package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnEntryClstCv1TableAccessor extends CassandraTable[HourlyUaPrtnEntryClstCv1TableAccessor, HourlyUaPrtnEntryClstCv1Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var1 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_entry_clst_cv1"

  def fromRow(row: Row): HourlyUaPrtnEntryClstCv1Row = {
    HourlyUaPrtnEntryClstCv1Row(
      partner_id(row), 
entry_id(row), 
metric(row), 
year(row), 
hour(row), 
custom_var1(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnEntryClstCv1Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.custom_var1, entity.customVar1)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, metric : Int, year : Int) : SelectQuery[HourlyUaPrtnEntryClstCv1TableAccessor, HourlyUaPrtnEntryClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partnerId : Int, entryId : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnEntryClstCv1TableAccessor, HourlyUaPrtnEntryClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerId : Int, entryId : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, customVar1Start : String, customVar1End : String) : SelectQuery[HourlyUaPrtnEntryClstCv1TableAccessor, HourlyUaPrtnEntryClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var1 gte customVar1Start)
.and(_.custom_var1 lt customVar1End)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int], yearList : List[Int]) : SelectQuery[HourlyUaPrtnEntryClstCv1TableAccessor, HourlyUaPrtnEntryClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.year in yearList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnEntryClstCv1TableAccessor, HourlyUaPrtnEntryClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, customVar1Start : String, customVar1End : String) : SelectQuery[HourlyUaPrtnEntryClstCv1TableAccessor, HourlyUaPrtnEntryClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var1 gte customVar1Start)
.and(_.custom_var1 lt customVar1End)
  }

}