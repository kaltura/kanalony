package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class minutely_ua_prtn_country_clst_entryTableAccessor extends CassandraTable[minutely_ua_prtn_country_clst_entryTableAccessor, minutely_ua_prtn_country_clst_entryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_country_clst_entry"

  def fromRow(row: Row): minutely_ua_prtn_country_clst_entryRow = {
    minutely_ua_prtn_country_clst_entryRow(
      partner_id(row), 
country(row), 
metric(row), 
minute(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: minutely_ua_prtn_country_clst_entryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.country, entity.country)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.entry_id, entity.entry_id)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, country : String, metric : Int) : SelectQuery[minutely_ua_prtn_country_clst_entryTableAccessor, minutely_ua_prtn_country_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.country eqs country)
.and(_.metric eqs metric)
  }
 def query(partner_id : Int, country : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_country_clst_entryTableAccessor, minutely_ua_prtn_country_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partner_id : Int, country : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, entry_idStart : String, entry_idEnd : String) : SelectQuery[minutely_ua_prtn_country_clst_entryTableAccessor, minutely_ua_prtn_country_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entry_idStart)
.and(_.entry_id lt entry_idEnd)
  }
def query(partner_id_list : List[Int], country_list : List[String], metric_list : List[Int]) : SelectQuery[minutely_ua_prtn_country_clst_entryTableAccessor, minutely_ua_prtn_country_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.country in country_list)
.and(_.metric in metric_list)
  }
 def query(partner_id_list : List[Int], country_list : List[String], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_country_clst_entryTableAccessor, minutely_ua_prtn_country_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.country in country_list)
.and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partner_id_list : List[Int], country_list : List[String], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime, entry_idStart : String, entry_idEnd : String) : SelectQuery[minutely_ua_prtn_country_clst_entryTableAccessor, minutely_ua_prtn_country_clst_entryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.country in country_list)
.and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entry_idStart)
.and(_.entry_id lt entry_idEnd)
  }

}