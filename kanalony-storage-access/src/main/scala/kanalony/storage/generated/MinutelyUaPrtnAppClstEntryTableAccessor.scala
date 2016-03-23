package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnAppClstEntryTableAccessor extends CassandraTable[MinutelyUaPrtnAppClstEntryTableAccessor, MinutelyUaPrtnAppClstEntryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object application extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_app_clst_entry"

  def fromRow(row: Row): MinutelyUaPrtnAppClstEntryRow = {
    MinutelyUaPrtnAppClstEntryRow(
      partner_id(row), 
application(row), 
metric(row), 
minute(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnAppClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.application, entity.application)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, application : String, metric : Int) : SelectQuery[MinutelyUaPrtnAppClstEntryTableAccessor, MinutelyUaPrtnAppClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, application : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnAppClstEntryTableAccessor, MinutelyUaPrtnAppClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, application : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[MinutelyUaPrtnAppClstEntryTableAccessor, MinutelyUaPrtnAppClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }
def query(partnerIdList : List[Int], applicationList : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnAppClstEntryTableAccessor, MinutelyUaPrtnAppClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], applicationList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnAppClstEntryTableAccessor, MinutelyUaPrtnAppClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], applicationList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[MinutelyUaPrtnAppClstEntryTableAccessor, MinutelyUaPrtnAppClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }

}