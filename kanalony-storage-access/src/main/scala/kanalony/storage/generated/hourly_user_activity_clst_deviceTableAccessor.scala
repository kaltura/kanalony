package kanalony.storage.generated

import com.websudos.phantom.builder._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.dsl._

import scala.concurrent.Future

abstract class hourly_user_activity_clst_deviceTableAccessor extends CassandraTable[hourly_user_activity_clst_deviceTableAccessor, hourly_user_activity_clst_deviceRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object device extends StringColumn(this)with ClusteringOrder[String] with Ascending
object count extends LongColumn(this)


  override def tableName = "hourly_user_activity_clst_device"

  def fromRow(row: Row): hourly_user_activity_clst_deviceRow = {
    hourly_user_activity_clst_deviceRow(
      partner_id(row), 
metric(row), 
year(row), 
hour(row), 
device(row), 
count(row)
    )
  }

  def store(entity: hourly_user_activity_clst_deviceRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.device, entity.device)
.value(_.count, entity.count)

      .future()
  }

  def query(partner_id : Int, metric : Int, year : Int) : SelectQuery[hourly_user_activity_clst_deviceTableAccessor, hourly_user_activity_clst_deviceRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_clst_deviceTableAccessor, hourly_user_activity_clst_deviceRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id : Int, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, deviceStart : String, deviceEnd : String) : SelectQuery[hourly_user_activity_clst_deviceTableAccessor, hourly_user_activity_clst_deviceRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.device gte deviceStart)
.and(_.device lt deviceEnd)
  }
def query(partner_id_list : List[Int], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_user_activity_clst_deviceTableAccessor, hourly_user_activity_clst_deviceRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_clst_deviceTableAccessor, hourly_user_activity_clst_deviceRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id_list : List[Int], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, deviceStart : String, deviceEnd : String) : SelectQuery[hourly_user_activity_clst_deviceTableAccessor, hourly_user_activity_clst_deviceRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.device gte deviceStart)
.and(_.device lt deviceEnd)
  }

}