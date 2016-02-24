package kanalony.storage.generated

import com.websudos.phantom.builder._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.dsl._

import scala.concurrent.Future

abstract class hourly_user_activity_prtn_cf1_clst_cf2TableAccessor extends CassandraTable[hourly_user_activity_prtn_cf1_clst_cf2TableAccessor, hourly_user_activity_prtn_cf1_clst_cf2Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object cf1 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object cf2 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object count extends LongColumn(this)


  override def tableName = "hourly_user_activity_prtn_cf1_clst_cf2"

  def fromRow(row: Row): hourly_user_activity_prtn_cf1_clst_cf2Row = {
    hourly_user_activity_prtn_cf1_clst_cf2Row(
      partner_id(row), 
cf1(row), 
metric(row), 
year(row), 
hour(row), 
cf2(row), 
count(row)
    )
  }

  def store(entity: hourly_user_activity_prtn_cf1_clst_cf2Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.cf1, entity.cf1)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.cf2, entity.cf2)
.value(_.count, entity.count)

      .future()
  }

  def query(partner_id : Int, cf1 : String, metric : Int, year : Int) : SelectQuery[hourly_user_activity_prtn_cf1_clst_cf2TableAccessor, hourly_user_activity_prtn_cf1_clst_cf2Row, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.cf1 eqs cf1)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, cf1 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_prtn_cf1_clst_cf2TableAccessor, hourly_user_activity_prtn_cf1_clst_cf2Row, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.cf1 eqs cf1)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id : Int, cf1 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, cf2Start : String, cf2End : String) : SelectQuery[hourly_user_activity_prtn_cf1_clst_cf2TableAccessor, hourly_user_activity_prtn_cf1_clst_cf2Row, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.cf1 eqs cf1)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.cf2 gte cf2Start)
.and(_.cf2 lt cf2End)
  }
def query(partner_id_list : List[Int], cf1_list : List[String], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_user_activity_prtn_cf1_clst_cf2TableAccessor, hourly_user_activity_prtn_cf1_clst_cf2Row, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.cf1 in cf1_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], cf1_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_prtn_cf1_clst_cf2TableAccessor, hourly_user_activity_prtn_cf1_clst_cf2Row, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.cf1 in cf1_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id_list : List[Int], cf1_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, cf2Start : String, cf2End : String) : SelectQuery[hourly_user_activity_prtn_cf1_clst_cf2TableAccessor, hourly_user_activity_prtn_cf1_clst_cf2Row, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.cf1 in cf1_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.cf2 gte cf2Start)
.and(_.cf2 lt cf2End)
  }

}