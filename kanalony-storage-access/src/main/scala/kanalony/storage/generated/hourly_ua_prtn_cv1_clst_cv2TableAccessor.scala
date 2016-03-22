package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class hourly_ua_prtn_cv1_clst_cv2TableAccessor extends CassandraTable[hourly_ua_prtn_cv1_clst_cv2TableAccessor, hourly_ua_prtn_cv1_clst_cv2Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var2 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_cv1_clst_cv2"

  def fromRow(row: Row): hourly_ua_prtn_cv1_clst_cv2Row = {
    hourly_ua_prtn_cv1_clst_cv2Row(
      partner_id(row), 
custom_var1(row), 
metric(row), 
year(row), 
hour(row), 
custom_var2(row), 
value(row)
    )
  }

  def store(entity: hourly_ua_prtn_cv1_clst_cv2Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.custom_var1, entity.custom_var1)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.custom_var2, entity.custom_var2)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, custom_var1 : String, metric : Int, year : Int) : SelectQuery[hourly_ua_prtn_cv1_clst_cv2TableAccessor, hourly_ua_prtn_cv1_clst_cv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.custom_var1 eqs custom_var1)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, custom_var1 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_cv1_clst_cv2TableAccessor, hourly_ua_prtn_cv1_clst_cv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.custom_var1 eqs custom_var1)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id : Int, custom_var1 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, custom_var2Start : String, custom_var2End : String) : SelectQuery[hourly_ua_prtn_cv1_clst_cv2TableAccessor, hourly_ua_prtn_cv1_clst_cv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.custom_var1 eqs custom_var1)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var2 gte custom_var2Start)
.and(_.custom_var2 lt custom_var2End)
  }
def query(partner_id_list : List[Int], custom_var1_list : List[String], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_ua_prtn_cv1_clst_cv2TableAccessor, hourly_ua_prtn_cv1_clst_cv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.custom_var1 in custom_var1_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], custom_var1_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_cv1_clst_cv2TableAccessor, hourly_ua_prtn_cv1_clst_cv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.custom_var1 in custom_var1_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id_list : List[Int], custom_var1_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, custom_var2Start : String, custom_var2End : String) : SelectQuery[hourly_ua_prtn_cv1_clst_cv2TableAccessor, hourly_ua_prtn_cv1_clst_cv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.custom_var1 in custom_var1_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var2 gte custom_var2Start)
.and(_.custom_var2 lt custom_var2End)
  }

}