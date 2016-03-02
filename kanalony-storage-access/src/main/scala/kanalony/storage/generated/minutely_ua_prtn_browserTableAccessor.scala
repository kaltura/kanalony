package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class minutely_ua_prtn_browserTableAccessor extends CassandraTable[minutely_ua_prtn_browserTableAccessor, minutely_ua_prtn_browserRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object browser extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_browser"

  def fromRow(row: Row): minutely_ua_prtn_browserRow = {
    minutely_ua_prtn_browserRow(
      partner_id(row), 
browser(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: minutely_ua_prtn_browserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.browser, entity.browser)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, browser : Int, metric : Int) : SelectQuery[minutely_ua_prtn_browserTableAccessor, minutely_ua_prtn_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.browser eqs browser)
.and(_.metric eqs metric)
  }
 def query(partner_id : Int, browser : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_browserTableAccessor, minutely_ua_prtn_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.browser eqs browser)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partner_id_list : List[Int], browser_list : List[Int], metric_list : List[Int]) : SelectQuery[minutely_ua_prtn_browserTableAccessor, minutely_ua_prtn_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.browser in browser_list)
.and(_.metric in metric_list)
  }
 def query(partner_id_list : List[Int], browser_list : List[Int], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_browserTableAccessor, minutely_ua_prtn_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.browser in browser_list)
.and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}