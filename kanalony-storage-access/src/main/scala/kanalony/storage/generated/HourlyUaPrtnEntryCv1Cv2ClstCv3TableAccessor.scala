package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnEntryCv1Cv2ClstCv3TableAccessor extends CassandraTable[HourlyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, HourlyUaPrtnEntryCv1Cv2ClstCv3Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var3 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_entry_cv1_cv2_clst_cv3"

  def fromRow(row: Row): HourlyUaPrtnEntryCv1Cv2ClstCv3Row = {
    HourlyUaPrtnEntryCv1Cv2ClstCv3Row(
      partner_id(row), 
entry_id(row), 
custom_var1(row), 
custom_var2(row), 
metric(row), 
year(row), 
hour(row), 
custom_var3(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnEntryCv1Cv2ClstCv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.custom_var1, entity.customVar1)
.value(_.custom_var2, entity.customVar2)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.custom_var3, entity.customVar3)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, metric : Int, year : Int) : SelectQuery[HourlyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, HourlyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, HourlyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, customVar3Start : String, customVar3End : String) : SelectQuery[HourlyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, HourlyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var3 gte customVar3Start)
.and(_.custom_var3 lt customVar3End)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], metricList : List[Int], yearList : List[Int]) : SelectQuery[HourlyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, HourlyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.year in yearList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, HourlyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, customVar3Start : String, customVar3End : String) : SelectQuery[HourlyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, HourlyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var3 gte customVar3Start)
.and(_.custom_var3 lt customVar3End)
  }

}