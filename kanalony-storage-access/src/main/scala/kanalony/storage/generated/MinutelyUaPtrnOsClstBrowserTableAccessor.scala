package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPtrnOsClstBrowserTableAccessor extends CassandraTable[MinutelyUaPtrnOsClstBrowserTableAccessor, MinutelyUaPtrnOsClstBrowserRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object browser extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_ptrn_os_clst_browser"

  def fromRow(row: Row): MinutelyUaPtrnOsClstBrowserRow = {
    MinutelyUaPtrnOsClstBrowserRow(
      partner_id(row), 
operating_system(row), 
metric(row), 
minute(row), 
browser(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPtrnOsClstBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.browser, entity.browser)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, operatingSystem : Int, metric : Int) : SelectQuery[MinutelyUaPtrnOsClstBrowserTableAccessor, MinutelyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, operatingSystem : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPtrnOsClstBrowserTableAccessor, MinutelyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, operatingSystem : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, browserStart : Int, browserEnd : Int) : SelectQuery[MinutelyUaPtrnOsClstBrowserTableAccessor, MinutelyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
  }
def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int]) : SelectQuery[MinutelyUaPtrnOsClstBrowserTableAccessor, MinutelyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPtrnOsClstBrowserTableAccessor, MinutelyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, browserStart : Int, browserEnd : Int) : SelectQuery[MinutelyUaPtrnOsClstBrowserTableAccessor, MinutelyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
  }

}