package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class hourly_ua_prtn_cv1_cv2_clst_entryTableAccessor extends CassandraTable[hourly_ua_prtn_cv1_cv2_clst_entryTableAccessor, hourly_ua_prtn_cv1_cv2_clst_entryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_cv1_cv2_clst_entry"

  def fromRow(row: Row): hourly_ua_prtn_cv1_cv2_clst_entryRow = {
    hourly_ua_prtn_cv1_cv2_clst_entryRow(
      partner_id(row), 
custom_var1(row), 
custom_var2(row), 
year(row), 
metric(row), 
hour(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: hourly_ua_prtn_cv1_cv2_clst_entryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.custom_var1, entity.custom_var1)
.value(_.custom_var2, entity.custom_var2)
.value(_.year, entity.year)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entry_id)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, custom_var1 : String, custom_var2 : String, year : Int, metric : Int) : SelectQuery[hourly_ua_prtn_cv1_cv2_clst_entryTableAccessor, hourly_ua_prtn_cv1_cv2_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.custom_var1 eqs custom_var1)
.and(_.custom_var2 eqs custom_var2)
.and(_.year eqs year)
.and(_.metric eqs metric)
  }
 def query(partner_id : Int, custom_var1 : String, custom_var2 : String, year : Int, metric : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_cv1_cv2_clst_entryTableAccessor, hourly_ua_prtn_cv1_cv2_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.custom_var1 eqs custom_var1)
.and(_.custom_var2 eqs custom_var2)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id : Int, custom_var1 : String, custom_var2 : String, year : Int, metric : Int, hourStart : DateTime, hourEnd : DateTime, entry_idStart : String, entry_idEnd : String) : SelectQuery[hourly_ua_prtn_cv1_cv2_clst_entryTableAccessor, hourly_ua_prtn_cv1_cv2_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.custom_var1 eqs custom_var1)
.and(_.custom_var2 eqs custom_var2)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entry_idStart)
.and(_.entry_id lt entry_idEnd)
  }
def query(partner_id_list : List[Int], custom_var1_list : List[String], custom_var2_list : List[String], year_list : List[Int], metric_list : List[Int]) : SelectQuery[hourly_ua_prtn_cv1_cv2_clst_entryTableAccessor, hourly_ua_prtn_cv1_cv2_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.custom_var1 in custom_var1_list)
.and(_.custom_var2 in custom_var2_list)
.and(_.year in year_list)
.and(_.metric in metric_list)
  }
 def query(partner_id_list : List[Int], custom_var1_list : List[String], custom_var2_list : List[String], year_list : List[Int], metric_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_cv1_cv2_clst_entryTableAccessor, hourly_ua_prtn_cv1_cv2_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.custom_var1 in custom_var1_list)
.and(_.custom_var2 in custom_var2_list)
.and(_.year in year_list)
.and(_.metric in metric_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id_list : List[Int], custom_var1_list : List[String], custom_var2_list : List[String], year_list : List[Int], metric_list : List[Int], hourStart : DateTime, hourEnd : DateTime, entry_idStart : String, entry_idEnd : String) : SelectQuery[hourly_ua_prtn_cv1_cv2_clst_entryTableAccessor, hourly_ua_prtn_cv1_cv2_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.custom_var1 in custom_var1_list)
.and(_.custom_var2 in custom_var2_list)
.and(_.year in year_list)
.and(_.metric in metric_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entry_idStart)
.and(_.entry_id lt entry_idEnd)
  }

}