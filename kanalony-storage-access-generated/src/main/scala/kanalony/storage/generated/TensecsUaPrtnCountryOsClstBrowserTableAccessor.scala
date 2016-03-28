package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsUaPrtnCountryOsClstBrowserTableAccessor extends CassandraTable[TensecsUaPrtnCountryOsClstBrowserTableAccessor, TensecsUaPrtnCountryOsClstBrowserRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object browser extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_ua_prtn_country_os_clst_browser"

  def fromRow(row: Row): TensecsUaPrtnCountryOsClstBrowserRow = {
    TensecsUaPrtnCountryOsClstBrowserRow(
      partner_id(row), 
country(row), 
operating_system(row), 
metric(row), 
tensecs(row), 
browser(row), 
value(row)
    )
  }

  def store(entity: TensecsUaPrtnCountryOsClstBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.browser, entity.browser)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, operatingSystem : Int, metric : Int) : SelectQuery[TensecsUaPrtnCountryOsClstBrowserTableAccessor, TensecsUaPrtnCountryOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, country : String, operatingSystem : Int, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnCountryOsClstBrowserTableAccessor, TensecsUaPrtnCountryOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerId : Int, country : String, operatingSystem : Int, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime, browserStart : Int, browserEnd : Int) : SelectQuery[TensecsUaPrtnCountryOsClstBrowserTableAccessor, TensecsUaPrtnCountryOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
  }
def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[Int]) : SelectQuery[TensecsUaPrtnCountryOsClstBrowserTableAccessor, TensecsUaPrtnCountryOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnCountryOsClstBrowserTableAccessor, TensecsUaPrtnCountryOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, browserStart : Int, browserEnd : Int) : SelectQuery[TensecsUaPrtnCountryOsClstBrowserTableAccessor, TensecsUaPrtnCountryOsClstBrowserRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
  }

}