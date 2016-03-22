package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class minutely_ua_clst_categoryTableAccessor extends CassandraTable[minutely_ua_clst_categoryTableAccessor, minutely_ua_clst_categoryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object category extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_clst_category"

  def fromRow(row: Row): minutely_ua_clst_categoryRow = {
    minutely_ua_clst_categoryRow(
      partner_id(row), 
metric(row), 
minute(row), 
category(row), 
value(row)
    )
  }

  def store(entity: minutely_ua_clst_categoryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.category, entity.category)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, metric : Int) : SelectQuery[minutely_ua_clst_categoryTableAccessor, minutely_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
  }
 def query(partner_id : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_clst_categoryTableAccessor, minutely_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partner_id : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, categoryStart : String, categoryEnd : String) : SelectQuery[minutely_ua_clst_categoryTableAccessor, minutely_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.category gte categoryStart)
.and(_.category lt categoryEnd)
  }
def query(partner_id_list : List[Int], metric_list : List[Int]) : SelectQuery[minutely_ua_clst_categoryTableAccessor, minutely_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
  }
 def query(partner_id_list : List[Int], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_clst_categoryTableAccessor, minutely_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partner_id_list : List[Int], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime, categoryStart : String, categoryEnd : String) : SelectQuery[minutely_ua_clst_categoryTableAccessor, minutely_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.category gte categoryStart)
.and(_.category lt categoryEnd)
  }

}