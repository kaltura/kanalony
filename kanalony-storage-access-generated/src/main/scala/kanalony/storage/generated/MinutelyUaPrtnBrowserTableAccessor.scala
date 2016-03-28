package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnBrowserTableAccessor extends CassandraTable[MinutelyUaPrtnBrowserTableAccessor, MinutelyUaPrtnBrowserRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object browser extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_browser"

  def fromRow(row: Row): MinutelyUaPrtnBrowserRow = {
    MinutelyUaPrtnBrowserRow(
      partner_id(row), 
browser(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.browser, entity.browser)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, browser : Int, metric : Int) : SelectQuery[MinutelyUaPrtnBrowserTableAccessor, MinutelyUaPrtnBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.browser eqs browser)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, browser : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnBrowserTableAccessor, MinutelyUaPrtnBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.browser eqs browser)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partnerIdList : List[Int], browserList : List[Int], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnBrowserTableAccessor, MinutelyUaPrtnBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.browser in browserList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], browserList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnBrowserTableAccessor, MinutelyUaPrtnBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.browser in browserList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}