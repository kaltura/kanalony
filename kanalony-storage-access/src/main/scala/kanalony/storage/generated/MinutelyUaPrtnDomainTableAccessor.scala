package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnDomainTableAccessor extends CassandraTable[MinutelyUaPrtnDomainTableAccessor, MinutelyUaPrtnDomainRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object domain extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_domain"

  def fromRow(row: Row): MinutelyUaPrtnDomainRow = {
    MinutelyUaPrtnDomainRow(
      partner_id(row), 
domain(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnDomainRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.domain, entity.domain)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, domain : String, metric : Int) : SelectQuery[MinutelyUaPrtnDomainTableAccessor, MinutelyUaPrtnDomainRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, domain : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnDomainTableAccessor, MinutelyUaPrtnDomainRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partnerIdList : List[Int], domainList : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnDomainTableAccessor, MinutelyUaPrtnDomainRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnDomainTableAccessor, MinutelyUaPrtnDomainRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}