package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnEntryCv1Cv2TableAccessor extends CassandraTable[HourlyUaPrtnEntryCv1Cv2TableAccessor, HourlyUaPrtnEntryCv1Cv2Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_entry_cv1_cv2"

  def fromRow(row: Row): HourlyUaPrtnEntryCv1Cv2Row = {
    HourlyUaPrtnEntryCv1Cv2Row(
      partner_id(row), 
entry_id(row), 
custom_var1(row), 
custom_var2(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnEntryCv1Cv2Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.custom_var1, entity.customVar1)
.value(_.custom_var2, entity.customVar2)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, metric : Int, year : Int) : SelectQuery[HourlyUaPrtnEntryCv1Cv2TableAccessor, HourlyUaPrtnEntryCv1Cv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnEntryCv1Cv2TableAccessor, HourlyUaPrtnEntryCv1Cv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], metricList : List[Int], yearList : List[Int]) : SelectQuery[HourlyUaPrtnEntryCv1Cv2TableAccessor, HourlyUaPrtnEntryCv1Cv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.year in yearList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnEntryCv1Cv2TableAccessor, HourlyUaPrtnEntryCv1Cv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }

}