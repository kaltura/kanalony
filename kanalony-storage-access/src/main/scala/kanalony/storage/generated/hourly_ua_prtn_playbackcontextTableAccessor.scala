package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class hourly_ua_prtn_playbackcontextTableAccessor extends CassandraTable[hourly_ua_prtn_playbackcontextTableAccessor, hourly_ua_prtn_playbackcontextRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object playbackContext extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_playbackcontext"

  def fromRow(row: Row): hourly_ua_prtn_playbackcontextRow = {
    hourly_ua_prtn_playbackcontextRow(
      partner_id(row), 
playbackContext(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: hourly_ua_prtn_playbackcontextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.playbackContext, entity.playbackContext)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, playbackContext : String, metric : Int, year : Int) : SelectQuery[hourly_ua_prtn_playbackcontextTableAccessor, hourly_ua_prtn_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.playbackContext eqs playbackContext)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, playbackContext : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_playbackcontextTableAccessor, hourly_ua_prtn_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.playbackContext eqs playbackContext)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
def query(partner_id_list : List[Int], playbackContext_list : List[String], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_ua_prtn_playbackcontextTableAccessor, hourly_ua_prtn_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.playbackContext in playbackContext_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], playbackContext_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_playbackcontextTableAccessor, hourly_ua_prtn_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.playbackContext in playbackContext_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }

}