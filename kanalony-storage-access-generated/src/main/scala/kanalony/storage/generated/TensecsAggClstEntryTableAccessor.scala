package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggClstEntryTableAccessor extends CassandraTable[TensecsAggClstEntryTableAccessor, TensecsAggClstEntryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object day extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_clst_entry"

  def fromRow(row: Row): TensecsAggClstEntryRow = {
    TensecsAggClstEntryRow(
      partner_id(row), 
day(row), 
metric(row), 
tensecs(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: TensecsAggClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.day, entity.day)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, day : Int, metric : String) : SelectQuery[TensecsAggClstEntryTableAccessor, TensecsAggClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.day eqs day)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, day : Int, metric : String, tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsAggClstEntryTableAccessor, TensecsAggClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerId : Int, day : Int, metric : String, tensecsStart : DateTime, tensecsEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[TensecsAggClstEntryTableAccessor, TensecsAggClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.day eqs day)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }
def query(partnerIdList : List[Int], dayList : List[Int], metricList : List[String]) : SelectQuery[TensecsAggClstEntryTableAccessor, TensecsAggClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.day in dayList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], dayList : List[Int], metricList : List[String], tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsAggClstEntryTableAccessor, TensecsAggClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.day in dayList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerIdList : List[Int], dayList : List[Int], metricList : List[String], tensecsStart : DateTime, tensecsEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[TensecsAggClstEntryTableAccessor, TensecsAggClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.day in dayList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }

}