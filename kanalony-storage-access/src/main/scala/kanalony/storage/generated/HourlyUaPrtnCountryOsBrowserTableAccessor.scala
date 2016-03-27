package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnCountryOsBrowserTableAccessor extends CassandraTable[HourlyUaPrtnCountryOsBrowserTableAccessor, HourlyUaPrtnCountryOsBrowserRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object browser extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_country_os_browser"

  def fromRow(row: Row): HourlyUaPrtnCountryOsBrowserRow = {
    HourlyUaPrtnCountryOsBrowserRow(
      partner_id(row), 
country(row), 
operating_system(row), 
browser(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnCountryOsBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.operating_system, entity.operatingSystem)
.value(_.browser, entity.browser)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, operatingSystem : Int, browser : Int, metric : Int, year : Int) : SelectQuery[HourlyUaPrtnCountryOsBrowserTableAccessor, HourlyUaPrtnCountryOsBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.browser eqs browser)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partnerId : Int, country : String, operatingSystem : Int, browser : Int, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnCountryOsBrowserTableAccessor, HourlyUaPrtnCountryOsBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.browser eqs browser)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[Int], yearList : List[Int]) : SelectQuery[HourlyUaPrtnCountryOsBrowserTableAccessor, HourlyUaPrtnCountryOsBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.browser in browserList)
.and(_.metric in metricList)
.and(_.year in yearList)
  }
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnCountryOsBrowserTableAccessor, HourlyUaPrtnCountryOsBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.browser in browserList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }

}