package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnEntryCv2TableAccessor extends CassandraTable[MinutelyUaPrtnEntryCv2TableAccessor, MinutelyUaPrtnEntryCv2Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_entry_cv2"

  def fromRow(row: Row): MinutelyUaPrtnEntryCv2Row = {
    MinutelyUaPrtnEntryCv2Row(
      partner_id(row), 
entry_id(row), 
custom_var2(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnEntryCv2Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.custom_var2, entity.customVar2)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, customVar2 : String, metric : Int) : SelectQuery[MinutelyUaPrtnEntryCv2TableAccessor, MinutelyUaPrtnEntryCv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, entryId : String, customVar2 : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryCv2TableAccessor, MinutelyUaPrtnEntryCv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], customVar2List : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnEntryCv2TableAccessor, MinutelyUaPrtnEntryCv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar2List : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryCv2TableAccessor, MinutelyUaPrtnEntryCv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}