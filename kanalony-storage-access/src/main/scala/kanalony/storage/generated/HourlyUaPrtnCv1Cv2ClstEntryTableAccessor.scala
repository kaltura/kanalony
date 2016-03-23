package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnCv1Cv2ClstEntryTableAccessor extends CassandraTable[HourlyUaPrtnCv1Cv2ClstEntryTableAccessor, HourlyUaPrtnCv1Cv2ClstEntryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_cv1_cv2_clst_entry"

  def fromRow(row: Row): HourlyUaPrtnCv1Cv2ClstEntryRow = {
    HourlyUaPrtnCv1Cv2ClstEntryRow(
      partner_id(row), 
custom_var1(row), 
custom_var2(row), 
year(row), 
metric(row), 
hour(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnCv1Cv2ClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.custom_var1, entity.customVar1)
.value(_.custom_var2, entity.customVar2)
.value(_.year, entity.year)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, customVar1 : String, customVar2 : String, year : Int, metric : Int) : SelectQuery[HourlyUaPrtnCv1Cv2ClstEntryTableAccessor, HourlyUaPrtnCv1Cv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.year eqs year)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, customVar1 : String, customVar2 : String, year : Int, metric : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnCv1Cv2ClstEntryTableAccessor, HourlyUaPrtnCv1Cv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerId : Int, customVar1 : String, customVar2 : String, year : Int, metric : Int, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[HourlyUaPrtnCv1Cv2ClstEntryTableAccessor, HourlyUaPrtnCv1Cv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }
def query(partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], yearList : List[Int], metricList : List[Int]) : SelectQuery[HourlyUaPrtnCv1Cv2ClstEntryTableAccessor, HourlyUaPrtnCv1Cv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.year in yearList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], yearList : List[Int], metricList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnCv1Cv2ClstEntryTableAccessor, HourlyUaPrtnCv1Cv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], yearList : List[Int], metricList : List[Int], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[HourlyUaPrtnCv1Cv2ClstEntryTableAccessor, HourlyUaPrtnCv1Cv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }

}