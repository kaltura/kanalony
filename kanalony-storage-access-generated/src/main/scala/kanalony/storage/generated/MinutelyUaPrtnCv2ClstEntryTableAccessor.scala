package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnCv2ClstEntryTableAccessor extends CassandraTable[MinutelyUaPrtnCv2ClstEntryTableAccessor, MinutelyUaPrtnCv2ClstEntryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_cv2_clst_entry"

  def fromRow(row: Row): MinutelyUaPrtnCv2ClstEntryRow = {
    MinutelyUaPrtnCv2ClstEntryRow(
      partner_id(row), 
custom_var2(row), 
day(row), 
metric(row), 
minute(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnCv2ClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.custom_var2, entity.customVar2)
.value(_.day, entity.day)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, customVar2 : String, day : Int, metric : Int) : SelectQuery[MinutelyUaPrtnCv2ClstEntryTableAccessor, MinutelyUaPrtnCv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var2 eqs customVar2)
.and(_.day eqs day)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, customVar2 : String, day : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnCv2ClstEntryTableAccessor, MinutelyUaPrtnCv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var2 eqs customVar2)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, customVar2 : String, day : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[MinutelyUaPrtnCv2ClstEntryTableAccessor, MinutelyUaPrtnCv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var2 eqs customVar2)
.and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }
def query(partnerIdList : List[Int], customVar2List : List[String], dayList : List[Int], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnCv2ClstEntryTableAccessor, MinutelyUaPrtnCv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var2 in customVar2List)
.and(_.day in dayList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], customVar2List : List[String], dayList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnCv2ClstEntryTableAccessor, MinutelyUaPrtnCv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var2 in customVar2List)
.and(_.day in dayList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], customVar2List : List[String], dayList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[MinutelyUaPrtnCv2ClstEntryTableAccessor, MinutelyUaPrtnCv2ClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var2 in customVar2List)
.and(_.day in dayList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }

}