package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class hourly_ua_prtn_cv1_cv2_cv3TableAccessor extends CassandraTable[hourly_ua_prtn_cv1_cv2_cv3TableAccessor, hourly_ua_prtn_cv1_cv2_cv3Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object custom_var3 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_cv1_cv2_cv3"

  def fromRow(row: Row): hourly_ua_prtn_cv1_cv2_cv3Row = {
    hourly_ua_prtn_cv1_cv2_cv3Row(
      partner_id(row), 
custom_var1(row), 
custom_var2(row), 
custom_var3(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: hourly_ua_prtn_cv1_cv2_cv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.custom_var1, entity.custom_var1)
.value(_.custom_var2, entity.custom_var2)
.value(_.custom_var3, entity.custom_var3)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, custom_var1 : String, custom_var2 : String, custom_var3 : String, metric : Int, year : Int) : SelectQuery[hourly_ua_prtn_cv1_cv2_cv3TableAccessor, hourly_ua_prtn_cv1_cv2_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.custom_var1 eqs custom_var1)
.and(_.custom_var2 eqs custom_var2)
.and(_.custom_var3 eqs custom_var3)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, custom_var1 : String, custom_var2 : String, custom_var3 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_cv1_cv2_cv3TableAccessor, hourly_ua_prtn_cv1_cv2_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.custom_var1 eqs custom_var1)
.and(_.custom_var2 eqs custom_var2)
.and(_.custom_var3 eqs custom_var3)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
def query(partner_id_list : List[Int], custom_var1_list : List[String], custom_var2_list : List[String], custom_var3_list : List[String], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_ua_prtn_cv1_cv2_cv3TableAccessor, hourly_ua_prtn_cv1_cv2_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.custom_var1 in custom_var1_list)
.and(_.custom_var2 in custom_var2_list)
.and(_.custom_var3 in custom_var3_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], custom_var1_list : List[String], custom_var2_list : List[String], custom_var3_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_cv1_cv2_cv3TableAccessor, hourly_ua_prtn_cv1_cv2_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.custom_var1 in custom_var1_list)
.and(_.custom_var2 in custom_var2_list)
.and(_.custom_var3 in custom_var3_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }

}