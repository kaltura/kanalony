package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class hourly_ua_prtn_country_clst_os_browserTableAccessor extends CassandraTable[hourly_ua_prtn_country_clst_os_browserTableAccessor, hourly_ua_prtn_country_clst_os_browserRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object operating_system extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object browser extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_country_clst_os_browser"

  def fromRow(row: Row): hourly_ua_prtn_country_clst_os_browserRow = {
    hourly_ua_prtn_country_clst_os_browserRow(
      partner_id(row), 
country(row), 
metric(row), 
year(row), 
hour(row), 
operating_system(row), 
browser(row), 
value(row)
    )
  }

  def store(entity: hourly_ua_prtn_country_clst_os_browserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.country, entity.country)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.operating_system, entity.operating_system)
.value(_.browser, entity.browser)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, country : String, metric : Int, year : Int) : SelectQuery[hourly_ua_prtn_country_clst_os_browserTableAccessor, hourly_ua_prtn_country_clst_os_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partner_id : Int, country : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_country_clst_os_browserTableAccessor, hourly_ua_prtn_country_clst_os_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id : Int, country : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, operating_systemStart : Int, operating_systemEnd : Int) : SelectQuery[hourly_ua_prtn_country_clst_os_browserTableAccessor, hourly_ua_prtn_country_clst_os_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.operating_system gte operating_systemStart)
.and(_.operating_system lt operating_systemEnd)
  }
 def query(partner_id : Int, country : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, operating_systemStart : Int, operating_systemEnd : Int, browserStart : Int, browserEnd : Int) : SelectQuery[hourly_ua_prtn_country_clst_os_browserTableAccessor, hourly_ua_prtn_country_clst_os_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.operating_system gte operating_systemStart)
.and(_.operating_system lt operating_systemEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
  }
def query(partner_id_list : List[Int], country_list : List[String], metric_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_ua_prtn_country_clst_os_browserTableAccessor, hourly_ua_prtn_country_clst_os_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.country in country_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], country_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_country_clst_os_browserTableAccessor, hourly_ua_prtn_country_clst_os_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.country in country_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partner_id_list : List[Int], country_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, operating_systemStart : Int, operating_systemEnd : Int) : SelectQuery[hourly_ua_prtn_country_clst_os_browserTableAccessor, hourly_ua_prtn_country_clst_os_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.country in country_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.operating_system gte operating_systemStart)
.and(_.operating_system lt operating_systemEnd)
  }
 def query(partner_id_list : List[Int], country_list : List[String], metric_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, operating_systemStart : Int, operating_systemEnd : Int, browserStart : Int, browserEnd : Int) : SelectQuery[hourly_ua_prtn_country_clst_os_browserTableAccessor, hourly_ua_prtn_country_clst_os_browserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.country in country_list)
.and(_.metric in metric_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.operating_system gte operating_systemStart)
.and(_.operating_system lt operating_systemEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
  }

}