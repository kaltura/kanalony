package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import scala.concurrent.Future

abstract class hourly_ua_prtn_entry_country_cityTableAccessor extends CassandraTable[hourly_ua_prtn_entry_country_cityTableAccessor, hourly_ua_prtn_entry_country_cityRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object city extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_entry_country_city"

  def fromRow(row: Row): hourly_ua_prtn_entry_country_cityRow = {
    hourly_ua_prtn_entry_country_cityRow(
      partner_id(row), 
entry_id(row), 
metric(row), 
country(row), 
city(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: hourly_ua_prtn_entry_country_cityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.entry_id, entity.entry_id)
.value(_.metric, entity.metric)
.value(_.country, entity.country)
.value(_.city, entity.city)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, entry_id : String, metric : Int, country : String, city : String, year : Int) : SelectQuery[hourly_ua_prtn_entry_country_cityTableAccessor, hourly_ua_prtn_entry_country_cityRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.metric eqs metric)
.and(_.country eqs country)
.and(_.city eqs city)
.and(_.year eqs year)
  }
 def query(partner_id : Int, entry_id : String, metric : Int, country : String, city : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_entry_country_cityTableAccessor, hourly_ua_prtn_entry_country_cityRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.metric eqs metric)
.and(_.country eqs country)
.and(_.city eqs city)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
def query(partner_id_list : List[Int], entry_id_list : List[String], metric_list : List[Int], country_list : List[String], city_list : List[String], year_list : List[Int]) : SelectQuery[hourly_ua_prtn_entry_country_cityTableAccessor, hourly_ua_prtn_entry_country_cityRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.metric in metric_list)
.and(_.country in country_list)
.and(_.city in city_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], metric_list : List[Int], country_list : List[String], city_list : List[String], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_ua_prtn_entry_country_cityTableAccessor, hourly_ua_prtn_entry_country_cityRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.metric in metric_list)
.and(_.country in country_list)
.and(_.city in city_list)
.and(_.year in year_list)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }

}