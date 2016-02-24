package kanalony.storage.generated

import com.websudos.phantom.builder._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.dsl._

import scala.concurrent.Future

abstract class minutely_user_activity_ptrn_domain_clst_referrerTableAccessor extends CassandraTable[minutely_user_activity_ptrn_domain_clst_referrerTableAccessor, minutely_user_activity_ptrn_domain_clst_referrerRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object domain extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object referrer extends StringColumn(this)with ClusteringOrder[String] with Ascending
object count extends LongColumn(this)


  override def tableName = "minutely_user_activity_ptrn_domain_clst_referrer"

  def fromRow(row: Row): minutely_user_activity_ptrn_domain_clst_referrerRow = {
    minutely_user_activity_ptrn_domain_clst_referrerRow(
      partner_id(row), 
domain(row), 
metric(row), 
minute(row), 
referrer(row), 
count(row)
    )
  }

  def store(entity: minutely_user_activity_ptrn_domain_clst_referrerRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.domain, entity.domain)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.referrer, entity.referrer)
.value(_.count, entity.count)

      .future()
  }

  def query(partner_id : Int, domain : String, metric : Int) : SelectQuery[minutely_user_activity_ptrn_domain_clst_referrerTableAccessor, minutely_user_activity_ptrn_domain_clst_referrerRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.domain eqs domain)
.and(_.metric eqs metric)
  }
 def query(partner_id : Int, domain : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_user_activity_ptrn_domain_clst_referrerTableAccessor, minutely_user_activity_ptrn_domain_clst_referrerRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partner_id : Int, domain : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, referrerStart : String, referrerEnd : String) : SelectQuery[minutely_user_activity_ptrn_domain_clst_referrerTableAccessor, minutely_user_activity_ptrn_domain_clst_referrerRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.referrer gte referrerStart)
.and(_.referrer lt referrerEnd)
  }
def query(partner_id_list : List[Int], domain_list : List[String], metric_list : List[Int]) : SelectQuery[minutely_user_activity_ptrn_domain_clst_referrerTableAccessor, minutely_user_activity_ptrn_domain_clst_referrerRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.domain in domain_list)
.and(_.metric in metric_list)
  }
 def query(partner_id_list : List[Int], domain_list : List[String], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_user_activity_ptrn_domain_clst_referrerTableAccessor, minutely_user_activity_ptrn_domain_clst_referrerRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.domain in domain_list)
.and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partner_id_list : List[Int], domain_list : List[String], metric_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime, referrerStart : String, referrerEnd : String) : SelectQuery[minutely_user_activity_ptrn_domain_clst_referrerTableAccessor, minutely_user_activity_ptrn_domain_clst_referrerRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.domain in domain_list)
.and(_.metric in metric_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.referrer gte referrerStart)
.and(_.referrer lt referrerEnd)
  }

}