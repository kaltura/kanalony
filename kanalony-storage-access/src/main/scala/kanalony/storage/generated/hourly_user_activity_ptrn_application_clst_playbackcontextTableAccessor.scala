package kanalony.storage.generated

import com.websudos.phantom.builder._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.dsl._

import scala.concurrent.Future

abstract class hourly_user_activity_ptrn_application_clst_playbackcontextTableAccessor extends CassandraTable[hourly_user_activity_ptrn_application_clst_playbackcontextTableAccessor, hourly_user_activity_ptrn_application_clst_playbackcontextRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object application extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object playbackContext extends StringColumn(this)with ClusteringOrder[String] with Ascending
object count extends LongColumn(this)


  override def tableName = "hourly_user_activity_ptrn_application_clst_playbackcontext"

  def fromRow(row: Row): hourly_user_activity_ptrn_application_clst_playbackcontextRow = {
    hourly_user_activity_ptrn_application_clst_playbackcontextRow(
      partner_id(row), 
application(row), 
metric(row), 
year(row), 
hour(row), 
playbackContext(row), 
count(row)
    )
  }

  def store(entity: hourly_user_activity_ptrn_application_clst_playbackcontextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.application, entity.application)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.playbackContext, entity.playbackContext)
.value(_.count, entity.count)

      .future()
  }

  def query(partner_id : Int, application : String, metric : Int, year : Int) : SelectQuery[hourly_user_activity_ptrn_application_clst_playbackcontextTableAccessor, hourly_user_activity_ptrn_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, application : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_ptrn_application_clst_playbackcontextTableAccessor, hourly_user_activity_ptrn_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id : Int, application : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : SelectQuery[hourly_user_activity_ptrn_application_clst_playbackcontextTableAccessor, hourly_user_activity_ptrn_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.playbackContext gte playbackContextStart)
.and(_.playbackContext lt playbackContextEnd)
  }
def query(partner_id_list : List[Int], application_list : List[String], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_user_activity_ptrn_application_clst_playbackcontextTableAccessor, hourly_user_activity_ptrn_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.application in application_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], application_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_ptrn_application_clst_playbackcontextTableAccessor, hourly_user_activity_ptrn_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.application in application_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id_list : List[Int], application_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : SelectQuery[hourly_user_activity_ptrn_application_clst_playbackcontextTableAccessor, hourly_user_activity_ptrn_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.application in application_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.playbackContext gte playbackContextStart)
.and(_.playbackContext lt playbackContextEnd)
  }

}