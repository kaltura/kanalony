package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import scala.concurrent.Future

abstract class minutely_ua_prtn_countryTableAccessor extends CassandraTable[minutely_ua_prtn_countryTableAccessor, minutely_ua_prtn_countryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_country"

  def fromRow(row: Row): minutely_ua_prtn_countryRow = {
    minutely_ua_prtn_countryRow(
      partner_id(row), 
metric(row), 
country(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: minutely_ua_prtn_countryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.metric, entity.metric)
.value(_.country, entity.country)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, metric : Int, country : String) : SelectQuery[minutely_ua_prtn_countryTableAccessor, minutely_ua_prtn_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.country eqs country)
  }
 def query(partner_id : Int, metric : Int, country : String, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_countryTableAccessor, minutely_ua_prtn_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.country eqs country)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partner_id_list : List[Int], metric_list : List[Int], country_list : List[String]) : SelectQuery[minutely_ua_prtn_countryTableAccessor, minutely_ua_prtn_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.country in country_list)
  }
 def query(partner_id_list : List[Int], metric_list : List[Int], country_list : List[String], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_countryTableAccessor, minutely_ua_prtn_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.country in country_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}