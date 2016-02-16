package kanalony.storage.generated

import com.websudos.phantom.builder._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.dsl._

import scala.concurrent.Future

abstract class hourly_user_activity_clst_entryTableAccessor extends CassandraTable[hourly_user_activity_clst_entryTableAccessor, hourly_user_activity_clst_entryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object event_type extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object count extends LongColumn(this)


  override def tableName = "hourly_user_activity_clst_entry"

  def fromRow(row: Row): hourly_user_activity_clst_entryRow = {
    hourly_user_activity_clst_entryRow(
      partner_id(row), 
event_type(row), 
year(row), 
hour(row), 
entry_id(row), 
count(row)
    )
  }

  def store(entity: hourly_user_activity_clst_entryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.event_type, entity.event_type)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entry_id)
.value(_.count, entity.count)

      .future()
  }

  def query(partner_id : Int, event_type : Int, year : Int) : SelectQuery[hourly_user_activity_clst_entryTableAccessor, hourly_user_activity_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.event_type eqs event_type)
.and(_.year eqs year)
  }
 def query(partner_id : Int, event_type : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_clst_entryTableAccessor, hourly_user_activity_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.event_type eqs event_type)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id : Int, event_type : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, entry_idStart : String, entry_idEnd : String) : SelectQuery[hourly_user_activity_clst_entryTableAccessor, hourly_user_activity_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.event_type eqs event_type)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entry_idStart)
.and(_.entry_id lt entry_idEnd)
  }
def query(partner_id_list : List[Int], event_type_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_user_activity_clst_entryTableAccessor, hourly_user_activity_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.event_type in event_type_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], event_type_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_clst_entryTableAccessor, hourly_user_activity_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.event_type in event_type_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id_list : List[Int], event_type_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, entry_idStart : String, entry_idEnd : String) : SelectQuery[hourly_user_activity_clst_entryTableAccessor, hourly_user_activity_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.event_type in event_type_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entry_idStart)
.and(_.entry_id lt entry_idEnd)
  }

}