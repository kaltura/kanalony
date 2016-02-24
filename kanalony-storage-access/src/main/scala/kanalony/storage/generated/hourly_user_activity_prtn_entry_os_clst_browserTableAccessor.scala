package kanalony.storage.generated

import com.websudos.phantom.builder._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.dsl._

import scala.concurrent.Future

abstract class hourly_user_activity_prtn_entry_os_clst_browserTableAccessor extends CassandraTable[hourly_user_activity_prtn_entry_os_clst_browserTableAccessor, hourly_user_activity_prtn_entry_os_clst_browserRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object os extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object browser extends StringColumn(this)with ClusteringOrder[String] with Ascending
object count extends LongColumn(this)


  override def tableName = "hourly_user_activity_prtn_entry_os_clst_browser"

  def fromRow(row: Row): hourly_user_activity_prtn_entry_os_clst_browserRow = {
    hourly_user_activity_prtn_entry_os_clst_browserRow(
      partner_id(row), 
entry_id(row), 
os(row), 
metric(row), 
year(row), 
hour(row), 
browser(row), 
count(row)
    )
  }

  def store(entity: hourly_user_activity_prtn_entry_os_clst_browserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.entry_id, entity.entry_id)
.value(_.os, entity.os)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.browser, entity.browser)
.value(_.count, entity.count)

      .future()
  }

  def query(partner_id : Int, entry_id : String, os : String, metric : Int, year : Int) : SelectQuery[hourly_user_activity_prtn_entry_os_clst_browserTableAccessor, hourly_user_activity_prtn_entry_os_clst_browserRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.os eqs os)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, entry_id : String, os : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_prtn_entry_os_clst_browserTableAccessor, hourly_user_activity_prtn_entry_os_clst_browserRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.os eqs os)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id : Int, entry_id : String, os : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, browserStart : String, browserEnd : String) : SelectQuery[hourly_user_activity_prtn_entry_os_clst_browserTableAccessor, hourly_user_activity_prtn_entry_os_clst_browserRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.os eqs os)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
  }
def query(partner_id_list : List[Int], entry_id_list : List[String], os_list : List[String], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_user_activity_prtn_entry_os_clst_browserTableAccessor, hourly_user_activity_prtn_entry_os_clst_browserRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.os in os_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], os_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_prtn_entry_os_clst_browserTableAccessor, hourly_user_activity_prtn_entry_os_clst_browserRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.os in os_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], os_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, browserStart : String, browserEnd : String) : SelectQuery[hourly_user_activity_prtn_entry_os_clst_browserTableAccessor, hourly_user_activity_prtn_entry_os_clst_browserRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.os in os_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
  }

}