package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class minutely_ua_prtn_cv3TableAccessor extends CassandraTable[minutely_ua_prtn_cv3TableAccessor, minutely_ua_prtn_cv3Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var3 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_cv3"

  def fromRow(row: Row): minutely_ua_prtn_cv3Row = {
    minutely_ua_prtn_cv3Row(
      partner_id(row), 
custom_var3(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: minutely_ua_prtn_cv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.custom_var3, entity.custom_var3)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, custom_var3 : String, metric : Int) : SelectQuery[minutely_ua_prtn_cv3TableAccessor, minutely_ua_prtn_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.custom_var3 eqs custom_var3)
.and(_.metric eqs metric)
  }
 def query(partner_id : Int, custom_var3 : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_cv3TableAccessor, minutely_ua_prtn_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.custom_var3 eqs custom_var3)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partner_id_list : List[Int], custom_var3_list : List[String], metric_list : List[Int]) : SelectQuery[minutely_ua_prtn_cv3TableAccessor, minutely_ua_prtn_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.custom_var3 in custom_var3_list)
.and(_.metric in metric_list)
  }
 def query(partner_id_list : List[Int], custom_var3_list : List[String], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_cv3TableAccessor, minutely_ua_prtn_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.custom_var3 in custom_var3_list)
.and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}