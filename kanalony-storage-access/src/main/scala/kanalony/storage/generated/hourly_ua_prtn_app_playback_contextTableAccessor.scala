package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class hourly_ua_prtn_app_playback_contextTableAccessor extends CassandraTable[hourly_ua_prtn_app_playback_contextTableAccessor, hourly_ua_prtn_app_playback_contextRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object application extends StringColumn(this)with PartitionKey[String]
object playback_context extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_app_playback_context"

  def fromRow(row: Row): hourly_ua_prtn_app_playback_contextRow = {
    hourly_ua_prtn_app_playback_contextRow(
      partner_id(row), 
application(row), 
playback_context(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: hourly_ua_prtn_app_playback_contextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.application, entity.application)
.value(_.playback_context, entity.playback_context)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, application : String, playback_context : String, metric : Int, year : Int) : SelectQuery[hourly_ua_prtn_app_playback_contextTableAccessor, hourly_ua_prtn_app_playback_contextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.application eqs application)
.and(_.playback_context eqs playback_context)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, application : String, playback_context : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_app_playback_contextTableAccessor, hourly_ua_prtn_app_playback_contextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.application eqs application)
.and(_.playback_context eqs playback_context)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
def query(partner_id_list : List[Int], application_list : List[String], playback_context_list : List[String], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_ua_prtn_app_playback_contextTableAccessor, hourly_ua_prtn_app_playback_contextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.application in application_list)
.and(_.playback_context in playback_context_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], application_list : List[String], playback_context_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_app_playback_contextTableAccessor, hourly_ua_prtn_app_playback_contextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.application in application_list)
.and(_.playback_context in playback_context_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }

}