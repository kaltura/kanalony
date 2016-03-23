package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPtrnOsClstBrowserTableAccessor extends CassandraTable[HourlyUaPtrnOsClstBrowserTableAccessor, HourlyUaPtrnOsClstBrowserRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object browser extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_ptrn_os_clst_browser"

  def fromRow(row: Row): HourlyUaPtrnOsClstBrowserRow = {
    HourlyUaPtrnOsClstBrowserRow(
      partner_id(row), 
operating_system(row), 
metric(row), 
year(row), 
hour(row), 
browser(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPtrnOsClstBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.browser, entity.browser)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, operatingSystem : Int, metric : Int, year : Int) : SelectQuery[HourlyUaPtrnOsClstBrowserTableAccessor, HourlyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partnerId : Int, operatingSystem : Int, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPtrnOsClstBrowserTableAccessor, HourlyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerId : Int, operatingSystem : Int, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, browserStart : Int, browserEnd : Int) : SelectQuery[HourlyUaPtrnOsClstBrowserTableAccessor, HourlyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
  }
def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int], yearList : List[Int]) : SelectQuery[HourlyUaPtrnOsClstBrowserTableAccessor, HourlyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPtrnOsClstBrowserTableAccessor, HourlyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, browserStart : Int, browserEnd : Int) : SelectQuery[HourlyUaPtrnOsClstBrowserTableAccessor, HourlyUaPtrnOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
  }

}