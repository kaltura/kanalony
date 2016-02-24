package kanalony.storage.generated

import com.websudos.phantom.builder._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.dsl._

import scala.concurrent.Future

abstract class minutely_user_activity_prtn_countryTableAccessor extends CassandraTable[minutely_user_activity_prtn_countryTableAccessor, minutely_user_activity_prtn_countryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object count extends LongColumn(this)


  override def tableName = "minutely_user_activity_prtn_country"

  def fromRow(row: Row): minutely_user_activity_prtn_countryRow = {
    minutely_user_activity_prtn_countryRow(
      partner_id(row), 
metric(row), 
country(row), 
minute(row), 
count(row)
    )
  }

  def store(entity: minutely_user_activity_prtn_countryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.metric, entity.metric)
.value(_.country, entity.country)
.value(_.minute, entity.minute)
.value(_.count, entity.count)

      .future()
  }

  def query(partner_id : Int, metric : Int, country : String) : SelectQuery[minutely_user_activity_prtn_countryTableAccessor, minutely_user_activity_prtn_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.country eqs country)
  }
 def query(partner_id : Int, metric : Int, country : String, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_user_activity_prtn_countryTableAccessor, minutely_user_activity_prtn_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.country eqs country)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partner_id_list : List[Int], metric_list : List[Int], country_list : List[String]) : SelectQuery[minutely_user_activity_prtn_countryTableAccessor, minutely_user_activity_prtn_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.country in country_list)
  }
 def query(partner_id_list : List[Int], metric_list : List[Int], country_list : List[String], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_user_activity_prtn_countryTableAccessor, minutely_user_activity_prtn_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.country in country_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}