package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class hourly_ua_prtn_entry_cv1_cv2_clst_cv3TableAccessor extends CassandraTable[hourly_ua_prtn_entry_cv1_cv2_clst_cv3TableAccessor, hourly_ua_prtn_entry_cv1_cv2_clst_cv3Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var3 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_entry_cv1_cv2_clst_cv3"

  def fromRow(row: Row): hourly_ua_prtn_entry_cv1_cv2_clst_cv3Row = {
    hourly_ua_prtn_entry_cv1_cv2_clst_cv3Row(
      partner_id(row), 
entry_id(row), 
custom_var1(row), 
custom_var2(row), 
metric(row), 
year(row), 
hour(row), 
custom_var3(row), 
value(row)
    )
  }

  def store(entity: hourly_ua_prtn_entry_cv1_cv2_clst_cv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.entry_id, entity.entry_id)
.value(_.custom_var1, entity.custom_var1)
.value(_.custom_var2, entity.custom_var2)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.custom_var3, entity.custom_var3)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, entry_id : String, custom_var1 : String, custom_var2 : String, metric : Int, year : Int) : SelectQuery[hourly_ua_prtn_entry_cv1_cv2_clst_cv3TableAccessor, hourly_ua_prtn_entry_cv1_cv2_clst_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.custom_var1 eqs custom_var1)
.and(_.custom_var2 eqs custom_var2)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, entry_id : String, custom_var1 : String, custom_var2 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_entry_cv1_cv2_clst_cv3TableAccessor, hourly_ua_prtn_entry_cv1_cv2_clst_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.custom_var1 eqs custom_var1)
.and(_.custom_var2 eqs custom_var2)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id : Int, entry_id : String, custom_var1 : String, custom_var2 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, custom_var3Start : String, custom_var3End : String) : SelectQuery[hourly_ua_prtn_entry_cv1_cv2_clst_cv3TableAccessor, hourly_ua_prtn_entry_cv1_cv2_clst_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.custom_var1 eqs custom_var1)
.and(_.custom_var2 eqs custom_var2)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var3 gte custom_var3Start)
.and(_.custom_var3 lt custom_var3End)
  }
def query(partner_id_list : List[Int], entry_id_list : List[String], custom_var1_list : List[String], custom_var2_list : List[String], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_ua_prtn_entry_cv1_cv2_clst_cv3TableAccessor, hourly_ua_prtn_entry_cv1_cv2_clst_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.custom_var1 in custom_var1_list)
.and(_.custom_var2 in custom_var2_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], custom_var1_list : List[String], custom_var2_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_entry_cv1_cv2_clst_cv3TableAccessor, hourly_ua_prtn_entry_cv1_cv2_clst_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.custom_var1 in custom_var1_list)
.and(_.custom_var2 in custom_var2_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], custom_var1_list : List[String], custom_var2_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, custom_var3Start : String, custom_var3End : String) : SelectQuery[hourly_ua_prtn_entry_cv1_cv2_clst_cv3TableAccessor, hourly_ua_prtn_entry_cv1_cv2_clst_cv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.custom_var1 in custom_var1_list)
.and(_.custom_var2 in custom_var2_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var3 gte custom_var3Start)
.and(_.custom_var3 lt custom_var3End)
  }

}