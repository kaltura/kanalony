package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnEntryCv1Cv2ClstCv3TableAccessor extends CassandraTable[MinutelyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, MinutelyUaPrtnEntryCv1Cv2ClstCv3Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var3 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_entry_cv1_cv2_clst_cv3"

  def fromRow(row: Row): MinutelyUaPrtnEntryCv1Cv2ClstCv3Row = {
    MinutelyUaPrtnEntryCv1Cv2ClstCv3Row(
      partner_id(row), 
entry_id(row), 
custom_var1(row), 
custom_var2(row), 
metric(row), 
minute(row), 
custom_var3(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnEntryCv1Cv2ClstCv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.custom_var1, entity.customVar1)
.value(_.custom_var2, entity.customVar2)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.custom_var3, entity.customVar3)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, metric : Int) : SelectQuery[MinutelyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, MinutelyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, MinutelyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, customVar3Start : String, customVar3End : String) : SelectQuery[MinutelyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, MinutelyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.custom_var3 gte customVar3Start)
.and(_.custom_var3 lt customVar3End)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, MinutelyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, MinutelyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, customVar3Start : String, customVar3End : String) : SelectQuery[MinutelyUaPrtnEntryCv1Cv2ClstCv3TableAccessor, MinutelyUaPrtnEntryCv1Cv2ClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.custom_var3 gte customVar3Start)
.and(_.custom_var3 lt customVar3End)
  }

}